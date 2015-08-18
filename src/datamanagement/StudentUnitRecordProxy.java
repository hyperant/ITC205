package datamanagement;

public class StudentUnitRecordProxy implements IStudentUnitRecord {
	private Integer studentID_;
	private String unitCode_;
	private StudentUnitRecordManager recordManager;

	public StudentUnitRecordProxy(Integer studentID, String unitCode) {
		this.studentID_ = studentID;
		this.unitCode_ = unitCode;
		this.recordManager = StudentUnitRecordManager.instance();
	}

	public Integer getStudentID() {
		return studentID_;
	}

	public String getUnitCode() {
		return unitCode_;
	}

	public void setAsg1(float mark) {
		recordManager.getStudentUnitRecord(studentID_, unitCode_).setAssignment1(mark);
	}

	public float getAsg1() {
		return recordManager.getStudentUnitRecord(studentID_, unitCode_).getAssignment1();
	}

	public void setAsg2(float mark) {
		recordManager.getStudentUnitRecord(studentID_, unitCode_).setAssignment2(mark);
	}

	public float getAsg2() {
		return recordManager.getStudentUnitRecord(studentID_, unitCode_).getAssignment2();
	}

	public void setExam(float mark) {
		recordManager.getStudentUnitRecord(studentID_, unitCode_).setExam(mark);
	}

	public float getExam() {
		return recordManager.getStudentUnitRecord(studentID_, unitCode_).getExam();
	}

	public float getTotal() {
		return recordManager.getStudentUnitRecord(studentID_, unitCode_).getTotal();
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
