package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.component.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnClass(name = "gr.codelearn.spring.showcase.core.component.DataSource")
public class DatabaseRunner extends BaseComponent implements CommandLineRunner {

	private final DataSource dataSource;

	@Override
	public void run(final String... args) throws Exception {
		logger.info("DataSource url: {}", dataSource.getUrl());
	}
}
