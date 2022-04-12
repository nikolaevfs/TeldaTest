package com.example.telda.mapper;

import com.example.telda.model.Directory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DirectoryMapper {

    @Select("select * from directory")
    List<Directory> findAll();

    @Select("insert into directory (name, abbreviation) " +
            "values (#{name}, #{abbreviation})")
    void addNew(Directory directory);

    @Delete("delete from directory where id = #{id}")
    boolean deleteDirectory(@Param("id") Long id);

    @Update("update directory " +
            "set name = #{name}, abbreviation = #{abbreviation} " +
            "where id = #{id}")
    boolean update(Directory directory);
}
