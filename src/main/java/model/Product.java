package model;

public class Product {

    private int id;
    private String name;
    private int price;
    private int amount;
    private String color;
    private String image;
    private String description;
    private int category;

    public Product() {
    }

    public Product(int id, String name, int price, int amount, String color, String image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.color = color;
        this.image = image;
        this.description = description;
    }

    public Product(String name, int price, int amount, String color, String image, String description, int category) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.color = color;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public Product(int id, String name, int price, int amount, String color, String image, String description, int category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.color = color;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

}
