package gr.codelearn.spring.showcase.core.service;

import gr.codelearn.spring.showcase.core.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("productServiceImpl2")
public class ProductServiceImpl2 implements ProductService {
	@Override
	public List<Product> getProducts() {
		return List.of(
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build()
					  );
	}
}
