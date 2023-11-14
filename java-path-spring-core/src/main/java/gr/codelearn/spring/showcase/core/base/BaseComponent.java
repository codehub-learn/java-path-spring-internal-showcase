package gr.codelearn.spring.showcase.core.base;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseComponent {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init(){
		logger.info("Created class: {}", this.getClass().getName());
	}
}
