package com.libreapp.store.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.libreapp.store.product.bean.Libro;
import com.libreapp.store.product.service.LibroService;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {

	@Autowired
	private LibroService libroService;

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getProduct(@PathVariable("id") String id) {

		Map<String, Object> result = new HashMap<>();

		try {
			Libro libro = libroService.getLibro(id);
			if (libro == null) {
				result.put("message", "No existe el producto con el id indicado");
			} else {
				result.put("data", libro);
				result.put("message", "Se ha encontrado un producto con el id indicado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/listAll")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listAllProducts() {

		Map<String, Object> result = new HashMap<>();

		try {
			List<Libro> list = libroService.listAll();
			if (CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				result.put("list", list);
				result.put("message", "Se encontro " + list.size() + " producto(s)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Libro book) {

		Map<String, Object> result = new HashMap<>();

		try {
			Libro libro = libroService.createLibro(book);
			if (libro == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				result.put("data", libro);
				result.put("message", "Se ha creado un producto");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable("id") String id, @RequestBody Libro book) {

		Map<String, Object> result = new HashMap<>();

		try {
			book.setId(id);
			Libro libro = libroService.updateLibro(book);
			if (libro == null) {
				result.put("message", "No existe el producto con el id indicado");
			} else {
				result.put("data", libro);
				result.put("message", "Se ha actualizado un producto");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable("id") String id) {

		Map<String, Object> result = new HashMap<>();

		try {
			Libro libro = libroService.deleteLibro(id);
			if (libro == null) {
				result.put("message", "No existe el producto con el id indicado");
			} else {
				result.put("data", libro);
				result.put("message", "Se ha eliminado un producto");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

//	---

	@GetMapping("/listAllActive")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listAllActiveProducts() {

		Map<String, Object> result = new HashMap<>();

		try {
			List<Libro> list = libroService.listAllActive();
			if (CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				result.put("list", list);
				result.put("message", "Se encontro " + list.size() + " producto(s)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

}
