package functii;

public class Logica {

	public static boolean verificPar(String parola) {
		 boolean q=false;
		 if(parola.length()>=8) {
			 for(int i=0;i<parola.length()&&q==false;i++)
				 if('a'<=parola.charAt(i)&&parola.charAt(i)<='z')
					 q=true;
			 if(q==true) {
				 q=false;
				 for(int i=0;i<parola.length()&&q==false;i++)
					 if('A'<=parola.charAt(i)&&parola.charAt(i)<='Z')
						 q=true;
			 }
			 if(q==true) {
				 q=false;
				 for(int i=0;i<parola.length()&&q==false;i++)
					 if('0'<=parola.charAt(i)&&parola.charAt(i)<='9')
						 q=true;
			 }
			 if(q==true) {
				 q=false;
				 for(int i=0;i<parola.length()&&q==false;i++)
					 if(('!'<=parola.charAt(i) && parola.charAt(i)<='/') || ('{'<=parola.charAt(i) && parola.charAt(i)<='~'))
						 q=true;
					 else if((':'<=parola.charAt(i)&&parola.charAt(i)<='@') || ('['<=parola.charAt(i) && parola.charAt(i)<='`'))
						 q=true;
			 }
		 }
		 if(q==true)
			 return true;
		 else return false;
	}
}
