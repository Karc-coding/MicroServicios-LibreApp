package com.libreapp.store.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.libreapp.store.product.bean.Autor;
import com.libreapp.store.product.service.AutorService;

@RestController
@RequestMapping("/author")
@CrossOrigin(origins = "http://localhost:4200")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getAuthor(@PathVariable("id") Long id) {

		Map<String, Object> result = new HashMap<>();

		try {
			Autor autor = autorService.getAutor(id);
			if (autor == null) {
				result.put("message", "No existe el autor con el id " + id);
			} else {
				result.put("data", autor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/listAll")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listAllAuthor() {

		Map<String, Object> result = new HashMap<>();

		try {
			List<Autor> list = autorService.listAll();
			if (CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				result.put("list", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

}
