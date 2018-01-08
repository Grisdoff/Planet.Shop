package at.htl.planetshopapp.entity;

import android.graphics.Bitmap;

/**
 * Created by Patrick on 24.11.2017.
 */

public class PlanetCard {

    private Long id;
    private double price;
    private String name;
    private Bitmap imageView;
    private String description;

    //region Constructors
    public PlanetCard() {
    }

    public PlanetCard(Long id, double price, String name, Bitmap imageView) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.imageView = imageView;
    }
    //endregion

    //region Getter Setter
    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImageView() {
        return imageView;
    }

    public void setImageView(Bitmap imageView) {
        this.imageView = imageView;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion
}
