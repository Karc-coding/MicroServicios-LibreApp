package com.libreapp.store.product.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "authors")
public class Autor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 50, message = "El campo nombre acepta un maximo de 50 caracteres")
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@Size(max = 50, message = "El campo apellido acepta un maximo de 50 caracteres")
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	@Size(max = 60, message = "El campo apodo acepta un maximo de 60 caracteres")
	@Column(nullable = false, length = 60)
	private String nickname;
	
}
