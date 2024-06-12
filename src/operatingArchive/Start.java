package operatingArchive;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.logging.Logger;

import operatingArchive.services.PDFService;

public class Start {
	private static final int MILLI_SECONDS = 1000;
	private static final int SECONDS = 60;	
	private static final int MINUTES = 3;
	private static final int TIME_SLEEP = MINUTES * SECONDS * MILLI_SECONDS;
	private static final Logger log = Logger.getGlobal();

	@SuppressWarnings("finally")
	public static void main(String[] args) {
		while (true) {
			try {
				log.info("Em Execução!");
				new PDFService(Constant.PATH_WAITING, Constant.PATH_PROCESSED, Constant.PATH_DUPLICATE).printAll();
				Thread.sleep(TIME_SLEEP);
			} catch (IOException | PrinterException | InterruptedException e) {
				log.throwing(null, null, e);
			} finally {
				continue;
			}
		}
	}
}
