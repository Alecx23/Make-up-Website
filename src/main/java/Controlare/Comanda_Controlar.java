package Controlare;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Services.ComandaService;
import Services.CosService;
import Services.ProductService;
import Services.UtilizatorService;
import obiecte.Comanda;
import obiecte.Cos;
import obiecte.Form;
import obiecte.Persoane;
import obiecte.Produs;

@Controller
public class Comanda_Controlar {

	@Autowired
	private ProductService produsService;
	
	@Autowired
	private UtilizatorService utilizatorService;
	
	@Autowired
	private ComandaService comandaService;
	
	@Autowired
	private CosService cosService;
	
	@PostMapping("/Comanda")
	public String addComanda(@RequestParam Long CosId, Model model) {
		
		Cos utilCos = cosService.getCosRepository().findById(CosId).orElse(null);
		
		Comanda utilComanda = new Comanda();
		
		utilComanda.setPretT(0);
		utilComanda.setComanda(new HashMap<>(utilCos.getCos()));
		utilComanda.calculeazaPretT();
		
		model.addAttribute("Comanda",utilComanda.getComanda());
		model.addAttribute("PretT", utilComanda.getPretT());
		model.addAttribute("form", new Form());
		
		return "comanda";
	}
	
	@PostMapping("/buyComanda")
	public String buyComanda(@ModelAttribute Form form) {
		
		Persoane utilizator = utilizatorService.getUtilizatorRepository().findById(LogIn_SignIn_Controlar.account.getId()).orElse(null);
		Cos utilCos = cosService.getCosRepository().findById(utilizator.getCos().getId()).orElse(null);
		Comanda utilComanda= new Comanda();
		
		utilComanda.setPretT(0);
		utilComanda.setComanda(new HashMap<>(utilCos.getCos()));
		utilComanda.calculeazaPretT();
		utilComanda.setUtilizator(utilizator);
		utilComanda.puneDate(form.getAdresa(), form.getPhoneNumber(), form.getEmail(), form.getCodPostal(), form.getOras(), form.getProvincie(), form.getNume(), form.getPrenume());
		
		for(Produs e : utilComanda.getComanda().keySet()) {
			e.setStoc(e.getStoc()-utilComanda.getComanda().get(e));
			produsService.addProduct(e);
		}
		
		Cos cos = utilizator.getCos();
		cos.getCos().clear();
		cosService.addCos(cos);
		utilizator.getComenzi().add(utilComanda);
		comandaService.addComanda(utilComanda);
		
		return "cumparat";
	}
}
