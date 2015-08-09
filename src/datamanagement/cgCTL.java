package datamanagement;

/**
 * This class is responsible for setting up the GUI that is displayed to the end user
 * 
 * @author Jason Fletcher
 *
 */
public class cgCTL {

	private cgUI CGUI;
	private String unitCode = null;
	private Integer currentStudentID = null;
	private boolean changed = false;

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
		ListUnitsCTL luCTL = new ListUnitsCTL();
		
		this.CGUI = new cgUI(this);
		this.CGUI.setState1(false);

		this.CGUI.setState2(false);
		this.CGUI.setState3(false);
		this.CGUI.setState4(false);
		this.CGUI.setState5(false);
		this.CGUI.setState6(false);
		this.CGUI.Refresh3();

		luCTL.listUnits(this.CGUI);
		this.CGUI.setVisible(true);
		this.CGUI.setState1(true);
	}

	
	/**
	 * Sets the selected course unit and updates the GUI accordingly.
	 * 
	 * @param unitCode The current courses unit code
	 */
	public void unitSelected(String unitCode) {
		if (unitCode.equals("NONE")) { //If we don't have a course unit set then we need to disable the student selection box
			this.CGUI.setState2(false);
		} else { //We have a course selected so display the students for that course
			ListStudentsCTL lsCTL = new ListStudentsCTL();
			lsCTL.listStudents(this.CGUI, unitCode);
			this.unitCode = unitCode;
			this.CGUI.setState2(true);
		}
		
		this.CGUI.setState3(false);
	}

	/**
	 * Sets the selected student and updates the GUI accordingly.
	 * 
	 * @param sID The current Students ID
	 */
	public void studentSelected(Integer sID) {
		this.currentStudentID = sID;
		
		if (this.currentStudentID.intValue() == 0) { //If we don't have a student set then we need to disable the assignment mark box's
			this.CGUI.Refresh3();
			this.CGUI.setState3(false);
			this.CGUI.setState4(false);
			this.CGUI.setState5(false);
			this.CGUI.setState6(false);
		} else { //We have a student selected so display the assignment mark box's
			IStudent studentmManager = StudentManager.get().getStudent(sID);
			IStudentUnitRecord studentRecord = studentmManager.getUnitRecord(this.unitCode);

			this.CGUI.setRecord(studentRecord);
			this.CGUI.setState3(true);
			this.CGUI.setState4(true);
			this.CGUI.setState5(false);
			this.CGUI.setState6(false);
			this.changed = false;
		}
	}

	/**
	 * Checks the current students grade
	 * 
	 * @param asg1 the students mark for their first assignment
	 * @param asg2 the students mark for their second assignment
	 * @param exam the students mark for their exam
	 * @return {@link #String} representation of the current students grade
	 */
	public String checkGrade(float asg1, float asg2, float exam) {
		IUnit unit = UnitManager.UM().getUnit(this.unitCode);
		String grade = unit.getGrade(asg1, asg2, exam);
		
		this.CGUI.setState4(true);
		this.CGUI.setState5(false);
		
		if (this.changed) {
			this.CGUI.setState6(true);
		}
		
		return grade;
	}

	/**
	 * Allow the students marks to be changed
	 */
	public void enableChangeMarks() {
		this.CGUI.setState4(false);
		this.CGUI.setState6(false);
		this.CGUI.setState5(true);
		this.changed = true;
	}

	/**
	 * Save the users grade
	 * 
	 * @param asg1 the students mark for their first assignment
	 * @param asg2 the students mark for their second assignment
	 * @param exam the students mark for their exam
	 */
	public void saveGrade(float asg1, float asg2, float exam) {
		IUnit unit = UnitManager.UM().getUnit(this.unitCode); //Seems to be redundent leaving in for now untill I can confirm it isnt actually needed
		IStudent studentManager = StudentManager.get().getStudent(this.currentStudentID);
		
		IStudentUnitRecord studentRecord = studentManager.getUnitRecord(this.unitCode);
		studentRecord.setAsg1(asg1);
		studentRecord.setAsg2(asg2);
		studentRecord.setExam(exam);
		StudentUnitRecordManager.instance().saveRecord(studentRecord);
		
		this.CGUI.setState4(true);
		this.CGUI.setState5(false);
		this.CGUI.setState6(false);
	}
}
