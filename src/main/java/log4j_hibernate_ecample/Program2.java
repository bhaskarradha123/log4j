package log4j_hibernate_ecample;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Program2 {

	public static void main(String[] args) {
	
		
		Logger logger=Logger.getLogger(Program2.class);
		Layout layout=new PatternLayout("%d{dd}--> %p %M ");
		/*
		 * d-date and time   or    {dd-mm-yyyy}-->  to date format
		 * p-logger level
		 * m-message
		 * c-filly qualified name
		 * M-thread
		 * l-line num of logger happening
		 * n-next line
		 */
		Appender appender=new ConsoleAppender(layout);
		
		logger.addAppender(appender);
		logger.info("hi");

	}

}
