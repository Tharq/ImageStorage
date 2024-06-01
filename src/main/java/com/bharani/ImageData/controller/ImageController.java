package com.bharani.ImageData.controller;

import com.bharani.ImageData.Service.ImageService;
import com.bharani.ImageData.model.ImageData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageService service;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile file,
                                         @RequestPart("title") String title,
                                         @RequestPart("description") String description) throws IOException {
        return service.uploadImage(file,title,description);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] img = service.downloadImage(fileName);
          return ResponseEntity.ok().contentType(MediaType.valueOf("image/png"))
                  .body(img);
    }
    @GetMapping("/all")
    public List<ImageData> find(){
        return service.findAll();
    }
}
