package factory;

import java.util.logging.Logger;

public class MasalaDosa implements Dosa{

	private static final Logger logger =  Logger.getLogger(MasalaDosa.class.getName());

	@Override
	public boolean spreadFlavours() {
		logger.info("The masala applied inside the dosa");
		return true;
	}

}
