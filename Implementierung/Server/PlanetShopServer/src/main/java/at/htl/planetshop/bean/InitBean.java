package at.htl.planetshop.bean;

import at.htl.planetshop.business.ProductFacade;
import at.htl.planetshop.entities.Product;

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
        Product card = new Product(14,"Schuhe", Files.readAllBytes(Paths.get("/home/robin/Windows_Desktop/Schule/SYP/PlanettShopAktuell/Planet.Shop/Implementierung/Server/PlanetShopServer/testpicture.jpg")), "Sehr schoen oida");
        Product card1 = new Product(57, "Tolles Kleid", Files.readAllBytes(Paths.get("/home/robin/Windows_Desktop/Schule/SYP/PlanettShopAktuell/Planet.Shop/Implementierung/Server/PlanetShopServer/kleid.jpg")), "1x getragen");
        Product card2 = new Product(1337, "Neuwertiger Kebap", Files.readAllBytes(Paths.get("/home/robin/Windows_Desktop/Schule/SYP/PlanettShopAktuell/Planet.Shop/Implementierung/Server/PlanetShopServer/kebap.jpg")), "1x gegessen");

        productFacade.saveItem(card);
        productFacade.saveItem(card1);
        productFacade.saveItem(card2);
    }

}