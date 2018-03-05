package at.htl.planetshop.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Product.findAll",query = "select v from Product v"),
        @NamedQuery(name = "Product.filter", query = "select v from Product v where upper(v.name) like :filter")
})
@XmlRootElement
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "No price given")
    private double price;

    @Column(name = "name")
    @Size(min = 5, max = 50, message = "Given name too long or too short")
    private String name;

    @Lob
    @NotNull
    private byte[] image;

    @Basic(fetch = FetchType.LAZY)
    private String description;

    //region Constructors
    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Product() {
    }

    public Product(double price, String name, byte[] image, String description) {
        this.price = price;
        this.name = name;
        this.image = image;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion
}

