package Utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
	
	private final Logger logger = 
			Logger.getLogger("MYLOAG");
	private FileHandler fd = null;
	
	public Log(String filen) {
		try {
			this.fd = new FileHandler(filen);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		fd.setFormatter(new SimpleFormatter());
		logger.addHandler(fd);
	}
	
	public void I(Object...msg) {
		String m = this.__MessageConcat(msg);
		this.logger.info(m);
	}
	
	public void S(Object... msg) {
		String m = this.__MessageConcat(msg);
		this.logger.severe(m);
	}
	
	public void E(Object... msg) {
		String m = this.__MessageConcat(msg);
		this.logger.severe(m);
	}
	
	public void W(Object... msg) {
		String m = this.__MessageConcat(msg);
		this.logger.warning(m);
	}

	private String __MessageConcat(Object...msg) {
		String m = "";
		
		for(int i=0; i < msg.length; i++) {
			m += msg[i].toString();
		}
		return m;
	}
}
