package filler;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

import edu.grocery.irepositories.ProductRepository;
import edu.grocery.pojo.Product;

@Configuration
public class DBFiller {

	@Bean
	public CommandLineRunner initDB(ProductRepository products) {
		return args ->{
			IntStream.range(0, 10).forEach(i -> {
				Product p = new Product("P "+i, 100);
				products.save(p);
			});
		};
	}
}