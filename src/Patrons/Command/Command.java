package Patrons.Command;

import java.nio.file.Path;
import java.nio.file.Paths;

import Patrons.PModel.Commandable;
import Patrons.PModel.ModelImage;

public interface Command {
	public static final Commandable model = ModelImage.getInstance();
	public static final double DEFAULT_ZOOM = 0.25;
	public static final Path workingDirectory = Paths.get("").toAbsolutePath();
	
	public boolean execute();
	public void reDo();
	public void unDo();
}
