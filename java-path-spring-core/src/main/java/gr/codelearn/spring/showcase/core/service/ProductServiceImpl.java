package gr.codelearn.spring.showcase.core.service;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.domain.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productServiceImpl")
@Order(1)
//@Primary
public class ProductServiceImpl extends BaseComponent implements ProductService{

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
