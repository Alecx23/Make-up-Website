package Controlare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Services.PersoanaService;
import functii.Logica;
import obiecte.Persoana;

@Controller
public class LogIn_SignIn_Controlar {

	protected static Persoana cont = new Persoana("user","user");
	private boolean invalid=false, invalidNume=false, invalidParola=false;
	
	@Autowired
	PersoanaService persoaneService;
	
	@GetMapping("/login")
	public String LogInForm(Model model) {
		model.addAttribute("utilizator", new Persoana("",""));
		model.addAttribute("invalid",invalid);
		if(invalid)
			invalid=false;
		return "LogIn_page";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute Persoana persoana) {
		String nume = persoana.getNume();
		String parola = persoana.getParola();
		
		if(persoaneService.LogIn(nume, parola)!=null) {
			cont = persoaneService.getPersoaneRepository().findByNumeAndParola(nume, parola);
			return "redirect:/";
		}
		invalid=true;
		return "redirect:/login";
	}
	
	@GetMapping("/signin")
	public String signInForm(Model model) {
		model.addAttribute("utilizator", new Persoana("",""));
		model.addAttribute("invalidEmail", invalidNume);
		model.addAttribute("invalidParola",invalidParola);
		if(invalidNume)
			invalidNume=false;
		else invalidParola=false;
		return "SignIn_page";
	}
	
	@PostMapping("/signin")
	public String signIn(@ModelAttribute Persoana persoana) {
		
		if(persoaneService.verificareNume(persoana.getNume())!=null) {
			invalidNume=true;
			return "redirect:/signin";
		}
		else if(Logica.verificPar(persoana.getParola())!=true) {
			invalidParola=true;
			return "redirect:/signin";
		}
		else {
			persoaneService.addPersoane(persoana);
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logOut")
	public String logOut() {
		cont.setParola("user");
		cont.setEmail("user");
		cont.setId(persoaneService.getPersoaneRepository().findByNume("user").getId());
		return "redirect:/";
	}
	
}
