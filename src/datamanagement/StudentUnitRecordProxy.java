package datamanagement;

public class StudentUnitRecordProxy implements IStudentUnitRecord {
	private Integer studentID_;
	private String unitCode_;
	private StudentUnitRecordManager recordManager_;

	public StudentUnitRecordProxy(Integer studentID, String unitCode) {
		this.studentID_ = studentID;
		this.unitCode_ = unitCode;
		this.recordManager_ = StudentUnitRecordManager.instance();
	}

	public Integer getStudentID() {
		return this.studentID_;
	}

	public String getUnitCode() {
		return this.unitCode_;
	}

	public void setAssignmentOne(float mark) {
		this.recordManager_.getStudentUnitRecord(studentID_, unitCode_).setAssignment1(mark);
	}

	public float getAssignmentOne() {
		return this.recordManager_.getStudentUnitRecord(studentID_, unitCode_).getAssignment1();
	}

	public void setAssignmentTwo(float mark) {
		this.recordManager_.getStudentUnitRecord(studentID_, unitCode_).setAssignment2(mark);
	}

	public float getAssignmentTwo() {
		return this.recordManager_.getStudentUnitRecord(studentID_, unitCode_).getAssignment2();
	}

	public void setExam(float mark) {
		this.recordManager_.getStudentUnitRecord(studentID_, unitCode_).setExam(mark);
	}

	public float getExam() {
		return this.recordManager_.getStudentUnitRecord(studentID_, unitCode_).getExam();
	}

	public float getTotal() {
		return this.recordManager_.getStudentUnitRecord(studentID_, unitCode_).getTotal();
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
