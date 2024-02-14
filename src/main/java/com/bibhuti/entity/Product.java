package com.bibhuti.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank(message="Name is mandatory")
	@Size(min=3, max=15, message="Name should have 3 to 15 characters")
	private String name;
	
	@NotNull(message="Price is mandatory")
	@Positive(message="Enter valid price")
	private Double price;
	
	@NotNull(message="Quantity is blank")
	@Positive(message="Enter valid quantity")
	private Integer qty;
}
