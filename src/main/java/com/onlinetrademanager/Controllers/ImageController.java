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
@RequestMapping("/api/Image")
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

    @GetMapping("/All")
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> examinations = imageService.findAllImages();
        return new ResponseEntity<>(examinations, HttpStatus.OK);
    }

    @GetMapping("/All_By_Item")
    public ResponseEntity<List<Image>> getAllImagesByItem(@RequestParam UUID id) {
        List<Image> examinations = imageService.findAllImagesByItem(id);
        return new ResponseEntity<>(examinations, HttpStatus.OK);
    }

    @PostMapping()
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

    @PutMapping()
    public ResponseEntity<Image> updateExamination(@RequestBody Image image) {
        Image updateImage = null;
        try {
            updateImage = imageService.updateImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateImage, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteImage(@RequestParam UUID id) {
        imageService.deleteImageByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
