package factory;

import java.util.logging.Logger;

public class DosaPrepHelper {

	private static final Logger logger = Logger.getLogger(DosaPrepHelper.class.getName());
	
	public static void makeBatter() {
		logger.info("Making Batter for dosa ... ");
	}

	public static void ferment() {
		logger.info("Fermenting ... ");
	}

	public static void heatTava() {
		logger.info("Heat the tava ... ");

	}
	
	public static void pourBatter() {
		logger.info("Pour Batter in round shape ... ");

	}
}
