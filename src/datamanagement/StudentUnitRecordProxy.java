package datamanagement;

public class StudentUnitRecordProxy implements IStudentUnitRecord {
	private Integer studentID;
	private String unitCode;
	private StudentUnitRecordManager recordManager;

	public StudentUnitRecordProxy(Integer studentID, String unitCode) {
		this.studentID = studentID;
		this.unitCode = unitCode;
		this.recordManager = StudentUnitRecordManager.instance();
	}

	public Integer getStudentID() {
		return studentID;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setAsg1(float mark) {
		recordManager.getStudentUnitRecord(studentID, unitCode).setAsg1(mark);
	}

	public float getAsg1() {
		return recordManager.getStudentUnitRecord(studentID, unitCode).getAsg1();
	}

	public void setAsg2(float mark) {
		recordManager.getStudentUnitRecord(studentID, unitCode).setAsg2(mark);
	}

	public float getAsg2() {
		return recordManager.getStudentUnitRecord(studentID, unitCode).getAsg2();
	}

	public void setExam(float mark) {
		recordManager.getStudentUnitRecord(studentID, unitCode).setExam(mark);
	}

	public float getExam() {
		return recordManager.getStudentUnitRecord(studentID, unitCode).getExam();
	}

	public float getTotal() {
		return recordManager.getStudentUnitRecord(studentID, unitCode).getTotal();
	}

	@Override
	public void setAssignment1(float mark) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getAssignment1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAssignment2(float mark) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getAssignment2() {
		// TODO Auto-generated method stub
		return 0;
	}
}
