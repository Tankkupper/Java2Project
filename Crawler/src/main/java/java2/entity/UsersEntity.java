package java2.entity;

import javax.persistence.*;
import java.util.Objects;

//@Entity
//@Table(name = "users", schema = "public", catalog = "Java2Project")
public class UsersEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "users_id")
    private int usersId;
//    @Basic
//    @Column(name = "repo_users_id")
    private Integer repoUsersId;
//    @Basic
//    @Column(name = "users_name")
    private String usersName;
//    @Basic
//    @Column(name = "commit_num")
    private Integer commitNum;

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public Integer getRepoUsersId() {
        return repoUsersId;
    }

    public void setRepoUsersId(Integer repoUsersId) {
        this.repoUsersId = repoUsersId;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
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
        UsersEntity that = (UsersEntity) o;
        return usersId == that.usersId && Objects.equals(repoUsersId, that.repoUsersId) && Objects.equals(usersName, that.usersName) && Objects.equals(commitNum, that.commitNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, repoUsersId, usersName, commitNum);
    }
}
