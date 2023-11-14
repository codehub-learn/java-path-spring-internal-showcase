package gr.codelearn.spring.showcase.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
	private final int id;
	private String code;
	private String name;
}
