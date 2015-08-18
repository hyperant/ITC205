package datamanagement;

public class UnitProxy implements IUnit {
	private String unitCode_;
	private String unitName_;

	UnitManager unitManager;

	public UnitProxy(String unitCode, String unitName) {
		this.unitCode_ = unitCode;
		this.unitName_ = unitName;
		unitManager = UnitManager.UM();
	}

	public String getUnitCode() {
		return this.unitCode_;
	}

	public String getUnitName() {
		return this.unitName_;
	}

	public void setPsCutoff(float cutoff) {
		unitManager.getUnit(unitCode_).setPsCutoff(cutoff);
	}

	public float getPsCutoff() {
		return unitManager.getUnit(unitCode_).getPsCutoff();
	}

	public void setCrCutoff(float cutoff) {
		unitManager.getUnit(unitCode_).setCrCutoff(cutoff);
	}

	public float getCrCutoff() {
		return unitManager.getUnit(unitCode_).getCrCutoff();
	}

	public void setDiCutoff(float cutoff) {
		unitManager.getUnit(unitCode_).setDiCutoff(cutoff);
	}

	public float getDiCuttoff() {
		return unitManager.getUnit(unitCode_).getDiCuttoff();
	}

	public void setHdCutoff(float cutoff) {
		unitManager.getUnit(unitCode_).setHdCutoff(cutoff);
	}

	public float getHdCutoff() {

		return unitManager.getUnit(unitCode_).getHdCutoff();
	}

	public void setAeCutoff(float cutoff) {
		unitManager.getUnit(unitCode_).setAeCutoff(cutoff);
	}

	public float getAeCutoff() {
		return unitManager.getUnit(unitCode_).getAeCutoff();
	}

	public String getGrade(float f1, float f2, float f3) {
		return unitManager.getUnit(unitCode_).getGrade(f1, f2, f3);
	}

	public void addStudentRecord(IStudentUnitRecord record) {
		unitManager.getUnit(unitCode_).addStudentRecord(record);
	}

	public IStudentUnitRecord getStudentRecord(int s) {
		return unitManager.getUnit(unitCode_).getStudentRecord(s);
	}

	public StudentUnitRecordList listStudentRecords() {
		return unitManager.getUnit(unitCode_).listStudentRecords();
	}

	public int getAsg1Weight() {
		return unitManager.getUnit(unitCode_).getAsg1Weight();
	}

	public int getAsg2Weight() {
		return unitManager.getUnit(unitCode_).getAsg2Weight();
	}

	public int getExamWeight() {
		return unitManager.getUnit(unitCode_).getExamWeight();
	}

	public void setAssessmentWeights(int asg1Wgt, int asg2Wgt, int examWgt) {
		unitManager.getUnit(unitCode_).setAssessmentWeights(asg1Wgt, asg2Wgt, examWgt);

	}
}
