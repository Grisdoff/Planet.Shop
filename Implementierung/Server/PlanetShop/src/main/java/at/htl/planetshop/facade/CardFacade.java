package at.htl.planetshop.facade;

import at.htl.planetshop.entity.PlanetCard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CardFacade {

    @PersistenceContext
    EntityManager entityManager;

    public CardFacade() {
    }

    public List<PlanetCard> findAll(){
        return this.entityManager
                .createNamedQuery("findAll",PlanetCard.class)
                .getResultList();
    }

    public PlanetCard saveItem(PlanetCard card) {
        entityManager.persist(card);
        return card;
    }

    public PlanetCard findById(long id) {
        return this.entityManager.find(PlanetCard.class, id);
    }
}
