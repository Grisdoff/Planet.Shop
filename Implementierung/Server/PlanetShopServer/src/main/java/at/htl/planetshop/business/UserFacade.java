package at.htl.planetshop.business;

import at.htl.planetshop.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserFacade {
    @PersistenceContext
    EntityManager em;

    //create a new User
    public void createUser(String username, String password){
        User u = new User(username, password);
        em.merge(u);
    }

    //delete User by his username
    public void deleteUser(String username){
        em.createNamedQuery("User.deleteByUsername", User.class).setParameter("username", username);
    }

    //check if there exists a User with this username + password
    public boolean checkUserLogin(String username, String password){
        if(em.createNamedQuery("User.getByUsernameAndPassword", User.class).setParameter("username", username).setParameter("password", password).getSingleResult() != null){
            return true;
        }
        return false;
    }

    //check if there's any User with this username
    public boolean checkIfUsernameExists(String username) {
        if(em.createNamedQuery("User.getByUsername", User.class).setParameter("username", username).getSingleResult() != null){
            return true;
        }
        return false;
    }

    //get User by his username
    public User getUserWithUsername(String username) {
        return em.createNamedQuery("User.getByUsername", User.class).setParameter("username", username).getSingleResult();
    }

    //change the password from a user
    public boolean changePassword(String username, String password) {
        User u = getUserWithUsername(username);
        if(u == null) return false;
        u.setPassword(password);
        em.merge(u);
        return true;
    }
}