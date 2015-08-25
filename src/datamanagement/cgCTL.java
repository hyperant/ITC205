package datamanagement;

/**
 * This class is responsible for setting up the GUI that is displayed to the end user
 *
 * @author Jason Fletcher
 *
 */
public class cgCTL {

	private cgUI cgUI_;
	private String unitCode_ = null;
	private Integer currentStudentID_ = null;
	private boolean changed_ = false;

	/**
	 * The constructor method for the class. This class accepts no input, and only creates a new instance of this class.
	 * </br></br>
	 * In order to display the GUI to the end user you must call {@link #execute()}
	 * </br>
	 * <b>Example:</b>
	 * </br>
	 * <pre>
	 * {@code
	 * 	new cgCTL().execute();
	 * }
	 * </pre>
	 */
	public cgCTL() {

	}

	/**
	 * Setups and displays the GUI to the end user
	 */
	public void execute() {
		ListUnitsCTL listUnitsCTL = new ListUnitsCTL();

		this.cgUI_ = new cgUI(this);
		this.cgUI_.setState1(false);

		this.cgUI_.setState2(false);
		this.cgUI_.setState3(false);
		this.cgUI_.setState4(false);
		this.cgUI_.setState5(false);
		this.cgUI_.setState6(false);
		this.cgUI_.Refresh3();

		listUnitsCTL.listUnits(this.cgUI_);
		this.cgUI_.setVisible(true);
		this.cgUI_.setState1(true);
	}


	/**
	 * Sets the selected course unit and updates the GUI accordingly.
	 *
	 * @param unitCode The current courses unit code
	 */
	public void unitSelected(String unitCode) {
		if (unitCode.equals("NONE")) { //If we don't have a course unit set then we need to disable the student selection box
			this.cgUI_.setState2(false);
		} else { //We have a course selected so display the students for that course
			ListStudentsCTL listStudentsCTL = new ListStudentsCTL();
			listStudentsCTL.listStudents(this.cgUI_, unitCode);
			this.unitCode_ = unitCode;
			this.cgUI_.setState2(true);
		}

		this.cgUI_.setState3(false);
	}

	/**
	 * Sets the selected student and updates the GUI accordingly.
	 *
	 * @param sID The current Students ID
	 */
	public void studentSelected(Integer sID) {
		this.currentStudentID_ = sID;

		if (this.currentStudentID_.intValue() == 0) { //If we don't have a student set then we need to disable the assignment mark box's
			this.cgUI_.Refresh3();
			this.cgUI_.setState3(false);
			this.cgUI_.setState4(false);
			this.cgUI_.setState5(false);
			this.cgUI_.setState6(false);
		} else { //We have a student selected so display the assignment mark box's
			IStudent studentmManager = StudentManager.get().getStudent(sID);
			IStudentUnitRecord studentRecord = studentmManager.getUnitRecord(this.unitCode_);

			this.cgUI_.setRecord(studentRecord);
			this.cgUI_.setState3(true);
			this.cgUI_.setState4(true);
			this.cgUI_.setState5(false);
			this.cgUI_.setState6(false);
			this.changed_ = false;
		}
	}

	/**
	 * Checks the current students grade
	 *
	 * @param assignment1 the students mark for their first assignment
	 * @param assignment2 the students mark for their second assignment
	 * @param exam the students mark for their exam
	 * @return String representation of the current students grade
	 * @see String
	 */
	public String checkGrade(float assignment1, float assignment2, float exam) {
		IUnit unit = UnitManager.unitManager().getUnit(this.unitCode_);
		String grade = unit.getGrade(assignment1, assignment2, exam);

		this.cgUI_.setState4(true);
		this.cgUI_.setState5(false);

		if (this.changed_) {
			this.cgUI_.setState6(true);
		}

		return grade;
	}

	/**
	 * Allow the students marks to be changed
	 */
	public void enableChangeMarks() {
		this.cgUI_.setState4(false);
		this.cgUI_.setState6(false);
		this.cgUI_.setState5(true);
		this.changed_ = true;
	}

	/**
	 * Save the users grade
	 *
	 * @param assignment1 the students mark for their first assignment
	 * @param assignment2 the students mark for their second assignment
	 * @param exam the students mark for their exam
	 */
	public void saveGrade(float assignment1, float assignment2, float exam) {
		IStudent studentManager = StudentManager.get().getStudent(this.currentStudentID_);

		IStudentUnitRecord studentRecord = studentManager.getUnitRecord(this.unitCode_);
		studentRecord.setAssignment1(assignment1);
		studentRecord.setAssignment2(assignment2);
		studentRecord.setExam(exam);
		StudentUnitRecordManager.instance().saveRecord(studentRecord);

		this.cgUI_.setState4(true);
		this.cgUI_.setState5(false);
		this.cgUI_.setState6(false);
	}
}
