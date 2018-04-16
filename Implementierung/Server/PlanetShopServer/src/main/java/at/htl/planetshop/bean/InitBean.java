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
        Product card = new Product(14,"Schnieke Schuhe", Files.readAllBytes(Paths.get("C:\\Users\\David\\Documents\\Planet.Shop\\Planet.Shop\\Implementierung\\Server\\PlanetShopServer\\Schnieke.jpg")), "Sehr schoen oida", 1L);
        Product card1 = new Product(57, "Tolles Kleid", Files.readAllBytes(Paths.get("C:\\Users\\David\\Documents\\Planet.Shop\\Planet.Shop\\Implementierung\\Server\\PlanetShopServer\\kleid.jpg")), "1x getragen", 1L);
        Product card2 = new Product(23.90, "Shimano Radschuhe", Files.readAllBytes(Paths.get("C:\\Users\\David\\Documents\\Planet.Shop\\Planet.Shop\\Implementierung\\Server\\PlanetShopServer\\Shimano-Schuh.jpg")),"Neuwertiger Radschuh, beste Qualität", 1L);
        Product card3 = new Product(55.89, "Motorrad Schuhe", Files.readAllBytes(Paths.get("C:\\Users\\David\\Documents\\Planet.Shop\\Planet.Shop\\Implementierung\\Server\\PlanetShopServer\\MotorradSchuh.jpg")), "Eine Saison getragen", 1L);
        Product card4 = new Product(33.90, "Kleid mit Choker Ausschnitt", Files.readAllBytes(Paths.get("C:\\Users\\David\\Documents\\Planet.Shop\\Planet.Shop\\Implementierung\\Server\\PlanetShopServer\\KleidChoker.jpg")), "Perfekt für die Disco", 10L);
        Product card5 = new Product(4444.77, "Compact Auto", Files.readAllBytes(Paths.get("C:\\Users\\David\\Documents\\Planet.Shop\\Planet.Shop\\Implementierung\\Server\\PlanetShopServer\\auto.png")), "Neuwertiger City-Flitzer", 1L);
        Product card6 = new Product(230.79, "Sony Playstation 4", Files.readAllBytes(Paths.get("C:\\Users\\David\\Documents\\Planet.Shop\\Planet.Shop\\Implementierung\\Server\\PlanetShopServer\\PS4.jpg")), "Gegen Aufpreis mit 2. Controller und The Last Of Us", 1L);
        Product card7 = new Product(200, "XBOX One", Files.readAllBytes(Paths.get("C:\\Users\\David\\Documents\\Planet.Shop\\Planet.Shop\\Implementierung\\Server\\PlanetShopServer\\xbox.jpg")),"Wie neu!", 1L);
        Product card8 = new Product(800.90, "Gaming PC + 144 Hz Monitor", Files.readAllBytes(Paths.get("C:\\Users\\David\\Documents\\Planet.Shop\\Planet.Shop\\Implementierung\\Server\\PlanetShopServer\\GamingPC.jpg")), "Intel Core i5 6500 3.6GHz GTX 970", 5L);

        productFacade.saveItem(card);
        productFacade.saveItem(card1);
        productFacade.saveItem(card2);
        productFacade.saveItem(card3);
        productFacade.saveItem(card4);
        productFacade.saveItem(card5);
        productFacade.saveItem(card6);
        productFacade.saveItem(card7);
        productFacade.saveItem(card8);
    }

}