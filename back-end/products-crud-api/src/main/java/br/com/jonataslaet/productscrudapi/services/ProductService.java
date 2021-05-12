package br.com.jonataslaet.productscrudapi.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonataslaet.productscrudapi.controllers.dtos.ProductDTO;
import br.com.jonataslaet.productscrudapi.domain.Product;
import br.com.jonataslaet.productscrudapi.repositories.ProductRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductDTO findProduct(Long id) throws ObjectNotFoundException {
		Optional<Product> productOptional = productRepository.findById(id);
		if (!productOptional.isPresent()) {
			throw new ObjectNotFoundException("Product not found");
		}
		return new ProductDTO(productOptional.get());
	}

	public ResponseEntity<List<ProductDTO>> readAll() {
		List<Product> products = productRepository.findAll();
		return ResponseEntity.ok(ProductDTO.productsDTO(products));
	}

	public ResponseEntity<ProductDTO> read(Long id) throws ObjectNotFoundException {
		ProductDTO productDTO = findProduct(id);
		return ResponseEntity.ok(productDTO);
	}

	public ResponseEntity<ProductDTO> create(ProductDTO productDTO) {
		Product product = new Product(productDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
		productRepository.save(product);
		return ResponseEntity.created(uri).body(productDTO);
	}

	public ResponseEntity<ProductDTO> update(Long id, ProductDTO productDTO) throws ObjectNotFoundException {
		ProductDTO productDTOdoBanco = findProduct(id);
		productDTOdoBanco.getUpdateProductDTO(productDTO);
		productRepository.save(new Product(productDTOdoBanco));
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Void> delete(Long id) throws ObjectNotFoundException {
		ProductDTO productDTOdoBanco = findProduct(id);
		productRepository.deleteById(productDTOdoBanco.getId());
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Void> deleteAll() {
		productRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<List<ProductDTO>> searchByName(String name) {
		List<Product> products = productRepository.searchByName(name);
		return ResponseEntity.ok(ProductDTO.productsDTO(products));
	}
}
