package ma.enset.Store;

import ma.enset.Store.entities.Location;
import ma.enset.Store.entities.Product;
import ma.enset.Store.entities.ProductLocation;
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

	/*@Bean
	ServletRegistrationBean h2ServletRegistration(){
		ServletRegistrationBean registrationBean=
				new ServletRegistrationBean(new WebdavServlet());
		registrationBean.addUrlMappings("/h2-console/*");
		return registrationBean;
	}*/
	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository,LocationRepository locationRepository,ProductLocationRepository productLocationRepository){
		Product p1=new Product("A125F","Table","Table à manger de boi",4000F);
		p1.setProductImage("oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		Product p2=new Product("52F23","Canapé","Canapé blanc pour 3 person",6500F);
		p2.setProductImage("oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		Product p3=new Product("52DF6","Tapi","tapi gray",3400F);
		p3.setProductImage("oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		/*Product p4=new Product("SD2D5","Table","Table à manger de boi",5600F);
		p4.setProductImage("/home/fati/java-workspace/e-commerce-storage/target/classes/static/img/oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		Product p5=new Product("A135F","Table","Table à manger de boi",4000F);
		p5.setProductImage("/home/fati/java-workspace/e-commerce-storage/target/classes/static/img/oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		Product p6=new Product("54F22","Canapé","Canapé blanc pour 3 person",6500F);
		p6.setProductImage("/home/fati/java-workspace/e-commerce-storage/target/classes/static/img/oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		Product p7=new Product("52DF7","Tapi","tapi gray",3400F);
		p7.setProductImage("/home/fati/java-workspace/e-commerce-storage/target/classes/static/img/oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		Product p8=new Product("SD2D4","Table","Table à manger de boi",5600F);
		p8.setProductImage("/home/fati/java-workspace/e-commerce-storage/target/classes/static/img/oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		Product p9=new Product("A155F","Table","Table à manger de boi",4000F);
		p9.setProductImage("/home/fati/java-workspace/e-commerce-storage/target/classes/static/img/oriol-pascual-wC7yYoTA9FM-unsplash (1).jpg");
		Product p10=new Product("56F24","Canapé","Canapé blanc pour 3 person",6500F);
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
			/*productRepository.save(p4);
			productRepository.save(p5);
			productRepository.save(p6);
			productRepository.save(p7);
			productRepository.save(p8);
			productRepository.save(p9);
			productRepository.save(p10);
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
