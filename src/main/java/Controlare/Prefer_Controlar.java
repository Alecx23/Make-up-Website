package Controlare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Services.PreferateService;
import Services.ProduseService;
import Services.PersoanaService;
import obiecte.Persoana;
import obiecte.Preferate;
import obiecte.Produs;

@Controller
public class Prefer_Controlar {

	@Autowired
	PreferateService preferateService;
	
	@Autowired
	PersoanaService persoaneService;
	
	@Autowired
	ProduseService produseService;
	
	@PostMapping("/preferate/add")
	public String adaugaPreferate(@RequestParam Long productId) {
		
		Produs produs = produseService.getProduseRepository().findById(productId).orElse(null);
		Persoana persoana = persoaneService.getPersoaneRepository().
				findById(LogIn_SignIn_Controlar.cont.getId()).orElse(null);
		
		if(persoana==null || produs==null)
			return "redirect:/";
		
		Preferate persPref = persoana.getPref();
		
		if(!persPref.getProdus().contains(produs)) {
			persPref.getProdus().add(produs);
			preferateService.addPreferate(persPref);
		}
		
		return "redirect:/product/" +productId.toString();
	}
	
	@PostMapping("/preferate/remove")
	public String scoatePref(@RequestParam Long productId) {
		
		Produs produs = produseService.getProduseRepository().findById(productId).orElse(null);
		Persoana persoana = persoaneService.getPersoaneRepository().
				findById(LogIn_SignIn_Controlar.cont.getId()).orElse(null);
		
		if(persoana==null || produs==null)
			return "redirect:/";
		
		Preferate persPref = persoana.getPref();
		
		if(persPref.getProdus().contains(produs)) {
			persPref.getProdus().remove(produs);
			preferateService.addPreferate(persPref);
		}
		
		return "redirect:/product/" +productId.toString();
	}
	
	@GetMapping("/preferate")
	public String preferate(Model model) {
		
		Persoana persoana = persoaneService.getPersoaneRepository().findById(LogIn_SignIn_Controlar.cont.getId()).orElse(null);
		Preferate persPref = persoana.getPref();
		model.addAttribute("Preferate",persPref.getProdus());
		model.addAttribute("utilizator",persoana);
		return "favorite";
	}
}
