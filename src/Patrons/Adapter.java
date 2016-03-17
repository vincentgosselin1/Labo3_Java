package Patrons;

public class Adapter {

	   private static Adapter instance = new Adapter();

	   private Adapter(){}

	   public static Adapter getInstance(){
	      return instance;
	   }

	   public void ouvrir(){
	      //Ce qu'on peut faire avec l'objet
	   }
}
