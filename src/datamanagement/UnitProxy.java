package datamanagement;

public class UnitProxy implements IUnit {
	private String unitCode_;
	private String unitName_;

	UnitManager unitManager;

	public UnitProxy(String unitCode, String unitName) {
		this.unitCode_ = unitCode;
		this.unitName_ = unitName;
		unitManager = UnitManager.unitManager();
	}

	public String getUnitCode() {
		return this.unitCode_;
	}

	public String getUnitName() {
		return this.unitName_;
	}

	public void setPassCutoff(float cutoff) {
		unitManager.getUnit(unitCode_).setPassCutoff(cutoff);
	}

	public float getPassCutoff() {
		return unitManager.getUnit(unitCode_).getPassCutoff();
	}

	public void setCreditCutoff(float cutoff) {
		unitManager.getUnit(unitCode_).setCreditCutoff(cutoff);
	}

	public float getCreditCutoff() {
		return unitManager.getUnit(unitCode_).getCreditCutoff();
	}

	public void setDistinctionCutoff(float cutoff) {
		unitManager.getUnit(unitCode_).setDistinctionCutoff(cutoff);
	}

	public float getDistinctionCuttoff() {
		return unitManager.getUnit(unitCode_).getDistinctionCuttoff();
	}

	public void setHighDistinctionCutoff(float cutoff) {
		unitManager.getUnit(unitCode_).setHighDistinctionCutoff(cutoff);
	}

	public float getHighDistinctionCutoff() {

		return unitManager.getUnit(unitCode_).getHighDistinctionCutoff();
	}

	public void setAdditionalExaminationCutoff(float cutoff) {
		unitManager.getUnit(unitCode_).setAdditionalExaminationCutoff(cutoff);
	}

	public float getAdditionalExaminationCutoff() {
		return unitManager.getUnit(unitCode_).getAdditionalExaminationCutoff();
	}

	public String getGrade(float assignment1Mark, float assignment2Mark, float examMark) {
		return unitManager.getUnit(unitCode_).getGrade(assignment1Mark, assignment2Mark, examMark);
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

	public int getAssignment1Weight() {
		return unitManager.getUnit(unitCode_).getAssignment1Weight();
	}

	public int getAssignment2Weight() {
		return unitManager.getUnit(unitCode_).getAssignment2Weight();
	}

	public int getExamWeight() {
		return unitManager.getUnit(unitCode_).getExamWeight();
	}

	public void setAssessmentWeights(int assignment1Weight, int assignment2Weight, int examWeight) {
		unitManager.getUnit(unitCode_).setAssessmentWeights(assignment1Weight, assignment2Weight, examWeight);

	}
}
