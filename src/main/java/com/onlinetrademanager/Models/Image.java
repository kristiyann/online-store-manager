package com.onlinetrademanager.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Image implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    private String imageURL;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    public Image(){

    }

    public Image(Image image){
        setId(image.getId());
        setImageURL(image.getImageURL());
        setItem(image.getItem());
    }

    public Image(String imageURL, Item item){
        setImageURL(imageURL);
        setItem(item);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
