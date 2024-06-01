package com.bharani.ImageData.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("image")
public class ImageData {

    @Id
    private String id;
    private String name;
    private String type;
    private String title;
    private String description;
    private byte[] imageData;
}
