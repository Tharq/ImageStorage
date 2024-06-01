package com.bharani.ImageData.Repository;

import com.bharani.ImageData.model.ImageData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository
public interface ImageRepo extends MongoRepository<ImageData,String> {

    Optional<ImageData> findByName(String name);
}
