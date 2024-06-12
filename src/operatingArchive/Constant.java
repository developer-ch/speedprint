package operatingArchive;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface Constant {
	String PATH_ROOT = "\\AutomaticArchivePrinting";
	Path PATH_WAITING = Paths.get(PATH_ROOT, "Waiting");
	Path PATH_PROCESSED = Paths.get(PATH_ROOT, "Processed");
	Path PATH_DUPLICATE = Paths.get(PATH_ROOT, "Duplicates");
}
