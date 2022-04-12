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
    public ResponseEntity<List<Directory>> getAllDirectories() {
        log.info("Try to get all directories");
        return ResponseEntity.ok(directoryService.getAllDirectories());
    }

    @PostMapping("/new")
    public ResponseEntity<String> addNewDirectory(@RequestBody Directory directory) {
        log.info("Try to add new directory");
        directoryService.addDirectory(directory);
        return ResponseEntity.ok("Directory with name " + directory.getName() + " was added");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDirectory(@PathVariable Long id) {
        log.info("Try to delete directory");
        if (directoryService.deleteDirectory(id)) {
            log.info("Directory was deleted");
            return ResponseEntity.ok("Directory with id " + id + " was deleted");
        }
        log.warn("There is no directory with id = " + id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no directory with id = " + id);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateDirectory(@RequestBody Directory directory) {
        log.info("Try to update directory");
        if (directoryService.updateDirectory(directory)) {
            log.info("Directory was updated");
            return ResponseEntity.ok("Successfully updated");
        }
        log.warn("Directory wasn't found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no directory with specified id");
    }
}
