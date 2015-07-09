package com.acme.order.application;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acme.order.Customer;
import com.acme.order.JdbcOrderRepository;
import com.acme.order.OrderRepository;
import com.acme.order.pizza.PizzaOrderService;
import com.acme.order.pizza.PizzaType;

@Slf4j
@Configuration
@ComponentScan("com.acme.order")
public class SpringAnnotationBasedApplication {

	public static void main(String[] args) {
		log.info("Spring XML based application");

		ApplicationContext ctx = new ClassPathXmlApplicationContext("BeanConnectionPool.xml");
		
		OrderRepository orderRepository = ctx.getBean(OrderRepository.class);
		
		orderRepository.findAll();

	}
	
	
	

}
