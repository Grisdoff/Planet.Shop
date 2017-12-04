package at.htl.planetshop.logic;

import at.htl.planetshop.boundary.Request;
import at.htl.planetshop.facade.CardFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class ClientRequestHandler implements RequestHandler {
    @Inject
    @EJB
    CardFacade cardFacade;

    @Override
    public Object answerRequest(Request request) {
        switch (request.getRequestType()) {
            case GET_ALL_PRODUCTS:
                return cardFacade.findAll();
        }
        return null;
    }
}
