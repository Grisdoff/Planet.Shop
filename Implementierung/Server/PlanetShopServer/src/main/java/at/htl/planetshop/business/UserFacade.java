package at.htl.planetshop.business;

import at.htl.planetshop.entities.ShoppingCart;
import at.htl.planetshop.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserFacade {
    @PersistenceContext
    EntityManager em;

    //create a new User
    public void createUser(Long id){
        User u = new User(id);
        em.merge(u);
    }

    //delete User by his username
    public void deleteUser(Long id){
        em.createNamedQuery("ShoppingCart.deleteProductsFromUser").setParameter("id", id).executeUpdate();
        em.createNamedQuery("User.deleteById").setParameter("id", id).executeUpdate();
    }
}