package com.bharani.ImageData.Service;

import com.bharani.ImageData.Repository.ImageRepo;
import com.bharani.ImageData.Util.ImageUtil;
import com.bharani.ImageData.model.ImageData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepo imageRepo;

    public ResponseEntity<Map<String, String>> uploadImage(
             MultipartFile file,
            String title,
            String description) throws IOException {

        ImageData data = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes()))
                .title(title)  // Assuming you have a title field in your ImageData class
                .description(description)  // Assuming you have a description field in your ImageData class
                .build();

        imageRepo.save(data);

        Map<String, String> response = new HashMap<>();
        response.put("Status", "upload successful");
        response.put("title",data.getTitle());
        response.put("description",data.getDescription());
        response.put("file", file.getOriginalFilename());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public byte[] downloadImage(String name){
        Optional<ImageData> img = imageRepo.findByName(name);
       return ImageUtil.decompressImage(img.get().getImageData());
    }

    public List<ImageData> findAll() {
        return imageRepo.findAll();
    }
}
