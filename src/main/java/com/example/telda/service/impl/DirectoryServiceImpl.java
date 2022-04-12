package com.example.telda.service.impl;

import com.example.telda.model.Directory;
import com.example.telda.mapper.DirectoryMapper;
import com.example.telda.service.DirectoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DirectoryServiceImpl implements DirectoryService {

    private final DirectoryMapper directoryMapper;

    @Override
    @Cacheable(value = "directories")
    public List<Directory> getAllDirectories() {
        log.info("Getting directories from db");
        return directoryMapper.findAll();
    }

    @Override
    @CacheEvict(value = "directories", allEntries=true)
    public void addDirectory(Directory directory) {
        log.info("Adding new directory");
        directoryMapper.addNew(directory);
    }

    @Override
    @CacheEvict(value = "directories", allEntries=true)
    public boolean deleteDirectory(Long id) {
        log.info("Deleting directory");
        return directoryMapper.deleteDirectory(id);
    }

    @Override
    @CacheEvict(value = "directories", allEntries=true)
    public boolean updateDirectory(Directory directory) {
        return directoryMapper.update(directory);
    }
}
