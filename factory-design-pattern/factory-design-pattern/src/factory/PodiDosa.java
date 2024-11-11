package factory;

import java.util.logging.Logger;

public class PodiDosa implements Dosa{

	private static final Logger logger =  Logger.getLogger(PodiDosa.class.getName());

	@Override
	public boolean spreadFlavours() {
		logger.info("The podi applied around the dosa");
		return true;
	}

}
