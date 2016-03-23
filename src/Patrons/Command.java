package Patrons;

public interface Command {
	public static final Commandable model = ModelImage.getInstance();
	public static final double DEFAULT_ZOOM = 0.25;
	
	public boolean execute();
	public void reDo();
	public void unDo();
}
