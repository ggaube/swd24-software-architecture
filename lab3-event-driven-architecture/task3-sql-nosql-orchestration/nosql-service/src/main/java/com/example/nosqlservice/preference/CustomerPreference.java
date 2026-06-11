package com.example.nosqlservice.preference;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer_preferences")
public class CustomerPreference {

    @Id
    private String id;

    private Long customerId;
    private String favoriteCategory;
    private boolean newsletter;

    public CustomerPreference() {
    }

    public CustomerPreference(String id, Long customerId, String favoriteCategory, boolean newsletter) {
        this.id = id;
        this.customerId = customerId;
        this.favoriteCategory = favoriteCategory;
        this.newsletter = newsletter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFavoriteCategory() {
        return favoriteCategory;
    }

    public void setFavoriteCategory(String favoriteCategory) {
        this.favoriteCategory = favoriteCategory;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }
}
