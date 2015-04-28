package com.myretail.myretail.Models;

import java.math.BigDecimal;

public class Item {
    private BigDecimal price;
    private Long id;
    private String name;
    private String detail;
    private String imageUrl;
    private Long categoryId;

    public Item(Long id, String name, String detail, String price, String imageUrl, Long categoryId) {
        this(id, name, categoryId);
        this.detail = detail;
        this.imageUrl = imageUrl;
        this.price = BigDecimal.valueOf(Double.parseDouble(price));
    }

    public Item(Long id, String name, Long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
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
