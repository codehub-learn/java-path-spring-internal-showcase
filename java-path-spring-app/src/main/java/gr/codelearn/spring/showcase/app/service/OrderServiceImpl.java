package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.domain.OrderItem;
import gr.codelearn.spring.showcase.app.domain.PaymentMethod;
import gr.codelearn.spring.showcase.app.domain.Product;
import gr.codelearn.spring.showcase.app.repository.OrderRepository;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.showcase.app.transfer.PurchasesPerCustomerCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
	private final OrderRepository orderRepository;

	@Override
	public JpaRepository<Order, Long> getRepository() {
		return orderRepository;
	}

	@Override
	public Order initiateOrder(Customer customer) {
		return Order.builder().customer(customer).build();
	}

	@Override
	public void addItem(Order order, Product product, int quantity) {
		if (checkNullability(order, product)) {
			return;
		}

		boolean increasedQuantity = false;

		// If product is already contained in the order, don't add it again, just increase the quantity accordingly
		for (OrderItem oi : order.getOrderItems()) {
			if (oi.getProduct().getSerial().equals(product.getSerial())) {
				oi.setQuantity(oi.getQuantity() + quantity);
				increasedQuantity = true;
				break;
			}
		}

		if (!increasedQuantity) {
			order.getOrderItems().add(newOrderItem(order, product, quantity));
			//order.add(newOrderItem(order, product, quantity));
		}

		logger.debug("Product[{}] added to Order[{}]", product, order);
	}

	@Override
	public void updateItem(Order order, Product product, int quantity) {
		if (checkNullability(order, product)) {
			return;
		}

		order.getOrderItems().removeIf(oi -> oi.getProduct().getSerial().equals(product.getSerial()));
		order.getOrderItems().add(newOrderItem(order, product, quantity));

		logger.debug("Product[{}] updated in Order[{}]", product, order);
	}

	@Override
	public void removeItem(Order order, Product product) {
		if (checkNullability(order, product)) {
			return;
		}

		order.getOrderItems().removeIf(oi -> oi.getProduct().getSerial().equals(product.getSerial()));
		logger.debug("Product[{}] removed from Order[{}]", product, order);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
				   rollbackFor = Exception.class)
	public Order checkout(Order order, PaymentMethod paymentMethod) {
		if (!validate(order)) {
			logger.warn("Order should have customer, order items, and payment type defined before being able to " +
						"checkout the order.");
			return null;
		}

		// Set all order fields with proper values
		order.setPaymentMethod(paymentMethod);
		order.setSubmitDate(new Date());
		order.setCost(giveDiscounts(order));

		return create(order);
	}

	@Override
	public Order getLazy(Long id) {
		return orderRepository.getLazy(id).orElseThrow(
				() -> new NoSuchElementException(String.format("There was no order found matching id %d.", id)));
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAllLazy();
	}

	private boolean checkNullability(Order order, Product product) {
		if (order == null) {
			logger.warn("Order is null.");
			return true;
		}
		if (product == null) {
			logger.warn("Product is null.");
			return true;
		}
		return false;
	}

	private boolean validate(Order order) {
		return order != null && !order.getOrderItems().isEmpty() && order.getCustomer() != null;
	}

	private OrderItem newOrderItem(Order order, Product product, int quantity) {
		return OrderItem.builder().product(product).order(order).quantity(quantity).price(product.getPrice()).build();
	}

	private BigDecimal giveDiscounts(Order order) {
		float totalDiscount =
				order.getCustomer().getCustomerCategory().getDiscount() + order.getPaymentMethod().getDiscount();

		// Calculate original order cost
		//@formatter:off
		BigDecimal originalCost = order.getOrderItems().stream()
				.map(oi -> oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		//@formatter:on

		// Apply discount
		BigDecimal finalCost = originalCost.multiply(BigDecimal.valueOf(1f - totalDiscount));

		logger.debug("Order[{}], originalCost: {}, totalDiscount: {}, finalCost: {}.", order.getId(), originalCost,
					 totalDiscount, finalCost);

		return finalCost;
	}

	@Override
	public List<PurchasesPerCustomerCategoryDto> findTotalNumberAndCostOfPurchasesPerCustomerCategory() {
		return orderRepository.findTotalNumberAndCostOfPurchasesPerCustomerCategory();
	}

	@Override
	public List<KeyValue<String, BigDecimal>> findAverageOrderCostPerCustomer() {
		return orderRepository.findAverageOrderCostPerCustomer();
	}
}
