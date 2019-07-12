package main.java.com.javaTask.service;

import java.util.List;

import main.java.com.javaTask.model.Product;
import main.java.com.javaTask.utilities.ProductTO;

public interface ProductService {

	public void insert(Product product);
	public List<Product> getAll();
	public List<ProductTO> getAllProductsByCartId(int id);
	public List<ProductTO> getProductsHistoryByTimeAndUserId(int userId, long from, long till);
	public Product getOneById(int id);
	public void update(Product product);
	public void delete(Product product);
}
