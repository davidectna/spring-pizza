package jana60.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Ingredient;
import jana60.repository.IngredientRepository;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

	@Autowired
	private IngredientRepository repo;
	
	@GetMapping
	public String ingredientsList (Model model) {
		model.addAttribute("listOfIngredients", repo.findAll());
		model.addAttribute("newIngredient", new Ingredient());

		return "/ingredient/ingredients";
	}
	
	@PostMapping("/save")
	public String saveIngredients (@Valid @ModelAttribute ("newIngredient") Ingredient formIngredient,BindingResult br,Model model) {
		if(br.hasErrors()) {
		model.addAttribute("listOfIngredients", repo.findAll());
		return "/ingredient/ingredients";
		}else {
		model.addAttribute("listOfIngredients", repo.findAll());
		repo.save(formIngredient);
		return "redirect:/ingredient";
		}
	}
}
