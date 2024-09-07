package com.movieflix.movieAPI.controller;

import com.movieflix.movieAPI.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;


    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    @Value("${project.poster}")
    private String path;
        @PostMapping("/upload")
        public String uploadFilehandler(@RequestPart MultipartFile file) throws IOException {

            String uploadFilename = fileService.uploadFile(path, file);
            return"file uploaded :  "+uploadFilename;
        }

        @GetMapping("/{filename}")
  public void   serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
     InputStream resouceFile= fileService.getResourceFile(path,fileName);

response.setContentType(MediaType.IMAGE_PNG_VALUE);

            StreamUtils.copy(resouceFile,response.getOutputStream());


    }


}
