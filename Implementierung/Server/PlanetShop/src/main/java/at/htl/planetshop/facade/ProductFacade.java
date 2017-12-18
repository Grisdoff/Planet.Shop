package at.htl.planetshop.facade;

import at.htl.planetshop.entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public ProductFacade() {
    }

    public List<Product> findAll(){
        return this.entityManager
                .createNamedQuery("findAll",Product.class)
                .getResultList();
    }

    public Product saveItem(Product card) {
        entityManager.persist(card);
        return card;
    }

    public Product findById(long id) {
        return this.entityManager.find(Product.class, id);
    }
}
