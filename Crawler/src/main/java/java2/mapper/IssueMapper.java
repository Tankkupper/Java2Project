package java2.mapper;


import java2.entity.IssueEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IssueMapper {

    @Select("Select * from issue")
    List<IssueEntity> selectAll();

    @Select("Select * from issue where is_closed = true and repo_issue_id = #{repoId}")
    List<IssueEntity> selectClosed(int repoId);

    @Select("Select count(*) from issue where is_closed = true and repo_issue_id = #{repoId}")
    int selectClosedNum(int repoId);

    @Select("Select count(*) from issue where is_closed = false and repo_issue_id = #{repoId}")
    int selectOpenNum(int repoId);

    @Insert("Insert into issue(repo_issue_id, is_closed, start_time, end_time) " +
            "values(#{repoIssueId}, #{isClosed}, #{startTime}, #{endTime})")
    int insert(IssueEntity issueEntity);

    @Select("select count(*) from issue where end_time - start_time < '1 hour' and repo_issue_id = #{repoId}")
    int solveTimeOneHour(int repoId);

    @Select("select count(*) from issue where end_time - start_time < '1 day' and repo_issue_id = #{repoId}")
    int solveTimeOneDay(int repoId);

    @Select("select count(*) from issue where end_time - start_time < '7 day' and repo_issue_id = #{repoId}")
    int solveTimeSevenDay(int repoId);

    @Select("select count(*) from issue where end_time - start_time < '1 month' and repo_issue_id = #{repoId}")
    int solveTimeOneMonth(int repoId);

    @Select("select count(*) from issue where end_time - start_time < '6 month' and repo_issue_id = #{repoId}")
    int solveTimeHalfYear(int repoId);

    @Select("select round(avg(extract(epoch from end_time) - extract(epoch from start_time))) from issue where  is_closed = true and repo_issue_id = #{repoId}")
    long solveTimeAvg(int repoId);
}
