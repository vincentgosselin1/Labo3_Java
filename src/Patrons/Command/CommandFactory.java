package Patrons.Command;

import Patrons.PController.Controller;
import Patrons.PController.InformationNeeded;

public class CommandFactory {

	private static final InformationNeeded controller = Controller.getInstance();

	public static Command createCommand(String commandName){
		Command nCommand = null;

		switch(commandName){
		case "Ouvrir" 				: nCommand = new Ouvrir();									break;
		case "OuvrirSpecial" 		: nCommand = new Ouvrir(controller.getImageName());									break;
		case "Save" 				: nCommand = Save.getInstance(); 									break;
		case "ZoomInFromVueDonnees" : nCommand = new ZoomIn(controller.getZoom());						break;
		case "ZoomOutFromVueDonnees": nCommand = new ZoomOut(controller.getZoom());						break;
		case "ZoomIn" 				: nCommand = new ZoomIn();											break;
		case "ZoomOut" 				: nCommand = new ZoomOut();											break;
		case "Drag" 				: nCommand = new Drag(controller.getNewX(),controller.getNewY());	break;
		}

		return nCommand;
	}
}
