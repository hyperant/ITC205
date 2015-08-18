package datamanagement;

public class Student implements IStudent {
	private Integer studentID_;
	private String firstName_;
	private String lastName_;
	private StudentUnitRecordList studentUnit;

	public Student(Integer studentID, String firstName, String lastName, StudentUnitRecordList studentUnit) {
		this.studentID_ = studentID;
		this.firstName_ = firstName;
		this.lastName_ = lastName;
		this.studentUnit = studentUnit == null ? new StudentUnitRecordList() : studentUnit;
	}

	public Integer getStudentID() {
		return this.studentID_;
	}

	public String getFirstName() {
		return firstName_;
	}

	public void setFirstName(String firstName) {
		this.firstName_ = firstName;
	}

	public String getLastName() {
		return lastName_;
	}

	public void setLastName(String lastName) {

		this.lastName_ = lastName;
	}

	public void addUnitRecord(IStudentUnitRecord record) {
		studentUnit.add(record);
	}

	public IStudentUnitRecord getUnitRecord(String unitCode) {
		for (IStudentUnitRecord unitRecord : studentUnit)
			if (unitRecord.getUnitCode().equals(unitCode))
				return unitRecord;

		return null;

	}

	public StudentUnitRecordList getUnitRecords() {
		return studentUnit;
	}

	@Override
	public Integer getID() {
		// TODO Auto-generated method stub
		return null;
	}
}
