package java2.mapper;

import java2.entity.ReleaseEntity;
import java2.entity.RepositoryEntity;
import java2.entity.UsersEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsersMapper {

    @Select("Select * from users where repo_users_id = #{repoId}")
    List<UsersEntity> selectAll(int repoId);

    @Select("Select * from users where repo_users_id = #{repoId} limit #{limit}")
    List<UsersEntity> selectWithLimit(int repoId, int limit);

    @Insert("Insert into users(repo_users_id, users_name, commit_num) " +
            "values(#{repoUsersId}, #{usersName}, #{commitNum})")
    int insert(UsersEntity usersEntity);
}
