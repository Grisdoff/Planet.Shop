package at.htl.planetshopapp.entity;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Patrick on 24.11.2017.
 */

public class PlanetCard {

    private  int Id;
    private double Price;
    private String Name;
    private Bitmap imageView;

    public PlanetCard(double price, String name, Bitmap imageView) {
        Price = price;
        Name = name;
        this.imageView = imageView;
    }

    public PlanetCard(int id, double price, String name) {
        Id = id;
        Price = price;
        Name = name;
    }

    public PlanetCard(int id, double price, String name, Bitmap imageView) {
        Id = id;
        Price = price;
        Name = name;
        this.imageView = imageView;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Bitmap getImageView() {
        return imageView;
    }

    public void setImageView(Bitmap imageView) {
        this.imageView = imageView;
    }
}
