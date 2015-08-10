package datamanagement;

public class Student implements IStudent {
	private Integer studentID;
	private String firstName;
	private String lastName;
	private StudentUnitRecordList studentUnit;

	public Student(Integer studentID, String firstName, String lastName, StudentUnitRecordList studentUnit) {
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentUnit = studentUnit == null ? new StudentUnitRecordList() : studentUnit;
	}

	public Integer getStudentID() {
		return this.studentID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {

		this.lastName = lastName;
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
