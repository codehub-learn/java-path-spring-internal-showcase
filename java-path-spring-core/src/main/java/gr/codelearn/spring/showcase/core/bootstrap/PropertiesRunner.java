package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.config.GlobalProperties;
import gr.codelearn.spring.showcase.core.config.GlobalYamlProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropertiesRunner extends BaseComponent implements CommandLineRunner {

	private final GlobalYamlProperties globalYamlProperties;
	private final GlobalProperties globalProperties;

	@Override
	public void run(final String... args) throws Exception {
		logger.info("threadPool: {}", globalYamlProperties.getThreadPool());
		logger.info("email: {}", globalYamlProperties.getEmail());
		logger.info("servers: {}", globalYamlProperties.getServers());

		logger.info("global properties threadPool: {}", globalProperties.getThreadPool());
	}
}
