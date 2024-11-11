package factory;

public class DosaFactory {
	
	public static Dosa createDosa(DosaTypes dosaType) {
		
		prepareDosa();
		
		switch (dosaType) {
		case Podi:
			return new PodiDosa();
		case Masala:
			return new MasalaDosa();
		case Onion:
			return new OnionDosa();
		default:
			throw new IllegalArgumentException("Unknown Dosa Type");
		}
		
	}

	private static void prepareDosa() {
		DosaPrepHelper.makeBatter();
		DosaPrepHelper.ferment();
		DosaPrepHelper.heatTava();
		DosaPrepHelper.pourBatter();
		
	}

}
