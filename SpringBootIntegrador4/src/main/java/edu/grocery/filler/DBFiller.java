package edu.grocery.filler;

import java.util.Date;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

import edu.grocery.irepositories.BillProductRepository;
import edu.grocery.irepositories.ClientRepository;
import edu.grocery.irepositories.ProductRepository;
import edu.grocery.pojo.Bill;
import edu.grocery.pojo.BillProduct;
import edu.grocery.pojo.Client;
import edu.grocery.pojo.Product;

@Configuration
public class DBFiller {
	
	long DNI = 1000;
	
	@Bean
	public CommandLineRunner initDB(ProductRepository products, ClientRepository clients, BillProductRepository bills) {
		return args ->{
			IntStream.range(0, 10).forEach(i -> {
				Product p = new Product("P"+i, 100);
				products.save(p);
			});
			IntStream.range(0, 10).forEach(i -> {
				Client c = new Client(DNI, "C"+i, "LasName", null);
				clients.save(c);
				DNI = DNI + 100;
			});
			/*
			long i = 1;
			int quantityp = 5;
			Bill b = new Bill (clients.getById(DNI));
			Date d = new Date(2014, 02, 11);
			BillProduct bp = new BillProduct(products.getById((long)i), b, d, quantityp);
			bills.save(bp);*/
		};
	}
}