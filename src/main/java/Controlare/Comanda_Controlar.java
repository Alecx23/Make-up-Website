package Controlare;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Services.ComandaService;
import Services.CosService;
import Services.ProduseService;
import Services.PersoanaService;
import obiecte.Comanda;
import obiecte.Cos;
import obiecte.Form;
import obiecte.Persoana;
import obiecte.Produs;

@Controller
public class Comanda_Controlar {

	@Autowired
	private ProduseService produseService;
	
	@Autowired
	private PersoanaService persoaneService;
	
	@Autowired
	private ComandaService comandaService;
	
	@Autowired
	private CosService cosService;
	
	@PostMapping("/Comanda")
	public String addComanda(@RequestParam Long CosId, Model model) {
		
		Cos persCos = cosService.getCosRepository().findById(CosId).orElse(null);
		
		Comanda persComanda = new Comanda();
		
		persComanda.setPretT(0);
		persComanda.setComanda(new HashMap<>(persCos.getCos()));
		persComanda.calculeazaPretT();
		
		model.addAttribute("Comanda",persComanda.getComanda());
		model.addAttribute("PretT", persComanda.getPretT());
		model.addAttribute("form", new Form());
		
		return "comanda";
	}
	
	@PostMapping("/buyComanda")
	public String buyComanda(@ModelAttribute Form form) {
		
		Persoana persoana = persoaneService.getPersoaneRepository().findById(LogIn_SignIn_Controlar.cont.getId()).orElse(null);
		Cos persCos = cosService.getCosRepository().findById(persoana.getCos().getId()).orElse(null);
		Comanda persComanda= new Comanda();
		
		persComanda.setPretT(0);
		persComanda.setComanda(new HashMap<>(persCos.getCos()));
		persComanda.calculeazaPretT();
		persComanda.setUtilizator(persoana);
		persComanda.puneDate(form.getAdresa(), form.getPhoneNumber(), form.getEmail(), form.getCodPostal(), form.getOras(), form.getProvincie(), form.getNume(), form.getPrenume());
		
		for(Produs e : persComanda.getComanda().keySet()) {
			e.setStoc(e.getStoc()-persComanda.getComanda().get(e));
			produseService.addProduse(e);
		}
		
		Cos cos = persoana.getCos();
		cos.getCos().clear();
		cosService.addCos(cos);
		persoana.getComenzi().add(persComanda);
		comandaService.addComanda(persComanda);
		
		return "cumparat";
	}
	
	@GetMapping("/Comenzi")
	public String arataComenzi(Model model) {
		
		Persoana persoana = persoaneService.getPersoaneRepository().findById(LogIn_SignIn_Controlar.cont.getId()).orElse(null);
		model.addAttribute("utilizator",persoana);
		model.addAttribute("comenzi", persoana.getComenzi());
		return "orders";
	}
	
	@GetMapping("/Comanda/{id}")
	public String arataComanda(@PathVariable Long id, Model model) {
		
		Persoana persoana = persoaneService.getPersoaneRepository().findById(LogIn_SignIn_Controlar.cont.getId()).orElse(null);
		Comanda comanda = comandaService.getComandaRepository().findById(id).orElse(null);
		
		model.addAttribute("utilizator",persoana);
		model.addAttribute("produseComanda",comanda.getComanda());
		model.addAttribute("idComanda",id);
		model.addAttribute("pretTotal", comanda.getPretT());
		return "produseComanda";
	}
	
	
}
