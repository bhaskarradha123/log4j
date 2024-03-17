package log4j_hibernate_ecample;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Program1 {
	
	//simple 

	public static void main(String[] args) {
		Logger logger =Logger.getLogger(Program1.class);
		Layout layout=new SimpleLayout();
		Appender appender=new ConsoleAppender(layout);
		logger.addAppender(appender);
		
		
		logger.info("Info meassage the db connection done..!!");
		logger.debug("Debug meassage");
		logger.warn("Warn message");
		logger.error("Error message");
		logger.fatal("Fatal meassage");

	}

}
