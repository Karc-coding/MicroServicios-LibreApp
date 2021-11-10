package com.libreapp.store.sale.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "book_sale_details")
public class BookSaleDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@PositiveOrZero(message = "El campo id de venta debe tener numeros positivos")
	@Column(name = "book_sales_id", nullable = true)
	private Long bookSalesId;
	
	@Size(min = 36, max = 36, message = "El campo usuario debe tener 36 caracteres")
	@Column(name = "books_id", nullable = false, length = 36)
	private String bookId;
	
	@Digits(integer = 7, fraction = 2, message = "El campo precio acepta un maximo de 7 numeros enteros y 2 decimales")
	@PositiveOrZero(message = "El campo precio debe tener numeros positivos")
	@Column(nullable = false, scale = 2, precision = 9)
	private double price;
	
	@Digits(integer = 9, fraction = 0, message = "El campo cantidad acepta un maximo de 9 numeros enteros")
	@PositiveOrZero(message = "El campo cantidad debe tener numeros positivos")
	@Column(nullable = false, precision = 9)
	private int amount;
	
	@Digits(integer = 7, fraction = 2, message = "El campo subtotal acepta un maximo de 7 numeros enteros y 2 decimales")
	@PositiveOrZero(message = "El campo subtotal debe tener numeros positivos")
	@Column(nullable = false, scale = 2, precision = 9)
	private double subtotal;

	public double getSubtotal() {
		return this.subTotal();
	}
	
	public double subTotal() {
		if (this.price > 0 && this.amount > 0) {
			return this.price * this.amount;
		} else {
			return 0;
		}
	}
	
}
