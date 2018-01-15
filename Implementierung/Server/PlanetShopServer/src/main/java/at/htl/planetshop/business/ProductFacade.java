package at.htl.planetshop.business;

import at.htl.planetshop.entities.Product;

import javax.ejb.Stateless;
import javax.json.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Produces;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;
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
            JsonObjectBuilder productBuilder = Json.createObjectBuilder();
            productBuilder.add("id", p.getId());
            productBuilder.add("name", p.getName());
            productBuilder.add("price", p.getPrice());
            productBuilder.add("image", Base64.getEncoder().encodeToString(p.getImage()));
            jsonArrayBuilder.add(productBuilder.build());
        }
        return jsonArrayBuilder.build();
    }

    public Product saveItem(Product card) {
        entityManager.persist(card);
        return card;
    }

    public Product findById(Long id) {
        return this.entityManager.find(Product.class, id);
    }
}