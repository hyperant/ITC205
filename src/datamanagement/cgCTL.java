package datamanagement;

/**
 * This class is responsible for setting up the GUI that is displayed to the end user
 * 
 * @author Jason Fletcher
 *
 */
public class cgCTL {

	private cgUI CGUI;
	private String cuc = null;
	private Integer currentStudentID = null;
	private boolean changed = false;

	/**
	 * The construtore method for the class. This class accepts no input, and only creates a new instance of this class.
	 * </br></br>
	 * In order to display the GUI to the end user you must call .execute()
	 * </br>
	 * Example:</br>
	 * <code>
	 * new cgCTL().execute();
	 * </code>
	 */
	public cgCTL() {
	}

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

	public void unitSelected(String code) {
		if (code.equals("NONE")) {
			this.CGUI.setState2(false);
		} else {
			ListStudentsCTL lsCTL = new ListStudentsCTL();
			
			lsCTL.listStudents(this.CGUI, code);
			this.cuc = code;
			this.CGUI.setState2(true);
		}
		
		this.CGUI.setState3(false);
	}

	public void studentSelected(Integer sID) {
		this.currentStudentID = sID;
		
		if (this.currentStudentID.intValue() == 0) {
			this.CGUI.Refresh3();
			this.CGUI.setState3(false);
			this.CGUI.setState4(false);
			this.CGUI.setState5(false);
			this.CGUI.setState6(false);
		} else {
			IStudent studentmManager = StudentManager.get().getStudent(sID);
			IStudentUnitRecord studentRecord = studentmManager.getUnitRecord(this.cuc);

			this.CGUI.setRecord(studentRecord);
			this.CGUI.setState3(true);
			this.CGUI.setState4(true);
			this.CGUI.setState5(false);
			this.CGUI.setState6(false);
			this.changed = false;
		}
	}

	public String checkGrade(float f, float g, float h) {
		IUnit unit = UnitManager.UM().getUnit(this.cuc);
		String grade = unit.getGrade(f, g, h);
		
		this.CGUI.setState4(true);
		this.CGUI.setState5(false);
		
		if (this.changed) {
			this.CGUI.setState6(true);
		}
		
		return grade;
	}

	public void enableChangeMarks() {
		this.CGUI.setState4(false);
		this.CGUI.setState6(false);
		this.CGUI.setState5(true);
		this.changed = true;
	}

	public void saveGrade(float asg1, float asg2, float exam) {
		IUnit unit = UnitManager.UM().getUnit(this.cuc);
		IStudent studentManager = StudentManager.get().getStudent(this.currentStudentID);
		
		IStudentUnitRecord r = studentManager.getUnitRecord(this.cuc);
		r.setAsg1(asg1);
		r.setAsg2(asg2);
		r.setExam(exam);
		StudentUnitRecordManager.instance().saveRecord(r);
		
		this.CGUI.setState4(true);
		this.CGUI.setState5(false);
		this.CGUI.setState6(false);
	}
}
