package datamanagement;

public class ListStudentsCTL {
	private StudentManager studentManager_;

	/**
	 * The constructor method for the class.
	 * <br />
	 * Initializes studentManager
	 */
	public ListStudentsCTL() {
		this.studentManager_ = StudentManager.get();
	}

	/**
	 * Clears and adds students to the studentLister object for a specified subject
	 * 
	 * @param lister the object to add the students to
	 * @param unitCode subject code to list the students from
	 */
	public void listStudents(IStudentLister lister, String unitCode) {
		lister.clearStudents();
		StudentMap students = this.studentManager_.getStudentsByUnit(unitCode);
		
		for (Integer id : students.keySet()) {
			lister.addStudent(students.get(id));
		}
	}
}
