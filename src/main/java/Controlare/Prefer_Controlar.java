package Controlare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Services.PreferateService;
import Services.ProductService;
import Services.UtilizatorService;
import obiecte.Persoane;
import obiecte.Preferate;
import obiecte.Produs;

@Controller
public class Prefer_Controlar {

	@Autowired
	PreferateService preferateService;
	
	@Autowired
	UtilizatorService utilizatorService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/preferate/add")
	public String adaugaToPreferate(@RequestParam Long productId) {
		
		Produs produs = productService.getProductRepository().findById(productId).orElse(null);
		Persoane utilizator = utilizatorService.getUtilizatorRepository().
				findById(LogIn_SignIn_Controlar.account.getId()).orElse(null);
		
		if(utilizator==null || produs==null)
			return "redirect:/";
		
		Preferate utilPref = utilizator.getPref();
		
		if(!utilPref.getProdus().contains(produs)) {
			utilPref.getProdus().add(produs);
			preferateService.addPreferate(utilPref);
		}
		
		return "redirect:/product/" +productId.toString();
	}
	
	@PostMapping("/preferate/remove")
	public String scoatePref(@RequestParam Long productId) {
		
		Produs produs = productService.getProductRepository().findById(productId).orElse(null);
		Persoane utilizator = utilizatorService.getUtilizatorRepository().
				findById(LogIn_SignIn_Controlar.account.getId()).orElse(null);
		
		if(utilizator==null || produs==null)
			return "redirect:/";
		
		Preferate utilPref = utilizator.getPref();
		
		if(utilPref.getProdus().contains(produs)) {
			utilPref.getProdus().remove(produs);
			preferateService.addPreferate(utilPref);
		}
		
		return "redirect:/product/" +productId.toString();
	}
	
	@GetMapping("/preferate")
	public String preferate(Model model) {
		
		Persoane utilizator = utilizatorService.getUtilizatorRepository().findById(LogIn_SignIn_Controlar.account.getId()).orElse(null);
		Preferate utilPref = utilizator.getPref();
		model.addAttribute("Preferate",utilPref.getProdus());
		model.addAttribute("utilizator",utilizator);
		return "favorite";
	}
}
