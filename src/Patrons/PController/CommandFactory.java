package Patrons.PController;

public class CommandFactory {

	private ZoomIn zoomIn;
	private ZoomOut zoomOut;
	private Drag drag;

	public static Command createCommand(String commandName){
		Command nCommand = null;

		switch(commandName){
		case "Ouvrir" 	: nCommand = Ouvrir.getInstance();	break;
		case "Save" 	: nCommand = Save.getInstance(); 	break;
		case "ZoomIn" 	: break;
		case "ZoomOut" 	: break;
		case "Drag" 	: break;
		}

		return nCommand;
	}
}
