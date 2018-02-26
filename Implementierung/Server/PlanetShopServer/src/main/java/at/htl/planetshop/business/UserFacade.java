package at.htl.planetshop.business;

import at.htl.planetshop.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserFacade {
    @PersistenceContext
    EntityManager em;

    public void createUser(User u){
        em.persist(u);
    }

    public void deleteUser(User u){
        em.createNamedQuery("User.deleteById", User.class).setParameter("id", u.getId());
    }

    public boolean checkIfUserExists(User u){
        if(em.createNamedQuery("User.getWithUsernameAndPassword", User.class).setParameter("username", u.getUsername()).setParameter("password", u.getPassword()).getSingleResult() != null){
            return true;
        }
        return false;
    }


}