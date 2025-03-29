package Controlare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import Services.UtilizatorService;
import obiecte.Persoane;

@Controller
public class LogIn_SignIn_Controlar {

	protected static Persoane account = new Persoane("user","user");
	
	@Autowired
	UtilizatorService utilizatorService;
	
	
	
}
