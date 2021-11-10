package com.libreapp.store.sale.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libreapp.store.sale.bean.BookSale;
import com.libreapp.store.sale.repository.BookSaleRepository;

@Service
public class BookSaleServiceImpl implements BookSaleService {

	@Autowired
	private BookSaleRepository repo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<BookSale> listAll() {
		return (List<BookSale>) repo.findAll();
	}

	@Override
	public BookSale createBookSale(BookSale bookSale) {
		return repo.save(bookSale);
	}

	@Override
	public BookSale updateBookSale(BookSale bookSale) {
		BookSale bs = getBookSale(bookSale.getId());
		if (bs == null) {
			return null;
		}
		return repo.save(bookSale);
	}

	@Override
	public BookSale deleteBookSale(Long id) {
		BookSale bs = getBookSale(id);
		if (bs == null) {
			return null;
		}
		bs.setState("ELIMINADO");
		return repo.save(bs);
	}

	@Override
	public BookSale getBookSale(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<BookSale> listAllForState(String state) {
		return repo.findByState(state);
	}

	@Override
	public BookSale getBookSaleForNumberInvoice(String numberInvoice) {
		return repo.findByNumberInvoice(numberInvoice);
	}

	@Override
	public String generateNumberInvoice() {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_NUMBER_INVOICE");
		
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
		query.execute();
		String numberInvoice = (String) query.getOutputParameterValue(1);
		return numberInvoice;
	}

}
