package gr.codelearn.spring.showcase.core.service;

import gr.codelearn.spring.showcase.core.domain.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("productServiceImpl")
//@Primary
public class ProductServiceImpl implements ProductService{

	public List<Product> getProducts(){
		return List.of(
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build(),
				Product.builder().build()
					  );
	}
}
