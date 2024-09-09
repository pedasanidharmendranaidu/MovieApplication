package com.movieflix.movieAPI.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class FileServiceImpl implements FileService{
    private static final Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        logger.info("info loggeer from movie FileService implementation class");
        logger.info(" uploadHandler method from fileImplementationcontroller::");
        String filePath=path + File.separator + fileName;
        File f=new File(path);
        if (!f.exists()) {
            f.mkdir();
            logger.warn("file from the fileserviceimlementation");
        }
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String name) throws FileNotFoundException {
        logger.info("info loggeer from movie FileService implementation class");
        logger.info(" serveHandler method from fileImplementationcontroller::");
        String filePath = path + File.separator + name;
        return new FileInputStream(filePath);

    }
}
