package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
@RequiredArgsConstructor
public class ProductServiceRunner extends BaseComponent implements CommandLineRunner {
	private final ProductService productService;

	@Override
	public void run(final String... args) throws Exception {
		logger.info("hello");
		logger.info("size of products: {}", productService.getProducts().size());
	}
}
