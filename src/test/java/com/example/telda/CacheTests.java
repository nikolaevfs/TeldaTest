package com.example.telda;

import com.example.telda.service.DirectoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
public class CacheTests {

    @Autowired
    private DirectoryService directoryService;

    @Test
    public void testingCache(CapturedOutput output) {
        directoryService.getAllDirectories();
        directoryService.getAllDirectories();
        directoryService.getAllDirectories();
        directoryService.getAllDirectories();

        assertThat(output.toString()).doesNotContainPattern("( *)INFO( *)INFO( *)");
    }
}
