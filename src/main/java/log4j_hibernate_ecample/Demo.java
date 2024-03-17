package log4j_hibernate_ecample;


import org.apache.log4j.Logger;

public class Demo {

	public static void main(String[] args) {
	 Logger logger=Logger.getLogger(Demo.class);
	 logger.info("info message");
	 logger.debug("debug message");
	}

}
