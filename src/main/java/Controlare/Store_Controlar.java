package Controlare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Services.ProduseService;
import Services.PersoanaService;
import obiecte.Persoana;
import obiecte.Produs;

@Controller
public class Store_Controlar {

	boolean q=false;
	
	@Autowired
	ProduseService produseService;
	
	@Autowired
	PersoanaService persoaneService;
	
	@GetMapping("/")
	public String homepage(Model model) {
		if(q==false) {
			produseService.addProduse(new Produs("Crema hidratanta",15,16,"/imagini/Picture3.png"));
			produseService.addProduse(new Produs("Spuma de curatare",15,0,"/imagini/Picture1.png"));
			produseService.addProduse(new Produs("Balsam de buze",15,3,"/imagini/Picture2.png"));
			persoaneService.addPersoane(new Persoana("admin","admin"));
			persoaneService.addPersoane(new Persoana("user","user"));
			LogIn_SignIn_Controlar.cont.setId(persoaneService.getPersoaneRepository().findByNume("user").getId());
			q=true;
		}
		model.addAttribute("Produse",produseService.getAllProduse());
		model.addAttribute("utilizator",LogIn_SignIn_Controlar.cont);
		return "homepage";
	}
}
