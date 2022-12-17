package java2.mapper;

import java2.entity.CommitEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface CommitMapper {
    @Select("Select * from commit")
    List<CommitEntity> selectAll();

    @Select("Select count(*) from commit where " +
            "time_commit > #{smallTime} and " +
            "time_commit <= #{largeTime} and repo_commit_id = #{repoId}" )
    int selectByTimeLimit(Timestamp smallTime, Timestamp largeTime, int repoId);

    @Insert("Insert into commit(repo_commit_id, commit_owner, time_commit) " +
            "values(#{repoCommitId}, #{commitOwner}, #{timeCommit})")
    int insert(CommitEntity commitEntity);


    @Select("select count(*) from " +
            "(select extract(dow from time_commit) as ex from commit " +
            "where repo_commit_id = #{repoId}) " +
            "as ce where ce.ex = 0")
    int sundayNum(int repoId);

    @Select("select count(*) from " +
            "(select extract(dow from time_commit) as ex from commit " +
            "where repo_commit_id = #{repoId}) " +
            "as ce where ce.ex = 1")
    int mondayNum(int repoId);

    @Select("select count(*) from " +
            "(select extract(dow from time_commit) as ex from commit " +
            "where repo_commit_id = #{repoId}) " +
            "as ce where ce.ex = 2")
    int tuesdayNum(int repoId);

    @Select("select count(*) from " +
            "(select extract(dow from time_commit) as ex from commit " +
            "where repo_commit_id = #{repoId}) " +
            "as ce where ce.ex = 3")
    int wednesdayNum(int repoId);

    @Select("select count(*) from " +
            "(select extract(dow from time_commit) as ex from commit " +
            "where repo_commit_id = #{repoId}) " +
            "as ce where ce.ex = 4")
    int thursdayNum(int repoId);

    @Select("select count(*) from " +
            "(select extract(dow from time_commit) as ex from commit " +
            "where repo_commit_id = #{repoId}) " +
            "as ce where ce.ex = 5")
    int fridayNum(int repoId);

    @Select("select count(*) from " +
            "(select extract(dow from time_commit) as ex from commit " +
            "where repo_commit_id = #{repoId}) " +
            "as ce where ce.ex = 6")
    int saturdayNum(int repoId);

    @Select("select count(*) from " +
            "(select extract(hour from time_commit) as ex from commit " +
            "where repo_commit_id = #{repoId}) " +
            "as ce where ce.ex < 12")
    int morning(int repoId);


    @Select("select count(*) from " +
            "(select extract(hour from time_commit) as ex from commit " +
            "where repo_commit_id = #{repoId}) " +
            "as ce where ce.ex > 12")
    int afternoon(int repoId);
}
