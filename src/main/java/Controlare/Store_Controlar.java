package Controlare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Services.ProductService;
import Services.UtilizatorService;
import obiecte.Persoane;
import obiecte.Produs;

@Controller
public class Store_Controlar {

	boolean q=false;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UtilizatorService utilizatorService;
	
	@GetMapping("/home")
	public String homepage(Model model) {
		if(q==false) {
			productService.addProduct(new Produs("Crema de fata",15,16,"/produs.png"));
			productService.addProduct(new Produs("Crema de fata",15,16,"/produs.png"));
			productService.addProduct(new Produs("Crema de fata",15,16,"/produs.png"));
			utilizatorService.addUtilizator(new Persoane("admin","admin"));
			utilizatorService.addUtilizator(new Persoane("user","user"));
			LogIn_SignIn_Controlar.account.setId(utilizatorService.getUtilizatorRepository().findByEmail("user").getId());
			q=true;
		}
		model.addAttribute("Produse",productService.getAllProducts());
		model.addAttribute("utilizator",LogIn_SignIn_Controlar.account);
		return "homepage";
	}
}
