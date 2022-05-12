package com.onlinetrademanager.Services;

import com.onlinetrademanager.Exceptions.ImageNotFoundException;
import com.onlinetrademanager.Exceptions.ItemNotFoundException;
import com.onlinetrademanager.Models.Image;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Repositories.ImageRepository;
import com.onlinetrademanager.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ImageService {

    public final ImageRepository imageRepository;
    public final ItemRepository itemRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, ItemRepository itemRepository){
        this.imageRepository = imageRepository;
        this.itemRepository = itemRepository;
    }

    public Image insertImage(Image image){
        imageRepository.save(image);
        return image;
    }

    public void deleteImageByID(UUID id){
        imageRepository.deleteImageById(id);
    }

    public void deleteImage(Image image){
        imageRepository.delete(image);
    }

    public Image updateImage(Image image){
        Image imageUpd = new Image(image);
        imageRepository.save(imageUpd);
        return imageUpd;
    }

    public List<Image> findAllImages(){
        List<Image> imageList = imageRepository.findAll();
        return imageList;
    }

    public List<Image> findAllImagesByItem(UUID id){
        Item item = itemRepository.getById(id);
        List<Image> imageList = imageRepository.findAllImagesByItem(item);
        return imageList;
    }

    public Image findImageById(UUID id){
        return imageRepository.findImageById(id).orElseThrow(()
                -> new ImageNotFoundException("Item " + id + "not found!"));
    }
}
