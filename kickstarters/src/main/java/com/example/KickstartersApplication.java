package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@SpringBootApplication
public class KickstartersApplication {

	public static void main(String[] args) {
		SpringApplication.run(KickstartersApplication.class, args);
	}
}

@RestController
class OrdinaController {

	@RequestMapping("/hello")
	public String hello() {
		return "hello!!!";
	}
}

@Entity
class Car {

	public Car() {
		// Why JPA why???
	}

	public Car(String brand, String type) {
		this.brand = brand;
		this.type = type;
	}

	@Id
	@GeneratedValue
	private long id;
	private String brand;
	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

@RepositoryRestResource
interface CarRepository extends JpaRepository<Car, Long> {

	List<Car> findByBrand(@Param("brand") String brand);
	List<Car> findByType(@Param("type") String type);
	List<Car> findByBrandAndType(@Param("brand") String brand, @Param("type") String type);
}
