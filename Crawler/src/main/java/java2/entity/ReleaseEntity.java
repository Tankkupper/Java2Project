package java2.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

//@Entity
//@Table(name = "release", schema = "public", catalog = "Java2Project")
public class ReleaseEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "release_id")
    private int releaseId;
//    @Basic
//    @Column(name = "repo_release_id")
    private Integer repoReleaseId;
//    @Basic
//    @Column(name = "time_release")
    private Timestamp timeRelease;
//    @Basic
//    @Column(name = "commit_num")
    private Integer commitNum;

    public int getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
    }

    public Integer getRepoReleaseId() {
        return repoReleaseId;
    }

    public void setRepoReleaseId(Integer repoReleaseId) {
        this.repoReleaseId = repoReleaseId;
    }

    public Timestamp getTimeRelease() {
        return timeRelease;
    }

    public void setTimeRelease(Timestamp timeRelease) {
        this.timeRelease = timeRelease;
    }

    public Integer getCommitNum() {
        return commitNum;
    }

    public void setCommitNum(Integer commitNum) {
        this.commitNum = commitNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReleaseEntity that = (ReleaseEntity) o;
        return releaseId == that.releaseId && Objects.equals(repoReleaseId, that.repoReleaseId) && Objects.equals(timeRelease, that.timeRelease) && Objects.equals(commitNum, that.commitNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(releaseId, repoReleaseId, timeRelease, commitNum);
    }
}
