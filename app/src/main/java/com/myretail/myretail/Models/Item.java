package com.myretail.myretail.models;

import java.math.BigDecimal;

public class Item {
    private BigDecimal price;
    private Long id;
    private String name;
    private String detail;
    private String imageUrl;
    private Long categoryId;

    public Item(Long id, String name, String detail, String price, String imageUrl, String categoryId) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.imageUrl = imageUrl;
        this.categoryId = Long.parseLong(categoryId);
        this.price = BigDecimal.valueOf(Double.parseDouble(price));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
