package Patrons;

public interface Command {
	public static final ModelImage model = ModelImage.getInstance();
	public void execute();
	public void reDo();
	public void unDo();
}
