package fr.rtp.onemany;

import java.awt.Color;

public class Product {

    private String id;
    private String name;
    private Color color;
    private float price;
    private ProductSize productSize;

    public Product(String id, String name, Color color, float price, ProductSize productSize) {
        super();
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.productSize = productSize;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public float getPrice() {
        return price;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

}
