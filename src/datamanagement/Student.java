package datamanagement;

public class Student implements IStudent {
	private Integer studentID_;
	private String firstName_;
	private String lastName_;
	private StudentUnitRecordList studentUnit_;

	public Student(Integer studentID, String firstName, String lastName, StudentUnitRecordList studentUnit) {
		this.studentID_ = studentID;
		this.firstName_ = firstName;
		this.lastName_ = lastName;
		this.studentUnit_ = studentUnit == null ? new StudentUnitRecordList() : studentUnit;
	}

	public Integer getStudentID() {
		return this.studentID_;
	}

	public String getFirstName() {
		return this.firstName_;
	}

	public void setFirstName(String firstName) {
		this.firstName_ = firstName;
	}

	public String getLastName() {
		return this.lastName_;
	}

	public void setLastName(String lastName) {

		this.lastName_ = lastName;
	}

	public void addUnitRecord(IStudentUnitRecord record) {
		this.studentUnit_.add(record);
	}

	public IStudentUnitRecord getUnitRecord(String unitCode) {
		for (IStudentUnitRecord unitRecord : this.studentUnit_)
			if (unitRecord.getUnitCode().equals(unitCode))
				return unitRecord;

		return null;

	}

	public StudentUnitRecordList getUnitRecords() {
		return this.studentUnit_;
	}

	@Override
	public Integer getID() {
		// TODO Auto-generated method stub
		return null;
	}
}
