package gr.codelearn.spring.showcase.core.service;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.domain.Product;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productServiceImpl2")
@Order(1)
public class ProductServiceImpl2 extends BaseComponent implements ProductService {
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
