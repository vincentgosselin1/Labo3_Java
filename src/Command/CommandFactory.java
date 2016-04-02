package Command;

import Controller.Controller;
import Controller.InformationNeeded;

public class CommandFactory {

	private static final InformationNeeded CONTROLLER = Controller.getInstance();

	public static Command createCommand(String commandName){
		Command nCommand = null;
		
		switch(commandName){
		case "Ouvrir" 				: nCommand = new Ouvrir();											break;
		case "OuvrirSpecial" 		: nCommand = new Ouvrir(CONTROLLER.getImageName());					break;
		case "Save" 				: nCommand = Save.getInstance(); 									break;
		case "ZoomInFromVueDonnees" : nCommand = new ZoomIn(CONTROLLER.getZoom());						break;
		case "ZoomOutFromVueDonnees": nCommand = new ZoomOut(CONTROLLER.getZoom());						break;
		case "ZoomIn" 				: nCommand = new ZoomIn();											break;
		case "ZoomOut" 				: nCommand = new ZoomOut();											break;
		case "Drag" 				: nCommand = new Drag(CONTROLLER.getNewX(),CONTROLLER.getNewY(), 
													CONTROLLER.getoDragX(), CONTROLLER.getoDragY());	break;
		case "CouleurChange"		: nCommand = CouleurChange.getInstance();									break;
		}

		return nCommand;
	}
}
