package com.onlinetrademanager.Models;

import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Models.Users.Client;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ClientBlockedCategoriesOnFeed {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @ManyToOne
    private Client client;
    private ItemCategory category;

    public ClientBlockedCategoriesOnFeed() {
    }

    public UUID getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }
}
