package br.com.jonataslaet.productscrudapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.productscrudapi.controllers.dtos.ProductDTO;
import br.com.jonataslaet.productscrudapi.services.ProductService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
//	readAll(): Observable<any> {
//	    return this.httpClient.get(baseURL);
//	  }
	//GET /api/products?name=[keyword] find all products which name contains the passed keyword.
//	  searchByName(name): Observable<any> {
//	    return this.httpClient.get(`${baseURL}?name=${name}`);
//	  }
	@GetMapping
	public ResponseEntity<List<ProductDTO>> readAll(@RequestParam(required=false) String name){
		if (name != null) {
			return productService.searchByName(name);
		}
		return productService.readAll();
	}
	
//	  read(id): Observable<any> {
//	    return this.httpClient.get(`${baseURL}/${id}`);
//	  }
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> read(@PathVariable("id") Long id) throws ObjectNotFoundException{
		return productService.read(id); 
	}
	
	
//	  create(data): Observable<any> {
//	    return this.httpClient.post(baseURL, data);
//	  }
	@PostMapping
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO){
		return productService.create(productDTO); 
	}
	
//	  update(id, data): Observable<any> {
//	    return this.httpClient.put(`${baseURL}/${id}`, data);
//	  }
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) throws ObjectNotFoundException{
		return productService.update(id, productDTO); 
	}
	
//	  delete(id): Observable<any> {
//	    return this.httpClient.delete(`${baseURL}/${id}`);
//	  }
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws ObjectNotFoundException{
		return productService.delete(id); 
	}

//	  deleteAll(): Observable<any> {
//	    return this.httpClient.delete(baseURL);
//	  }
	@DeleteMapping
	public ResponseEntity<Void> deleteAll() throws ObjectNotFoundException{
		return productService.deleteAll(); 
	}
	
	
}
