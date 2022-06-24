package com.onlinetrademanager.DataTransferObjects.Items;

import com.onlinetrademanager.Models.Item;

import java.util.List;

public class FrontPageFeed {
    private List<ItemList> itemsVehicle;
    private List<ItemList> itemsSportHobby;
    private List<ItemList> itemsClothes;
    private List<ItemList> itemsPets;
    private List<ItemList> itemsElectronics;
    private List<ItemList> itemsGames;
    private List<ItemList> itemsAntiques;
    private List<ItemList> itemsOther;

    public FrontPageFeed() {
    }

    public List<ItemList> getItemsVehicle() {
        return itemsVehicle;
    }

    public List<ItemList> getItemsSportHobby() {
        return itemsSportHobby;
    }

    public List<ItemList> getItemsClothes() {
        return itemsClothes;
    }

    public List<ItemList> getItemsPets() {
        return itemsPets;
    }

    public List<ItemList> getItemsElectronics() {
        return itemsElectronics;
    }

    public List<ItemList> getItemsGames() {
        return itemsGames;
    }

    public List<ItemList> getItemsAntiques() {
        return itemsAntiques;
    }

    public List<ItemList> getItemsOther() {
        return itemsOther;
    }

    public void setItemsVehicle(List<ItemList> itemsVehicle) {
        this.itemsVehicle = itemsVehicle;
    }

    public void setItemsSportHobby(List<ItemList> itemsSportHobby) {
        this.itemsSportHobby = itemsSportHobby;
    }

    public void setItemsClothes(List<ItemList> itemsClothes) {
        this.itemsClothes = itemsClothes;
    }

    public void setItemsPets(List<ItemList> itemsPets) {
        this.itemsPets = itemsPets;
    }

    public void setItemsElectronics(List<ItemList> itemsElectronics) {
        this.itemsElectronics = itemsElectronics;
    }

    public void setItemsGames(List<ItemList> itemsGames) {
        this.itemsGames = itemsGames;
    }

    public void setItemsAntiques(List<ItemList> itemsAntiques) {
        this.itemsAntiques = itemsAntiques;
    }

    public void setItemsOther(List<ItemList> itemsOther) {
        this.itemsOther = itemsOther;
    }
}
