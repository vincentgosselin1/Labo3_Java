package Patrons;

public interface Command {
	public static final Controller controller = Controller.getInstance();
	public void execute();
	public void reDo();
	public void unDo();
}
