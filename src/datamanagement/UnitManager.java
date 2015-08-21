package datamanagement;

import java.util.List;
import org.jdom.Element;

public class UnitManager {

	private static UnitManager self = null;

	private UnitMap unitMap_;

	public static UnitManager unitManager() {
		if (self == null)
			self = new UnitManager();
		return self;
	}

	private UnitManager() {
		unitMap_ = new UnitMap();
	}

	public IUnit getUnit(String unitCode) {
		IUnit iUnit = unitMap_.get(unitCode);
		return iUnit != null ? iUnit : createUnit(unitCode);

	}

	private IUnit createUnit(String unitCode) {

		IUnit iUnit;

		for (Object unitElement : (List<?>) XMLManager.getXML().getDocument()
				.getRootElement().getChild("unitTable").getChildren("unit"))
			if (unitCode.equals(((Element) unitElement).getAttributeValue("uid"))) {

				iUnit = new Unit(((Element) unitElement).getAttributeValue("uid"),
						((Element) unitElement).getAttributeValue("name"), Float.valueOf(
								((Element) unitElement).getAttributeValue("ps")).floatValue(), Float
								.valueOf(((Element) unitElement).getAttributeValue("cr"))
								.floatValue(), Float.valueOf(
								((Element) unitElement).getAttributeValue("di")).floatValue(), Float
								.valueOf(((Element) unitElement).getAttributeValue("hd"))
								.floatValue(), Float.valueOf(
								((Element) unitElement).getAttributeValue("ae")).floatValue(),
						Integer.valueOf(((Element) unitElement).getAttributeValue("asg1wgt"))
								.intValue(), Integer.valueOf(
								((Element) unitElement).getAttributeValue("asg2wgt")).intValue(),
						Integer.valueOf(((Element) unitElement).getAttributeValue("examwgt"))
								.intValue(), StudentUnitRecordManager
								.instance().getRecordsByUnit(unitCode));
				unitMap_.put(iUnit.getUnitCode(), iUnit);
				return iUnit;
			}

		throw new RuntimeException("DBMD: createUnit : unit not in file");
	}

	public UnitMap getUnits() {

		UnitMap unitMap;
		IUnit iUnit;

		unitMap = new UnitMap();
		for (Object unitElement : (List<?>) XMLManager.getXML().getDocument()
				.getRootElement().getChild("unitTable").getChildren("unit")) {
			iUnit = new UnitProxy(((Element)unitElement).getAttributeValue("uid"),
					((Element) unitElement).getAttributeValue("name"));
			unitMap.put(iUnit.getUnitCode(), iUnit);
		} // unit maps are filled with PROXY units
		return unitMap;
	}

}
