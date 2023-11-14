package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
@RequiredArgsConstructor
public class TestRunner extends BaseComponent implements CommandLineRunner {

	private final AnnotationConfigApplicationContext context;
	@Override
	public void run(final String... args) throws Exception {
		logger.info("context: {}", context.toString());
	}
}
