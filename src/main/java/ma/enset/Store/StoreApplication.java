package ma.enset.Store;

import ma.enset.Store.entities.Location;
import ma.enset.Store.entities.Product;
import ma.enset.Store.entities.ProductLocation;
import ma.enset.Store.repositories.LocationRepository;
import ma.enset.Store.repositories.ProductLocationRepository;
import ma.enset.Store.repositories.ProductRepository;
import org.apache.catalina.Store;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.validation.Valid;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository,LocationRepository locationRepository,ProductLocationRepository productLocationRepository){
		return args -> {
			productRepository.save(new Product(null,"A125F","Table","Table à manger de boi",4000F));
			productRepository.save(new Product(null,"52F23","Canapé","Canapé blanc pour 3 person",6500F));
			productRepository.save(new Product(null,"52DF6","Tapi","tapi gray",3400F));
			productRepository.save(new Product(null,"SD2D5","Table","Table à manger de boi",5600F));
			productRepository.save(new Product(null,"A135F","Table","Table à manger de boi",4000F));
			productRepository.save(new Product(null,"54F22","Canapé","Canapé blanc pour 3 person",6500F));
			productRepository.save(new Product(null,"52DF7","Tapi","tapi gray",3400F));
			productRepository.save(new Product(null,"SD2D4","Table","Table à manger de boi",5600F));
			productRepository.save(new Product(null,"A155F","Table","Table à manger de boi",4000F));
			productRepository.save(new Product(null,"56F24","Canapé","Canapé blanc pour 3 person",6500F));
			productRepository.save(new Product(null,"52DF9","Tapi","tapi gray",3400F));
			productRepository.save(new Product(null,"SD2D6","Table","Table à manger de boi",5600F));

			locationRepository.save(new Location(null,"Mhamid Marrakech Maroc","Mhamid","Marrakech","Maroc"));
			locationRepository.save(new Location(null,"Massira Marrakech Maroc","Massira","Marrakech","Maroc"));
			locationRepository.save(new Location(null,"Mazar Marrakech Maroc","Mazar","Marrakech","Maroc"));
			locationRepository.save(new Location(null,"Azli Marrakech Maroc","Azli","Marrakech","Maroc"));
			locationRepository.save(new Location(null,"Ain dyab Casablanca Maroc","Ain dyab","Casablanca","Maroc"));
			locationRepository.save(new Location(null,"Alfa Casablanca Maroc","Alfa","Casablanca","Maroc"));
			locationRepository.save(new Location(null,"Ouaziz Casablanca Maroc","Ouaziz","Casablanca","Maroc"));
			locationRepository.save(new Location(null,"Lisasfa Casablanca Maroc","Lisasfa","Casablanca","Maroc"));

			/*productLocationRepository.save(new ProductLocation(null,"A125F",1L,120F));
			productLocationRepository.save(new ProductLocation(null,"A125F",2L,350F));
			productLocationRepository.save(new ProductLocation(null,"52DF6",1L,410F));
			productLocationRepository.save(new ProductLocation(null,"52DF6",3L,270F));
			productLocationRepository.save(new ProductLocation(null,"52F21",2L,520F));
			productLocationRepository.save(new ProductLocation(null,"52F21",3L,105F));
			productLocationRepository.save(new ProductLocation(null,"SD2D5",1L,580F));
			productLocationRepository.save(new ProductLocation(null,"SD2D5",2L,510F));*/
		};
	}

	/*@Bean
	CommandLineRunner commandLineRunner(LocationRepository locationRepository){
		return args -> {
			locationRepository.save(new Location(null,"Mhamid Marrakech Maroc","Mhamid","Marrakech","Maroc"));
			locationRepository.save(new Location(null,"Massira Marrakech Maroc","Massira","Marrakech","Maroc"));
			locationRepository.save(new Location(null,"Mazar Marrakech Maroc","Mazar","Marrakech","Maroc"));
			locationRepository.save(new Location(null,"Azli Marrakech Maroc","Azli","Marrakech","Maroc"));
			locationRepository.save(new Location(null,"Ain dyab Marrakech Maroc","Ain dyab","Casablanca","Maroc"));
			locationRepository.save(new Location(null,"Alfa Casablanca Maroc","Alfa","Casablanca","Maroc"));
			locationRepository.save(new Location(null,"Ouaziz Marrakech Maroc","Ouaziz","Casablanca","Maroc"));
			locationRepository.save(new Location(null,"Lisasfa Marrakech Maroc","Lisasfa","Casablanca","Maroc"));
		};
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductLocationRepository productLocationRepository){
		return args -> {
			productLocationRepository.save(new ProductLocation(null,"A125F",1L,120F));
			productLocationRepository.save(new ProductLocation(null,"A125F",2L,350F));
			productLocationRepository.save(new ProductLocation(null,"52DF6",1L,410F));
			productLocationRepository.save(new ProductLocation(null,"52DF6",3L,270F));
			productLocationRepository.save(new ProductLocation(null,"52F21",2L,520F));
			productLocationRepository.save(new ProductLocation(null,"52F21",3L,105F));
			productLocationRepository.save(new ProductLocation(null,"SD2D5",1L,580F));
			productLocationRepository.save(new ProductLocation(null,"SD2D5",2L,510F));
		};
	}*/
}
