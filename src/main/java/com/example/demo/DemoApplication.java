package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@SpringBootApplication
@RestController
public class DemoApplication {

    @Value("${copy-file-path}")
    private String databaseBinLocation;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @GetMapping("/copyFile")
    private Boolean copyBatFileInBin() throws IOException {
        File source = new File("src/main/resources/templates/file.txt");
        File dest = new File(databaseBinLocation+"file.txt");

        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        sourceChannel = new FileInputStream(source).getChannel();
        destChannel = new FileOutputStream(dest).getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        sourceChannel.close();
        destChannel.close();
        return true;
    }
}
