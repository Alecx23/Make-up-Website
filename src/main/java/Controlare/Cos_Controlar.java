package Controlare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Services.CosService;
import Services.ProduseService;
import Services.PersoanaService;
import obiecte.Cos;
import obiecte.Persoana;
import obiecte.Produs;

@Controller
public class Cos_Controlar {

	@Autowired
	private ProduseService produseService;
	
	@Autowired
	private PersoanaService persoaneService;
	
	@Autowired
	private CosService cosService;
	
	@PostMapping("/addCos")
	public String addToCos(@RequestParam Long produsId) {
		
		Produs produs = produseService.getProduseRepository().findById(produsId).orElse(null);
		Persoana persoana = persoaneService.getPersoaneRepository().findById(LogIn_SignIn_Controlar.cont.getId()).orElse(null);
		
		if(produs==null||persoana==null) {
			return "redirect:/";
		}
		
		Cos persCos = persoana.getCos();
		
		persCos.addCos(produs);
		cosService.addCos(persCos);
		
		return "redirect:/product/"+produsId.toString();
	}
	
	@PostMapping("removeCos")
	public String removeToCos(@RequestParam Long produsId) {
		
		Produs produs = produseService.getProduseRepository().findById(produsId).orElse(null);
		Persoana persoana = persoaneService.getPersoaneRepository().findById(LogIn_SignIn_Controlar.cont.getId()).orElse(null);
		
		if(produs==null||persoana==null) {
			return "redirect:/";
		}
		
		Cos persCos = persoana.getCos();
		
		persCos.stergereCos(produs);
		cosService.addCos(persCos);
		
		return "redirect:/Cos";
	}
	
	@GetMapping("/Cos")
	public String Cos(Model model) {
		
		Persoana persoana = persoaneService.getPersoaneRepository().findById(LogIn_SignIn_Controlar.cont.getId()).orElse(null);
		Cos persCos = persoana.getCos();
		
		model.addAttribute("CosId",persCos.getId());
		model.addAttribute("ProduseCos",persCos.getCos());
		model.addAttribute("utilizator",persoana);
		if(persCos.totalProdCos()!=0)
			model.addAttribute("titlu","Cos("+persCos.totalProdCos().toString()+")");
		else model.addAttribute("titlu","Cos");
		
		return "cos";
	}
}
