package operatingArchive.utils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {

	private IOUtil() {
	}

	public static Path createDirectories(Path dir) throws IOException {
		if (!pathExists(dir)) {
			return Files.createDirectories(dir);
		}
		return dir;
	}

	public static void moveFiles(Path pathSource, Path pathTarget) throws IOException {
		Files.move(pathSource, pathTarget, StandardCopyOption.REPLACE_EXISTING);
	}

	public static boolean pathExists(Path path) {
		return Files.exists(path);
	}

	public static List<Path> listFiles(Path dir, String glob) throws IOException {
		List<Path> paths = new ArrayList<>();
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir, glob)) {
			dirStream.forEach(paths::add);
		}
		return paths;
	}
}