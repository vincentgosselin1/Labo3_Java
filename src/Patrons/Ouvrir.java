package Patrons;

public class Ouvrir implements Command{
	
	private static Ouvrir instance = new Ouvrir();
	
	public Ouvrir(){}
	
	public Ouvrir getInstance(){
		return instance;
	}
	
	@Override
	public void execute() {
		controller.ouvrirFichier();
	}

	@Override
	public void reDo() {
		//Do nothing
	}

	@Override
	public void unDo() {
		//Do nothing
	}
}
