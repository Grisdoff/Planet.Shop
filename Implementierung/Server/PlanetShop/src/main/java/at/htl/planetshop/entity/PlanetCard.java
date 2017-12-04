package at.htl.planetshop.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "findAll",query = "select v from PlanetCard v")
})
@XmlRootElement
public class PlanetCard implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @Column(name = "Price")
    double Price;
    @Column(name = "Name")
    String Name;
    @Lob
    @Column(name = "PlanetPicture")
    byte[] image;

    public PlanetCard() {
    }

    public PlanetCard(double price, String name) {
        Price = price;
        Name = name;
    }

    public PlanetCard(double price, String name, byte[] image) {
        Price = price;
        Name = name;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return Id;
    }
}
