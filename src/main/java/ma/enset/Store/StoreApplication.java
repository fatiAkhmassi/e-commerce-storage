package ma.enset.Store;

import ma.enset.Store.entities.Categorie;
import ma.enset.Store.entities.Location;
import ma.enset.Store.entities.Product;
import ma.enset.Store.entities.ProductLocation;
import ma.enset.Store.repositories.CategorieRepository;
import ma.enset.Store.repositories.LocationRepository;
import ma.enset.Store.repositories.ProductLocationRepository;
import ma.enset.Store.repositories.ProductRepository;
import org.apache.catalina.Store;
import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.validation.Valid;
import java.util.Date;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CategorieRepository categorieRepository,ProductRepository productRepository,LocationRepository locationRepository,ProductLocationRepository productLocationRepository){
		Categorie c1=new Categorie("Tables","Table a manger,coffe,corner");
		c1.setCategorieImage("lr.jpg");
		Categorie c2=new Categorie("Canape","Canape for the living room");
		c2.setCategorieImage("canape.jpg");
		Categorie c3=new Categorie("Decoration","Decorations for all the rooms");
		c3.setCategorieImage("deco.jpg");
		Categorie c4=new Categorie("Path Room","Path Room Accesories & Decorations");
		c4.setCategorieImage("br.jpg");
		Categorie c5=new Categorie("Bed Room","Bed Room Fournitures");
		c5.setCategorieImage("bedr.jpg");
		Categorie c6=new Categorie("Kitchen","Kitchen suplies & Tools");
		c6.setCategorieImage("kitchen.jpg");

		categorieRepository.save(c1);
		categorieRepository.save(c2);
		categorieRepository.save(c3);
		categorieRepository.save(c4);
		categorieRepository.save(c5);
		categorieRepository.save(c6);


		Product p1=new Product("A125F","Table","Table à manger de boi",4000F,c1);
		p1.setProductImage("table.jpg");
		Product p2=new Product("52F23","Canapé","Canapé blanc pour 3 person",6500F,c1);
		p2.setProductImage("pic2.jpg");
		Product p3=new Product("52DF6","Tapi","tapi gray",3400F,c2);
		p3.setProductImage("pic3.jpg");
		Product p4=new Product("SD2D5","Table","Table à manger de boi",5600F,c2);
		p4.setProductImage("pic4.jpg");
		Product p5=new Product("A135F","Table","Table à manger de boi",4000F,c3);
		p5.setProductImage("pic5.jpg");
		Product p6=new Product("54F22","Canapé","Canapé blanc pour 3 person",6500F,c3);
		p6.setProductImage("pic6.jpg");
		Product p7=new Product("52DF7","Tapi","tapi gray",3400F,c4);
		p7.setProductImage("pic7.jpg");
		Product p8=new Product("SD2D4","Table","Table à manger de boi",5600F,c5);
		p8.setProductImage("pic.jpg");
		Product p9=new Product("A155F","Table","Table à manger de boi",4000F,c6);
		p9.setProductImage("pic1.jpg");
		/*Product p10=new Product("56F24","Canapé","Canapé blanc pour 3 person",6500F);
		p10.setProductImage("/home/fati/java-workspace/e-commerce-storage/target/classes/static/img/oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		Product p11=new Product("52DF9","Tapi","tapi gray",3400F);
		p11.setProductImage("/home/fati/java-workspace/e-commerce-storage/target/classes/static/img/oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		Product p12=new Product("SD2D6","Table","Table à manger de boi",5600F);
		p12.setProductImage("/home/fati/java-workspace/e-commerce-storage/target/classes/static/img/oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");*/

		Location l1=new Location("Mhamid Marrakech Maroc","Mhamid","Marrakech","Maroc");
		Location l2=new Location("Massira Marrakech Maroc","Massira","Marrakech","Maroc");
		Location l3=new Location("Mazar Marrakech Maroc","Mazar","Marrakech","Maroc");
		Location l4=new Location("Azli Marrakech Maroc","Azli","Marrakech","Maroc");
		Location l5=new Location("Ain dyab Casablanca Maroc","Ain dyab","Casablanca","Maroc");
		Location l6=new Location("Alfa Casablanca Maroc","Alfa","Casablanca","Maroc");
		Location l7=new Location("Ouaziz Casablanca Maroc","Ouaziz","Casablanca","Maroc");
		Location l8=new Location("Lisasfa Casablanca Maroc","Lisasfa","Casablanca","Maroc");


		ProductLocation pl1=new ProductLocation(p1,l2,150F,new Date());
		ProductLocation pl2=new ProductLocation(p2,l2,320F,new Date());
		ProductLocation pl3=new ProductLocation(p2,l6,410F,new Date());
		ProductLocation pl4=new ProductLocation(p2,l5,20F,new Date());




		return args -> {

			productRepository.save(p1);
			productRepository.save(p2);
			productRepository.save(p3);
			productRepository.save(p4);
			productRepository.save(p5);
			productRepository.save(p6);
			productRepository.save(p7);
			productRepository.save(p8);
			productRepository.save(p9);
			/*productRepository.save(p10);
			productRepository.save(p11);
			productRepository.save(p12);*/

			locationRepository.save(l1);
			locationRepository.save(l2);
			locationRepository.save(l3);
			locationRepository.save(l4);
			locationRepository.save(l5);
			locationRepository.save(l6);
			locationRepository.save(l7);
			locationRepository.save(l8);

			productLocationRepository.save(pl1);
			productLocationRepository.save(pl2);
			productLocationRepository.save(pl3);
			productLocationRepository.save(pl4);
		};
	}
}
