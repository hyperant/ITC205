package datamanagement;

public class ListStudentsCTL {
	private StudentManager studentManager;

	public ListStudentsCTL() {
		this.studentManager = StudentManager.get();
	}

	public void listStudents(IStudentLister lister, String unitCode) {
		lister.clearStudents();
		StudentMap students = this.studentManager.getStudentsByUnit(unitCode);
		
		for (Integer id : students.keySet()) {
			lister.addStudent(students.get(id));
		}
	}
}
