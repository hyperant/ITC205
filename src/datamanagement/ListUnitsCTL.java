package datamanagement;

public class ListUnitsCTL {
	private UnitManager unitManager;

	public ListUnitsCTL() {
		this.unitManager = UnitManager.UM();
	}

	public void listUnits(IUnitLister lister) {
		lister.clearUnits();
		UnitMap units = this.unitManager.getUnits();
		
		for (String s : units.keySet()) {
			lister.addUnit(units.get(s));
		}
	}
}
