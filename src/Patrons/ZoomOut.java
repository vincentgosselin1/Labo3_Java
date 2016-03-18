package Patrons;

public class ZoomOut implements Command {
	
	private static ZoomOut instance = new ZoomOut();

	public ZoomOut(){}

	public static ZoomOut getInstance(){
		return instance;
	}
	
	private double zoomValue;
	
	@Override
	public void execute() {
		
		
	}

	@Override
	public void reDo() {
		
		
	}

	@Override
	public void unDo() {
		
		
	}
	
}