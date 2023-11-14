package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@DependsOn("productServiceImpl")
@Order(1)
public class ProductServiceRunner extends BaseComponent implements CommandLineRunner {
	private final ProductService productService;

	public ProductServiceRunner(@Qualifier("productServiceImpl2") final ProductService productService) {
		this.productService = productService;
	}

	@Override
	public void run(final String... args) throws Exception {
		logger.info("hello");
		logger.info("size of products: {}", productService.getProducts().size());
	}
}
