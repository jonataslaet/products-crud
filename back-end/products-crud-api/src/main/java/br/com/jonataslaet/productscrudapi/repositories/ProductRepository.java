package br.com.jonataslaet.productscrudapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jonataslaet.productscrudapi.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("select p from Product p where p.name like %:nameP%")
	List<Product> searchByName(@Param("nameP") String name);

}
