package at.htl.planetshop.business;

import at.htl.planetshop.entities.ShoppingCart;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ShoppingCartFacade {
    @PersistenceContext
    EntityManager em;


    public void add(Long productId, Long userId) {
        List<ShoppingCart> list = em.createNamedQuery("ShoppingCart.GetByIds", ShoppingCart.class).setParameter("prodId", productId).setParameter("usId", userId).getResultList();
        ShoppingCart item;
        if(list.size() != 0){
            item = list.get(0);
            item.setAmount(item.getAmount() + 1);
            em.merge(item);
        }
        else{
            item = new ShoppingCart(productId, userId);
            em.persist(item);

        }
    }

    public void remove(Long productId, Long userId){
        ShoppingCart s = em.createNamedQuery("ShoppingCart.GetByIds", ShoppingCart.class).setParameter("prodId", productId).setParameter("usId", userId).getSingleResult();


        if(s.getAmount() == 1)
            em.createNamedQuery("ShoppingCart.DeleteByIds").setParameter("prodId", productId).setParameter("usId", userId).executeUpdate();
        else {
            s.setAmount(s.getAmount() - 1);
            em.merge(s);
        }
    }


}
