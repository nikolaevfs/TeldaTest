package com.example.telda.mapper;

import com.example.telda.model.Directory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DirectoryMapper {

    @Select("select * from directory")
    List<Directory> findAll();

    @Update("UPDATE directory " +
            "SET name = #{name}, abbreviation = #{abbreviation} " +
            "WHERE id = #{id}")
    boolean update(Directory directory);
}
