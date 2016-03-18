package Patrons;

public class Save implements Command{
	
	private static Save instance = new Save();
	
	public Save(){}
	
	public Save getInstance(){
		return instance;
	}
	
	@Override
	public void execute() {
		controller.saveImage();
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
