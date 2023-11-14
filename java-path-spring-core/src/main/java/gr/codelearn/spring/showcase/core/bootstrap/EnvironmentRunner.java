package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnvironmentRunner extends BaseComponent implements CommandLineRunner {

	private final Environment environment;

	@Override
	public void run(final String... args) throws Exception {
		logger.info("Profile(s) active: {}", environment.getActiveProfiles());
		logger.info("Environment property: {}", environment.getProperty("custom.test.hello"));
	}
}
