package Patrons.Command;

import Memento.CouleurCaretaker;
import Memento.CouleurOriginator;
import Patrons.PController.Controller;
import Patrons.PController.InformationNeeded;

public class CommandFactory {

	private static final InformationNeeded controller = Controller.getInstance();
	
	private static CouleurCaretaker caretaker = new CouleurCaretaker();
	private static CouleurOriginator originator = new CouleurOriginator();

	public static Command createCommand(String commandName){
		Command nCommand = null;
		
		switch(commandName){
		case "Ouvrir" 				: nCommand = new Ouvrir();											break;
		case "OuvrirSpecial" 		: nCommand = new Ouvrir(controller.getImageName());					break;
		case "Save" 				: nCommand = Save.getInstance(); 									break;
		case "ZoomInFromVueDonnees" : nCommand = new ZoomIn(controller.getZoom());						break;
		case "ZoomOutFromVueDonnees": nCommand = new ZoomOut(controller.getZoom());						break;
		case "ZoomIn" 				: nCommand = new ZoomIn();											break;
		case "ZoomOut" 				: nCommand = new ZoomOut();											break;
		case "Drag" 				: nCommand = new Drag(controller.getNewX(),controller.getNewY());	break;
		case "CouleurChange"		: nCommand = new CouleurChange(caretaker,originator);				break;
		}

		return nCommand;
	}
}
