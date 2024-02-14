package com.bibhuti.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bibhuti.entity.Product;
import com.bibhuti.repository.ProductRepository;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository repo;
	
	@GetMapping("/")
	public String loadForm(Model model) {
		model.addAttribute("product", new Product());
		return "index";
	}
	
	@PostMapping("/product")
	public String saveProduct(@Validated @ModelAttribute("product") Product p, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "index";
		}
		
		p = repo.save(p);
		
		if(p.getId() != null) {
			model.addAttribute("msg", "Product Saved");
		}
		
		return "index";
	}
	
	@GetMapping("/products")
	public String getProducts(Model model) {
		List<Product> list = repo.findAll();
		
		model.addAttribute("products", list);
		model.addAttribute("products", repo.findAll());
		
		return "data";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id, Model model) {
		repo.deleteById(id);
		model.addAttribute("msg", "Product Deleted");
		model.addAttribute("products", repo.findAll());
		return "data";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam("id") Integer id, Model model) {
			
		Optional<Product> byId = repo.findById(id);
		if(byId.isPresent()) {
			Product product = byId.get();
			model.addAttribute("product", product);
			return "index";
		}
		
		return "data";
	}
}
