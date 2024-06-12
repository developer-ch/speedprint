package operatingArchive.services;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.Orientation;
import org.apache.pdfbox.printing.PDFPageable;

import operatingArchive.utils.IOUtil;

public class PDFService {
	private static final String glob = "*.pdf";
	private final Path dirWaiting;
	private final Path dirProcessed;
	private final Path dirDuplicate;

	public PDFService(Path dirWaiting, Path dirProcessed, Path dirDuplicate) throws IOException {
		this.dirWaiting = IOUtil.createDirectories(dirWaiting);
		this.dirProcessed = IOUtil.createDirectories(dirProcessed);
		this.dirDuplicate = IOUtil.createDirectories(dirDuplicate);
	}

	public void print(Path path) throws IOException, PrinterException {
		try (PDDocument documento = PDDocument.load(path.toFile())) {	
			PrintService servico = PrintServiceLookup.lookupDefaultPrintService();
			PrinterJob job = PrinterJob.getPrinterJob();
			PDFPageable pdfPageable = new PDFPageable(documento,Orientation.PORTRAIT,true);
			job.setPageable(pdfPageable);
			job.setPrintService(servico);			
			job.print();
		}
	}

	public void printAll() throws IOException, PrinterException {
		List<Path> files = IOUtil.listFiles(dirWaiting, glob);
		for (Path pathPrint : files) {
			Path pathProcessed = definePathTarget(dirProcessed, pathPrint);
			if (!IOUtil.pathExists(pathProcessed)) {
				IOUtil.moveFiles(pathPrint, pathProcessed);
				print(pathProcessed);
				continue;
			}
			IOUtil.moveFiles(pathPrint, definePathTarget(dirDuplicate, pathPrint));
		}
	}

	private Path definePathTarget(Path dirTargetPath, Path file) {
		return Paths.get(dirTargetPath.toString(), file.getFileName().toString());
	}
}
