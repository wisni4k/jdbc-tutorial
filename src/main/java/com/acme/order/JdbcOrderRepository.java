package com.acme.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.acme.order.pizza.PizzaOrder;
import com.acme.order.pizza.PizzaType;

@Slf4j
@Repository
@Primary
public class JdbcOrderRepository implements OrderRepository {

	@Autowired
	private DataSource ds;

	@Override
	public String save(PizzaOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub

	}

	@Override
	public PizzaOrder get(String pizzaOrderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PizzaOrder> findAll() {

		List<PizzaOrder> pizzaOrder = new ArrayList<PizzaOrder>();
		Customer customer;
		PizzaOrder piOrder;
		try (Connection conn = ds.getConnection()) {
			final String SQL = "SELECT o.id as order_id,o.customer_id as customer_id,o.status,o.type,o.estimatedDeliveryTime, o.finishTime,c.name,c.email,c.address from order_t o,customer_t c where o.customer_id = c.id";

			try (PreparedStatement ps = conn.prepareStatement(SQL)) {
				
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next())
					{
						customer = new Customer(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("address"));
						System.out.println("My customer from db: " + customer.toString());
						String typee = rs.getString("type");
						piOrder = new PizzaOrder(customer, PizzaType.valueOf("type"));
						pizzaOrder.add(piOrder);
					}
				}

			} catch (SQLException e) {
				log.error("Error connection DB", e);
			}

		} catch (SQLException e) {
			log.error("Error connection DB", e);
		}

		return pizzaOrder;
	}

	@Override
	public List<PizzaOrder> findByOrderStatus(OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
