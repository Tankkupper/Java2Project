package java2.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

//@Entity
//@Table(name = "issue", schema = "public", catalog = "Java2Project")
public class IssueEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "issue_id")
    private int issueId;
//    @Basic
//    @Column(name = "repo_issue_id")
    private Integer repoIssueId;
//    @Basic
//    @Column(name = "is_closed")
    private boolean isClosed;
//    @Basic
//    @Column(name = "start_time")
    private Timestamp startTime;
//    @Basic
//    @Column(name = "end_time")
    private Timestamp endTime;

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public Integer getRepoIssueId() {
        return repoIssueId;
    }

    public void setRepoIssueId(Integer repoIssueId) {
        this.repoIssueId = repoIssueId;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueEntity that = (IssueEntity) o;
        return issueId == that.issueId && isClosed == that.isClosed && Objects.equals(repoIssueId, that.repoIssueId) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueId, repoIssueId, isClosed, startTime, endTime);
    }
}
