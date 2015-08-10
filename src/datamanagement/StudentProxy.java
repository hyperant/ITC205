package datamanagement;

public class StudentProxy implements IStudent {
	private Integer identification;
	private String firstName;

	private String lastName;
	private StudentManager studentManager;

	public StudentProxy(Integer identification, String firstName, String lastName) {
		this.identification = identification;
		this.firstName = firstName;

		this.lastName = lastName;
		this.studentManager = StudentManager.get();
	}

	public Integer getID() {
		return identification;

	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {

		studentManager.getStudent(identification).setFirstName(firstName);
	}

	public void setLastName(String lastName) {

		studentManager.getStudent(identification).setLastName(lastName);
	}

	public void addUnitRecord(IStudentUnitRecord record) {
		studentManager.getStudent(identification).addUnitRecord(record);
	}

	public IStudentUnitRecord getUnitRecord(String unitCode) {

		return studentManager.getStudent(identification).getUnitRecord(unitCode);
	}

	public StudentUnitRecordList getUnitRecords() {
		return studentManager.getStudent(identification).getUnitRecords();
	}
}
