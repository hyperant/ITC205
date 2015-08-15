package datamanagement;

public class ListUnitsCTL {
	private UnitManager unitManager;

	/**
	 * The constructor method for the class.
	 * <br />
	 * Initializes unitManager
	 */
	public ListUnitsCTL() {
		this.unitManager = UnitManager.UM();
	}

	/**
	 * Clears and adds units to the unitLister object
	 * 
	 * @param lister the object to add the units to
	 */
	public void listUnits(IUnitLister lister) {
		lister.clearUnits();
		UnitMap units = this.unitManager.getUnits();
		
		for (String s : units.keySet()) {
			lister.addUnit(units.get(s));
		}
	}
}
