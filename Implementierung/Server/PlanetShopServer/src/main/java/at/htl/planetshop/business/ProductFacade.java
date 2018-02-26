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
import java.util.function.Supplier;

@Stateless
public class ProductFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public ProductFacade() {
    }

    public JsonArray getAllProducts(){ //nur price name und image
        List<Product> products = this.entityManager
                .createNamedQuery("Product.findAll",Product.class)
                .getResultList();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Product p : products) {
            jsonArrayBuilder.add(buildSimpleProductJson(p));
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

    public JsonArray getProductsByIds(List<Long> ids) {
        return ids.stream()
                .map(it -> findById(it))
                .collect(() -> Json.createArrayBuilder(),
                        (builder, product) -> builder.add(buildSimpleProductJson(product)),
                        (b1, b2) -> b1.addAll(b2))
                .build();
    }

    private JsonObject buildSimpleProductJson(Product p) {
        JsonObjectBuilder productBuilder = Json.createObjectBuilder();
        productBuilder.add("id", p.getId());
        productBuilder.add("name", p.getName());
        productBuilder.add("price", p.getPrice());
        productBuilder.add("image", Base64.getEncoder().encodeToString(p.getImage()));
        return productBuilder.build();
    }

    public JsonArray getFilteredProducts(String filter) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        List<Product> thelist = entityManager.createNamedQuery("Product.filter", Product.class).setParameter("filter", "%" + filter.toUpperCase() + "%").getResultList();
        thelist.stream().forEach(r -> arrayBuilder.add(Json.createObjectBuilder()
                .add("id", r.getId())
                .add("name", r.getName())
                .add("price", r.getPrice())
                .add("image", Base64.getEncoder().encodeToString(r.getImage()))));
        return arrayBuilder.build();
    }
}