package edu.grocery.filler;

import java.time.LocalDate;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

import edu.grocery.irepositories.BillProductRepository;
import edu.grocery.irepositories.BillRepository;
import edu.grocery.irepositories.ClientRepository;
import edu.grocery.irepositories.ProductRepository;
import edu.grocery.pojo.Bill;
import edu.grocery.pojo.BillProduct;
import edu.grocery.pojo.Client;
import edu.grocery.pojo.Product;

@Configuration
public class DBFiller {
	
	long DNI = 1000;
	int QUANTITY = 50;
	
	@Bean
	public CommandLineRunner initDB(ProductRepository products, ClientRepository clients, BillRepository bills, BillProductRepository billProducts) {
		return args ->{
			//Se insertan 10 productos
			IntStream.range(0, 10).forEach(i -> {
				Product p = new Product("P"+i, 100);
				products.save(p);
			});
			//Se insertan 10 clientes
			IntStream.range(0, 10).forEach(i -> {
				Client c = new Client(DNI, "C"+i, "LasName");
				clients.save(c);
				DNI = DNI + 100;
			});
			DNI = 1000;
			//Se insertan 10 facturas
			IntStream.range(0, 10).forEach(i -> {
				Bill b = new Bill (clients.getById(DNI));
				bills.save(b);
				DNI += 100;
			});
			//Se insertan 10 facturaProducto
			IntStream.range(0, 10).forEach(i -> {
				long id = i + 1; 
				LocalDate d = LocalDate.of(2020, 1, 8);
				BillProduct bp = new BillProduct(products.getById(id), bills.getById(id), d, QUANTITY);
				billProducts.save(bp);
				QUANTITY+=20;
			});
		};
	}
}