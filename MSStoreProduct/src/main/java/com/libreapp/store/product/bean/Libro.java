package com.libreapp.store.product.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Libro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@NotEmpty(message = "El campo titulo no puede estar vacio")
	@Size(max = 125, message = "El campo titulo acepta un maximo de 125 caracteres")
	@Column(nullable = false, length = 125)
	private String title;

	@NotEmpty(message = "El campo serie no puede estar vacio")
	@Size(min = 7, max = 7, message = "El campo serie debe tener 7 caracteres")
	@Column(unique = true, nullable = false, length = 7)
	private String serie;

	@Size(min = 4, max = 4, message = "El campo año debe tener 4 caracteres")
	@Column(name = "year_book", nullable = true, length = 4)
	private String yearBook;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categories_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Categoria categoria;

	@Digits(integer = 7, fraction = 2, message = "El campo precio acepta un maximo de 7 numeros enteros y 2 decimales")
	@PositiveOrZero(message = "El campo precio debe tener numeros positivos")
	@Column(nullable = false, scale = 2, precision = 9)
	private double price;

	@Digits(integer = 9, fraction = 0, message = "El campo stock acepta un maximo de 9 numeros enteros")
	@PositiveOrZero(message = "El campo stock debe tener numeros positivos")
	@Column(nullable = false, precision = 9)
	private int stock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authors_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Autor autor;

	@Size(max = 10, message = "El campo estado acepta un maximo de 10 caracteres")
	@Column(nullable = false, length = 10)
	private String state;
	
}