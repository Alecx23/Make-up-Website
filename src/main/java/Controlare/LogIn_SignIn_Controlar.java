package Controlare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Services.UtilizatorService;
import functii.Logica;
import obiecte.Persoane;

@Controller
public class LogIn_SignIn_Controlar {

	protected static Persoane account = new Persoane("user","user");
	private boolean invalid=false, invalidEmail=false, invalidParola=false;
	@Autowired
	UtilizatorService utilizatorService;
	
	@GetMapping("/login")
	public String LogInForm(Model model) {
		model.addAttribute("utilizator", new Persoane("",""));
		model.addAttribute("invalid",invalid);
		if(invalid)
			invalid=false;
		return "LogIn_page";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute Persoane utilizator) {
		String email = utilizator.getEmail();
		String password = utilizator.getParola();
		
		if(utilizatorService.LogIn(email, password)!=null) {
			account = new Persoane(email,password);
			account.setId(utilizatorService.getUtilizatorRepository().findByEmailAndParola(email, password).getId());
			return "redirect:/";
		}
		invalid=true;
		return "redirect:/login";
	}
	
	@GetMapping("/signin")
	public String signInForm(Model model) {
		model.addAttribute("utilizator", new Persoane("",""));
		model.addAttribute("invalidEmail", invalidEmail);
		model.addAttribute("invalidParola",invalidParola);
		if(invalidEmail)
			invalidEmail=false;
		else invalidParola=false;
		return "SignIn_page";
	}
	
	@PostMapping("/signin")
	public String signIn(@ModelAttribute Persoane utilizator) {
		
		if(utilizatorService.verificareEmail(utilizator.getEmail())!=null) {
			invalidEmail=true;
			return "redirect:/signin";
		}
		else if(Logica.verificPar(utilizator.getParola())!=true) {
			invalidParola=true;
			return "redirect:/signin";
		}
		else {
			utilizatorService.addUtilizator(utilizator);
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logOut")
	public String logOut() {
		account.setParola("user");
		account.setEmail("user");
		account.setId(utilizatorService.getUtilizatorRepository().findByEmail("user").getId());
		return "redirect:/";
	}
	
}
