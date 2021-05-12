package br.com.jonataslaet.productscrudapi.controllers.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jonataslaet.productscrudapi.domain.Product;

public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private Boolean available;

	public ProductDTO() {

	}

	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.available = product.getAvailable();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public static List<ProductDTO> productsDTO(List<Product> products) {
		return products.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}

	public ProductDTO getUpdateProductDTO(ProductDTO productDTO) {
		if (productDTO.getAvailable() != null) {
			this.available = productDTO.getAvailable();
		}
		if (productDTO.getDescription() != null) {
			this.description = productDTO.getDescription();
		}
		if (productDTO.getId() != null) {
			this.id = productDTO.getId();
		}

		if (productDTO.getName() != null) {
			this.name = productDTO.getName();
		}
		return this;
	}
}
