<<<<<<< HEAD:Implementierung/Server/PlanetShop/src/main/java/at/htl/planetshop/bean/InitBean.java
package at.htl.planetshop.bean;

import at.htl.planetshop.business.ProductFacade;
import at.htl.planetshop.entities.Product;
=======
package at.htl.planetshop.initBean;

import at.htl.planetshop.entities.Product;
import at.htl.planetshop.facade.ProductFacade;
>>>>>>> 4d339bfb2ca6bc284680a2d481ecc965f8293c39:Implementierung/Server/PlanetShop/src/main/java/at/htl/planetshop/initBean/InitBean.java

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
<<<<<<< HEAD:Implementierung/Server/PlanetShop/src/main/java/at/htl/planetshop/bean/InitBean.java
        Product card = new Product(14,"Schuhe", Files.readAllBytes(Paths.get("C:\\Users\\Daniel\\Documents\\GitHub\\Planet.Shop\\Implementierung\\Server\\PlanetShop\\testpicture.jpg")), "Sehr schoen oida");
        Product card1 = new Product(57, "Tolles Kleid", Files.readAllBytes(Paths.get("C:\\Users\\Daniel\\Documents\\GitHub\\Planet.Shop\\Implementierung\\Server\\PlanetShop\\kleid.jpg")), "1x getragen");
=======
        Product card = new Product(14,"Schuhe", Files.readAllBytes(Paths.get("C:\\GitHub\\Planet.Shop\\Implementierung\\Server\\PlanetShop\\testpicture.jpg")));
        Product card1 = new Product(57, "Tolles Kleid", Files.readAllBytes(Paths.get("C:\\GitHub\\Planet.Shop\\Implementierung\\Server\\PlanetShop\\kleid.jpg")));
>>>>>>> 4d339bfb2ca6bc284680a2d481ecc965f8293c39:Implementierung/Server/PlanetShop/src/main/java/at/htl/planetshop/initBean/InitBean.java

        productFacade.saveItem(card);
        productFacade.saveItem(card1);
    }
<<<<<<< HEAD:Implementierung/Server/PlanetShop/src/main/java/at/htl/planetshop/bean/InitBean.java

}
=======
}
>>>>>>> 4d339bfb2ca6bc284680a2d481ecc965f8293c39:Implementierung/Server/PlanetShop/src/main/java/at/htl/planetshop/initBean/InitBean.java
