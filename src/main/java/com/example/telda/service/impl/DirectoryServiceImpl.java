package com.example.telda.service.impl;

import com.example.telda.model.Directory;
import com.example.telda.mapper.DirectoryMapper;
import com.example.telda.service.DirectoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public boolean updateDirectory(Directory directory) {
        return directoryMapper.update(directory);
    }
}
