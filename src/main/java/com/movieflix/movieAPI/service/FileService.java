package com.movieflix.movieAPI.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
@Service
public interface FileService {
   public String uploadFile(String path, MultipartFile file)throws IOException;

   public InputStream getResourceFile(String path, String name) throws FileNotFoundException;
}
