package datamanagement;

public class StudentProxy implements IStudent {
	private Integer identification_;
	private String firstName_;

	private String lastName_;
	private StudentManager studentManager;

	public StudentProxy(Integer identification, String firstName, String lastName) {
		this.identification_ = identification;
		this.firstName_ = firstName;

		this.lastName_ = lastName;
		this.studentManager = StudentManager.get();
	}

	public Integer getID() {
		return identification_;

	}

	public String getFirstName() {
		return firstName_;
	}

	public String getLastName() {
		return lastName_;
	}

	public void setFirstName(String firstName) {

		studentManager.getStudent(identification_).setFirstName(firstName);
	}

	public void setLastName(String lastName) {

		studentManager.getStudent(identification_).setLastName(lastName);
	}

	public void addUnitRecord(IStudentUnitRecord record) {
		studentManager.getStudent(identification_).addUnitRecord(record);
	}

	public IStudentUnitRecord getUnitRecord(String unitCode) {

		return studentManager.getStudent(identification_).getUnitRecord(unitCode);
	}

	public StudentUnitRecordList getUnitRecords() {
		return studentManager.getStudent(identification_).getUnitRecords();
	}
}
