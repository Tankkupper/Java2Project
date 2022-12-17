package java2.entity;

import javax.persistence.*;
import java.util.Objects;

//@Entity
//@Table(name = "repository", schema = "public", catalog = "Java2Project")
public class RepositoryEntity {
//    @Basic
//    @Column(name = "repo_id")
    private int repoId;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "repo_owner")
    private String repoOwner;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "repo_name")
    private String repoName;

    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }

    public String getRepoOwner() {
        return repoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        this.repoOwner = repoOwner;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public boolean isEmpty() {
        return repoName == null || repoOwner == null;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositoryEntity that = (RepositoryEntity) o;
        return repoId == that.repoId && Objects.equals(repoOwner, that.repoOwner) && Objects.equals(repoName, that.repoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repoId, repoOwner, repoName);
    }

    @Override
    public String toString() {
        return "RepositoryEntity{" +
                "repoId=" + repoId +
                ", repoOwner='" + repoOwner + '\'' +
                ", repoName='" + repoName + '\'' +
                '}';
    }
}
