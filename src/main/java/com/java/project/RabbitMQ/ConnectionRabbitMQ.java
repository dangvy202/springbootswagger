package com.java.project.RabbitMQ;


import org.springframework.stereotype.Component;

import com.java.project.RabbitMQ.constance.RabbitMQConstance;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Component
@Configuration
public class ConnectionRabbitMQ {
	private static final String NONE_EXCHANGE = "amq.direct";
	private AmqpAdmin AmqpAdmin;
	

	public ConnectionRabbitMQ(AmqpAdmin AmqpAdmin) {
		this.AmqpAdmin = AmqpAdmin;
	}
	
	private Queue fila(String noneFila) {
		return new Queue(noneFila,true,false,false);
	}
	
	private DirectExchange trocaDirect() {
		return new DirectExchange(NONE_EXCHANGE);
	}
	
	private Binding relacion(Queue fila, DirectExchange trocaDirect) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, trocaDirect.getName(), fila.getName(), null);
	}
	
	@PostConstruct
	private void adiciona() {
		Queue filaEstoque 	= this.fila(RabbitMQConstance.FILA_ESTOQUE);
		Queue filaPreco 	= this.fila(RabbitMQConstance.FILA_PROCO);
	
		DirectExchange trocaDirect 	= this.trocaDirect();
		Binding relacionEstoque	 	= this.relacion(filaEstoque, trocaDirect);
		Binding relacionPreco 		= this.relacion(filaPreco, trocaDirect);
		
		this.AmqpAdmin.declareQueue(filaEstoque);
		this.AmqpAdmin.declareQueue(filaPreco);
		this.AmqpAdmin.declareExchange(trocaDirect);
		this.AmqpAdmin.declareBinding(relacionEstoque);
		this.AmqpAdmin.declareBinding(relacionPreco);
	}
}
