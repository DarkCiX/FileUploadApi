package com.bezkoder.spring.files.upload;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bezkoder.spring.files.upload.service.FilesStorageService;

@SpringBootApplication
public class SpringBootUploadFilesApplication implements CommandLineRunner , WebMvcConfigurer {
  @Resource
  FilesStorageService storageService;

  
  
  public static void main(String[] args) {
    SpringApplication.run(SpringBootUploadFilesApplication.class, args);
  }
  
 

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

      // Register resource handler for images
      registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
              .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
  }

  @Override
  public void run(String... arg) throws Exception {
    storageService.init();
  }
  
  
}
