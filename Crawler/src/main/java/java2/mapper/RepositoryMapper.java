package java2.mapper;

import java2.entity.RepositoryEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RepositoryMapper {

    @Select("Select * from repository")
    List<RepositoryEntity> selectAll();

    @Select("Select repo_id from repository where repo_owner = #{repoOwner} and repo_name = #{repoName}")
    RepositoryEntity select(RepositoryEntity repositoryEntity);

    @Select("Select * from repository where repo_id = #{id}")
    RepositoryEntity selectById(int id);

    @Insert("Insert into repository(repo_owner, repo_name) " +
            "values(#{repoOwner}, #{repoName})")
    int insert(RepositoryEntity repositoryEntity);


}
