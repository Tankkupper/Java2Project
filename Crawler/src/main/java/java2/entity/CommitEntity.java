package java2.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

//@Entity
//@Table(name = "commit", schema = "public", catalog = "Java2Project")
public class CommitEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "commit_id")
    private int commitId;
//    @Basic
//    @Column(name = "repo_commit_id")
    private Integer repoCommitId;
//    @Basic
//    @Column(name = "commit_owner")
    private String commitOwner;
//    @Basic
//    @Column(name = "time_commit")
    private Timestamp timeCommit;

    public int getCommitId() {
        return commitId;
    }

    public void setCommitId(int commitId) {
        this.commitId = commitId;
    }

    public Integer getRepoCommitId() {
        return repoCommitId;
    }

    public void setRepoCommitId(Integer repoCommitId) {
        this.repoCommitId = repoCommitId;
    }

    public String getCommitOwner() {
        return commitOwner;
    }

    public void setCommitOwner(String commitOwner) {
        this.commitOwner = commitOwner;
    }

    public Timestamp getTimeCommit() {
        return timeCommit;
    }

    public void setTimeCommit(Timestamp timeCommit) {
        this.timeCommit = timeCommit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitEntity that = (CommitEntity) o;
        return commitId == that.commitId && Objects.equals(repoCommitId, that.repoCommitId) && Objects.equals(commitOwner, that.commitOwner) && Objects.equals(timeCommit, that.timeCommit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commitId, repoCommitId, commitOwner, timeCommit);
    }
}
