package Controlare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Services.CosService;
import Services.ProductService;
import Services.UtilizatorService;
import obiecte.Cos;
import obiecte.Persoane;
import obiecte.Produs;

@Controller
public class Cos_Controlar {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UtilizatorService utilizatorService;
	
	@Autowired
	private CosService cosService;
	
	@PostMapping("/addCos")
	public String addToCos(@RequestParam Long produsId) {
		
		Produs produs = productService.getProductRepository().findById(produsId).orElse(null);
		Persoane utilizator = utilizatorService.getUtilizatorRepository().findById(LogIn_SignIn_Controlar.account.getId()).orElse(null);
		
		if(produs==null||utilizator==null) {
			return "redirect:/";
		}
		
		Cos utilCos = utilizator.getCos();
		
		utilCos.addCos(produs);
		cosService.addCos(utilCos);
		
		return "redirect:/product/"+produsId.toString();
	}
	
	@PostMapping("removeCos")
	public String removeToCos(@RequestParam Long produsId) {
		
		Produs produs = productService.getProductRepository().findById(produsId).orElse(null);
		Persoane utilizator = utilizatorService.getUtilizatorRepository().findById(LogIn_SignIn_Controlar.account.getId()).orElse(null);
		
		if(produs==null||utilizator==null) {
			return "redirect:/";
		}
		
		Cos utilCos = utilizator.getCos();
		
		utilCos.stergereCos(produs);
		cosService.addCos(utilCos);
		
		return "redirect:/Cos";
	}
	
	@GetMapping("/Cos")
	public String Cos(Model model) {
		
		Persoane utilizator = utilizatorService.getUtilizatorRepository().findById(LogIn_SignIn_Controlar.account.getId()).orElse(null);
		Cos utilCos = utilizator.getCos();
		
		model.addAttribute("CosId",utilCos.getId());
		model.addAttribute("ProduseCos",utilCos.getCos());
		model.addAttribute("utilizator",utilizator);
		if(utilCos.totalProdCos()!=0)
			model.addAttribute("titlu","Cos("+utilCos.totalProdCos().toString()+")");
		else model.addAttribute("titlu","Cos");
		
		return "cos";
	}
}
