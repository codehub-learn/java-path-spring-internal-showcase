package gr.codelearn.spring.showcase.app.domain;

import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@formatter:off
@NamedNativeQuery(name = "Customer.purchasedMostExpensiveProduct",
		query =
		"SELECT C.FIRSTNAME || ' ' || C.LASTNAME as fullname, COUNT(*) as purchases " +
		"FROM ORDERS O, ORDER_ITEMS OI, CUSTOMERS C " +
		"WHERE OI.ORDER_ID = O.ID " +
		"AND O.CUSTOMER_ID = C.ID " +
		"AND OI.PRODUCT_ID = (SELECT TOP 1 ID FROM PRODUCTS ORDER BY PRICE DESC) " +
		"GROUP BY O.CUSTOMER_ID " +
		"ORDER BY purchases, c.lastname, c.firstname",
		resultSetMapping = "CustomersPurchasedMostExpensiveProduct")
@SqlResultSetMapping(name = "CustomersPurchasedMostExpensiveProduct",
		classes = @ConstructorResult(
				targetClass = KeyValue.class,
				columns = {
						@ColumnResult(name = "fullname", type = String.class),
						@ColumnResult(name = "purchases", type = Long.class)
				}
		)
)
//@formatter:on

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMERS", indexes = {@Index(columnList = "email")})
@SequenceGenerator(name = "idGenerator", sequenceName = "CUSTOMERS_SEQ", initialValue = 1, allocationSize = 1)
public class Customer extends BaseModel {
	@NotNull(message = "{email.null}")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "{email.format}")
	@Column(length = 50, nullable = false, unique = true)
	private String email;

	@NotNull(message = "{firstname.null}")
	@Column(length = 20, nullable = false)
	private String firstname;

	@NotNull(message = "{lastname.null}")
	@Column(length = 30, nullable = false)
	private String lastname;

	@Min(value = 12, message = "{age.min}")
	@Max(value = 120, message = "{age.max}")
	private Integer age;

	@Column(length = 50)
	@Size(max = 50, message = "{address.length}")
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private CustomerCategory customerCategory;
}
