package jana60.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jana60.model.Pizza;
import jana60.repository.PizzaRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class RestPizzaController {
	
	@Autowired
	private PizzaRepository repo;
	
	
	@GetMapping
	public List<Pizza> getPiazzas(){
		return (List<Pizza>)repo.findAll();	
	}
	
	@GetMapping("/{id}")
	public Pizza getPizzasById(@PathVariable Integer id) {
		Optional <Pizza> result = repo.findById(id);
		
		if(result.isPresent()) {
			return result.get();
		}else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza not found");
		}
	}
}
