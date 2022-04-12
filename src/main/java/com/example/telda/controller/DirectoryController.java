package com.example.telda.controller;

import com.example.telda.model.Directory;
import com.example.telda.service.DirectoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/telda/directory")
public class DirectoryController {

    private final DirectoryService directoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Directory>> getAllDirectories(){
        log.info("Try to get all directories");
        return ResponseEntity.ok(directoryService.getAllDirectories());
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateDirectory(@RequestBody Directory directory){
        log.info("Try to update directory");
        if (directoryService.updateDirectory(directory)){
            log.info("Directory was updated");
            return ResponseEntity.ok("Successfully updated");
        }
        log.warn("Directory wasn't found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no directory with specified id");
    }
}
