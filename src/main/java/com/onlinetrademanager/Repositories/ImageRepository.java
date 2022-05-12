package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.Image;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
    Optional<Image> findImageById(UUID id);
    void deleteImageById(UUID id);
    List<Image> findAllImagesByItem(Item item);

}
