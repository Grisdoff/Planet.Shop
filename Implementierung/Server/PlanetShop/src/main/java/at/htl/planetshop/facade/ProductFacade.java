package at.htl.planetshop.facade;

import at.htl.planetshop.entity.Product;

import javax.ejb.Stateless;
import javax.json.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Produces;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Stateless
public class ProductFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public ProductFacade() {
    }

    public JsonArray getAllProducts(){ //nur price name und image
        List<Product> products = this.entityManager
                .createNamedQuery("findAll",Product.class)
                .getResultList();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Product p : products) {
            jsonArrayBuilder.add(Json.createObjectBuilder().add("name", p.getName()).add("price", p.getPrice()).add("image", p.getImage().toString()));
        }
        return jsonArrayBuilder.build();
    }

    public Product saveItem(Product card) {
        entityManager.persist(card);
        return card;
    }

    public Product findById(long id) {
        return this.entityManager.find(Product.class, id);
    }
}
