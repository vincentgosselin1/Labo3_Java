package Patrons;

public class SingletonExemple {

	public static class SingleObject {

		   //create an object of SingleObject
		   private static SingleObject instance = new SingleObject();

		   //make the constructor private so that this class cannot be
		   //instantiated
		   private SingleObject(){}

		   //Get the only object available
		   public static SingleObject getInstance(){
		      return instance;
		   }

		   public void Méthode(){
		      //Ce qu'on peut faire avec l'objet
		   }
		}
}
