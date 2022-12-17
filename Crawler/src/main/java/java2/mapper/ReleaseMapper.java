package java2.mapper;

import java2.entity.ReleaseEntity;
import java2.entity.RepositoryEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReleaseMapper {

    @Select("Select * from release")
    List<ReleaseEntity> selectAll();

    @Select("Select * from release " +
            "where repo_release_id = #{repoId} " +
            "order by time_release desc " +
            "limit #{limit} offset #{offset}")
    List<ReleaseEntity> limitAndPage(int repoId, int limit, int offset);

    @Insert("Insert into release(repo_release_id, time_release, commit_num) " +
            "values(#{repoReleaseId}, #{timeRelease}, #{commitNum})")
    int insert(ReleaseEntity releaseEntity);

    @Select("select count(*) from release where repo_release_id = #{repoId}")
    int releaseNum(int repoId);

    @Select("select avg(commit_num) from release where repo_release_id = #{repoId}")
    Double avgCommitNum(int repoId);


}
