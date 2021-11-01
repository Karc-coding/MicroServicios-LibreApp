package com.libreapp.store.customer.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@NotEmpty(message = "El campo dni no puede estar vacio")
	@Size(min = 8, max = 8, message = "El campo dni debe tener 8 caracteres")
	@Column(unique = true, nullable = false, length = 8)
	private String dni;
	
	@NotEmpty(message = "El campo nombre no puede estar vacio")
	@Size(max = 50, message = "El campo nombre acepta un maximo de 50 caracteres")
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@NotEmpty(message = "El campo apellido no puede estar vacio")
	@Size(max = 50, message = "El campo apellido acepta un maximo de 50 caracteres")
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	@NotEmpty(message = "El campo correo no puede estar vacio")
	@Email(message = "El formato del correo esta mal realizada")
	@Size(max = 100, message = "El campo correo acepta un maximo de 100 caracteres")
	@Column(nullable = false, length = 100)
	private String email;

	@Size(max = 20, message = "El campo telefono acepta un maximo de 20 caracteres")
	@Column(nullable = true, length = 20)
	private String phone;
	
	@Size(max = 100, message = "El campo direccion acepta un maximo de 100 caracteres")
	@Column(nullable = true, length = 100)
	private String address;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_birth", nullable = true)
	private Date dateBirth;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_register", nullable = true)
	private Date dateRegister;
	
	@NotNull(message = "El departamento no puede estar vacio")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departments_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Departamento departamento;
	
}
