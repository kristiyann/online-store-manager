package com.onlinetrademanager.Controllers;


import com.onlinetrademanager.Models.Image;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(UUID id) {
        Image image = imageService.findImageById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/all_images")
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> examinations = imageService.findAllImages();
        return new ResponseEntity<>(examinations, HttpStatus.OK);
    }

    @GetMapping("/all/item/{id}")
    public ResponseEntity<List<Image>> getAllImagesByItem(UUID id) {
        List<Image> examinations = imageService.findAllImagesByItem(id);
        return new ResponseEntity<>(examinations, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Image> addExamination(@RequestBody Image image) {
        Image newImage = null;
        try {
            newImage = imageService.insertImage(image);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(newImage, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Image> updateExamination(@RequestBody Image image) {
        Image updateImage = null;
        try {
            updateImage = imageService.updateImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateImage, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") UUID id) {
        imageService.deleteImageByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
