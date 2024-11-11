package factory;

import java.util.logging.Logger;

public class OnionDosa implements Dosa{

	private static final Logger logger =  Logger.getLogger(OnionDosa.class.getName());

	@Override
	public boolean spreadFlavours() {
		logger.info("The onion applied around the dosa");
		return true;
	}

}
