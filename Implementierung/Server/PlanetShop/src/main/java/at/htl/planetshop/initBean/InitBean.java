package at.htl.planetshop.initBean;
import at.htl.planetshop.entity.Product;
import at.htl.planetshop.facade.ProductFacade;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Startup
@Singleton
public class InitBean {

    @Inject
    private ProductFacade productFacade;

    @PostConstruct
    public void init() throws IOException {
        System.out.println();
        Product card = new Product(14,"Schuhe", Files.readAllBytes(Paths.get("C:\\Users\\Daniel\\Documents\\GitHub\\Planet.Shop\\Implementierung\\Server\\PlanetShop\\testpicture.jpg")), "Sehr schoen oida");
        Product card1 = new Product(57, "Tolles Kleid", Files.readAllBytes(Paths.get("C:\\Users\\Daniel\\Documents\\GitHub\\Planet.Shop\\Implementierung\\Server\\PlanetShop\\kleid.jpg")), "1x getragen");

        productFacade.saveItem(card);
        productFacade.saveItem(card1);
    }

}
