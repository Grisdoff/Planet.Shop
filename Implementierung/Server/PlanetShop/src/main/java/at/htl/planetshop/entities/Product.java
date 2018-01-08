package at.htl.planetshop.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "findAll",query = "select v from Product v")
})
@XmlRootElement
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private String name;
    @Lob
    private byte[] image;

    private String details;

    public Product() {
    }

    //region Constructors
    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Product(double price, String name, byte[] image, String details) {
        this.price = price;
        this.name = name;
        this.image = image;
        this.details = details;
    }
    //endregion

    //region Getter Setter
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    //endregion
}

