package com.example.telda.service;

import com.example.telda.model.Directory;

import java.util.List;

public interface DirectoryService {
    List<Directory> getAllDirectories();

    boolean updateDirectory(Directory directory);
}
