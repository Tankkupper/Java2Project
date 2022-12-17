package java2.controller;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import java2.utils.Crawler;
import java2.entity.*;
import java2.mapper.*;
import java2.utils.DateUtil;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;

@RestController
public class CrawlerController {
    Thread crawlerThread = null;

    private RepositoryEntity repository;
    @Autowired
    RepositoryMapper repositoryMapper;
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    CommitMapper commitMapper;
    @Autowired
    ReleaseMapper releaseMapper;

    @GetMapping("/crawler")
    public String crawlerTech(){
        if (crawlerThread == null || !crawlerThread.isAlive()) {
            return "The Crawler is ready to work!";
        } else {
            return "The Crawler is working!";
        }
    }

    @PostMapping("/crawler")
    public String crawler(@RequestBody RepositoryEntity repository){
        if (crawlerThread != null  && crawlerThread.isAlive()) {
            return "Crawler is working!";
        }
        this.repository = repository;
        crawlerThread = new Thread(this::run);
        crawlerThread.start();
        return "Crawler starts to work!";
    }

    @PostMapping("/crawler/insert")
    public String insert(@RequestBody RepositoryEntity repository) {
        int i = repositoryMapper.insert(repository);
        if (i > 0) {
            return "成功";
        } else {
            return "失败";
        }
    }

    @PostMapping("/crawler/select")
    public RepositoryEntity select(@RequestBody RepositoryEntity repository) {
        return repositoryMapper.select(repository);
    }

    @PostMapping("/crawler/selectbytime")
    public int select(Timestamp smallTime, Timestamp largeTime) {
//        System.out.println(smallTime);
//        System.out.println(largeTime);
        return commitMapper.selectByTimeLimit(smallTime, largeTime, repository.getRepoId());
    }
    public void run() {
        String owner = repository.getRepoOwner();
        String name = repository.getRepoName();
        RepositoryEntity tempEntity = repositoryMapper.select(repository);
        if (tempEntity != null && tempEntity.isEmpty()) {
            System.out.println("Repository has existed!");
            return;
        }

        repositoryMapper.insert(repository);
        // get serials repo_id
        this.repository = repositoryMapper.select(repository);
        int repoId = this.repository.getRepoId();
        try {
            Crawler crawler = new Crawler(owner, name);

            // get contributors
            JSONArray contributors = crawler.getContributors();
            for (Object contributor : contributors) {
                JSONObject obj = (JSONObject) contributor;
                UsersEntity entity = new UsersEntity();
                entity.setRepoUsersId(repoId);
                entity.setUsersName(obj.getString("login"));
                entity.setCommitNum(obj.getInteger("contributions"));
                usersMapper.insert(entity);
            }


            // get issue
            /*
            "state": "closed",
            "locked": false,
            "assignees": [],
            "comments": 0,
            "created_at": "2022-07-14T07:41:36Z",
            "updated_at": "2022-07-14T07:42:55Z",
            "closed_at": "2022-07-14T07:42:55Z",*/

            JSONArray issues = crawler.getIssues();
            for (Object issue : issues) {
                JSONObject obj = (JSONObject) issue;

                // if this is a pull request not an issue (although all requests are issues)
                if (obj.containsKey("pull_request")) {
                    continue;
                }
                IssueEntity entity = new IssueEntity();
                entity.setRepoIssueId(repoId);
                entity.setClosed(obj.getString("state").equals("closed"));
                entity.setStartTime(DateUtil.ISOToTimestamp(obj.getString("created_at")));
                entity.setEndTime(DateUtil.ISOToTimestamp(obj.getString("closed_at")));
                issueMapper.insert(entity);
            }


            JSONArray commits = crawler.getCommits();
            for (Object commit : commits) {
                JSONObject obj = (JSONObject) commit;
                CommitEntity entity = new CommitEntity();
                entity.setRepoCommitId(repoId);

                entity.setCommitOwner(
                                (
                                        (
                                                (JSONObject)
                                                        (
                                                                (JSONObject) obj.get("commit")
                                                        ).get("committer"))
                                                .getString("name")
                                ).replace(" ", "")
                );
                entity.setTimeCommit(
                        DateUtil.ISOToTimestamp(
                                (
                                        (
                                                (JSONObject)
                                                        (
                                                                (JSONObject) obj.get("commit")
                                                        ).get("committer"))
                                                .getString("date")
                                )
                        )
                );
                commitMapper.insert(entity);
            }

            JSONArray releases = crawler.getReleases();
            for (int i= releases.size() - 1; i >= 0; i--) {
                JSONObject obj = (JSONObject) releases.get(i);
                Timestamp smallTime;
                Timestamp largeTime = DateUtil.ISOToTimestamp(obj.getString("created_at"));
                if (i != releases.size()-1) {
                    smallTime = DateUtil.ISOToTimestamp(((JSONObject) releases.get(i+1)).getString("created_at"));
                } else {
                    smallTime = Timestamp.valueOf("2000-01-01 00:00:01");
                }
                ReleaseEntity entity = new ReleaseEntity();
                entity.setRepoReleaseId(repoId);
                entity.setTimeRelease(largeTime);

                int commitNum = commitMapper.selectByTimeLimit(smallTime, largeTime, repoId);
                entity.setCommitNum(commitNum);
//                System.out.println(commitNum);
//                System.out.println(smallTime);
//                System.out.println(largeTime);
                releaseMapper.insert(entity);
            }

            System.out.println("The Crawler task finished!");
        } catch (URISyntaxException | IOException | ParseException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}