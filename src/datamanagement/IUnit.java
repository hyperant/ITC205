package datamanagement;

public interface IUnit {

	public String getUnitCode();

	public String getUnitName();

	public float getPsCutoff();

	public void setPsCutoff(float cutoff);

	public float getCrCutoff();

	public void setCrCutoff(float cutoff);

	public float getDiCuttoff();

	public void setDiCutoff(float cutoff);

	public float getHdCutoff();

	public void setHdCutoff(float cutoff);

	public float getAeCutoff();

	public void setAeCutoff(float cutoff);

	public int getAsg1Weight();

	public int getAsg2Weight();

	public int getExamWeight();

	public void setAssessmentWeights(int assignment1Weight, int assignment2Weight, int examWeight);

	public String getGrade(float assignment1Mark, float assignment2Mark, float examMark);

	public void addStudentRecord(IStudentUnitRecord record);

	public IStudentUnitRecord getStudentRecord(int studentID);

	public StudentUnitRecordList listStudentRecords();
}
