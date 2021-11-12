package com.libreapp.store.sale.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "sp_numberInvoice",
			procedureName = "SP_NUMBER_INVOICE",
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_invoice", type = String.class)
			})
})
@Data
@Entity
@Table(name = "book_sales")
public class BookSale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 11, max = 11, message = "El campo numero de factura debe tener 11 caracteres")
	@Column(name = "number_invoice", unique = true, nullable = false, length = 11)
	private String numberInvoice;
	
	@Column(name = "description_sales", nullable = true)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_register", nullable = true)
	private Date dateRegister;
	
	@Size(min = 36, max = 36, message = "El campo usuario debe tener 36 caracteres")
	@Column(name = "users_id", nullable = false, length = 36)
	private String user;
	
	@Valid
	@NotEmpty(message = "El detalle no puede estar vacio")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "book_sales_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<BookSaleDetails> bookSaleDetails;
	
	@Size(max = 10, message = "El campo estado acepta un maximo de 10 caracteres")
	@Column(nullable = false, length = 10)
	private String state;	
	
}
