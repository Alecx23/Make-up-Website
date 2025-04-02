package Controlare;

//import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Services.ProductService;
import Services.UtilizatorService;
import obiecte.Persoane;
import obiecte.Produs;

@Controller
public class Produse_Controlar {

	@Autowired
	ProductService productService;
	
	@Autowired
	UtilizatorService utilizatorService;
	
	/*private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir")+
			File.separator+"src"+File.separator+"main"+File.separator+"resources"+
			File.separator+"static"+File.separator+"uploads";*/
	
	@GetMapping("/product/{id}")
	public String productDetails(@PathVariable Long id, Model model) {
		
		if(id==null) {
			return "redirect:/";
		}
		
		Produs produs = productService.getProductRepository().findById(id).orElse(null);
		Persoane utilizator = utilizatorService.getUtilizatorRepository().
				findById(LogIn_SignIn_Controlar.account.getId()).orElse(null);
		
		boolean estePreferat=false;
		if(utilizator!=null) {
			estePreferat=utilizator.getPref().getProdus().contains(produs);
		}
		
		model.addAttribute("produs", produs);
		model.addAttribute("estePreferat",estePreferat);
		model.addAttribute("utilizator", utilizator);
		model.addAttribute("cantitate", utilizator.getCos().getCos().get(produs));
		
		return "Product_page";
	}
}
