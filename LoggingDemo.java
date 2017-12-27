import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingDemo {
  public static void main(String[] args) throws Exception {
    // Obtain the logger object
    Logger logger = Logger.getGlobal();

    // Create a file handler
    FileHandler handler = new FileHandler("c:\\temp.log");
    // Add the file handler to the logger
    logger.addHandler(handler); 

    // Set logger level
    logger.setLevel(Level.FINEST);

    logger.severe("my severe message");
    logger.warning("my warning message");
    logger.info("my info message");
    logger.config("my config message");
    logger.fine("my fine message");
    logger.finer("my finer message");
    logger.finest("my finest message");
  }
}