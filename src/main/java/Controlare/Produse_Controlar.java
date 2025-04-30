package Controlare;

//import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Services.ProduseService;
import Services.PersoanaService;
import obiecte.Persoana;
import obiecte.Produs;

@Controller
public class Produse_Controlar {

	@Autowired
	ProduseService produseService;
	
	@Autowired
	PersoanaService persoaneService;
	
	@GetMapping("/product/{id}")
	public String productDetails(@PathVariable Long id, Model model) {
		
		if(id==null) {
			return "redirect:/";
		}
		
		Produs produs = produseService.getProduseRepository().findById(id).orElse(null);
		Persoana persoana = persoaneService.getPersoaneRepository().
				findById(LogIn_SignIn_Controlar.cont.getId()).orElse(null);
		
		boolean estePreferat=false;
		if(persoana!=null) {
			estePreferat=persoana.getPref().getProdus().contains(produs);
		}
		
		model.addAttribute("produs", produs);
		model.addAttribute("estePreferat",estePreferat);
		model.addAttribute("utilizator", persoana);
		model.addAttribute("cantitate", persoana.getCos().getCos().get(produs));
		
		return "Product_page";
	}
}
