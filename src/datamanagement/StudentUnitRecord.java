package datamanagement;

/**
 * @author spider
 *
 */
public class StudentUnitRecord implements IStudentUnitRecord {
	private Integer studentID_;
	private String unitCode_;
	private float assignment1_, assignment2_, exam_;

	/**
	 * The constructor method for the class.
	 *
	 * @param studentID the student ID
	 * @param unitCode the unitCode for the subject
	 * @param assignment1 assignment 1 mark
	 * @param assignment2 assignment 2 mark
	 * @param exam exam mark
	 */
	public StudentUnitRecord(Integer studentID, String unitCode, float assignment1, float assignment2, float exam) {
		this.studentID_ = studentID;
		this.unitCode_ = unitCode;
		this.setAssignment1(assignment1);
		this.setAssignment2(assignment2);
		this.setExam(exam);
	}

	/**
	 * gets the current students ID
	 *
	 * @return {@link #Integer} students ID
	 */
	public Integer getStudentID() {
		return this.studentID_;
	}

	/**
	 * gets the current subject code
	 *
	 * @return {@link #String} subject code
	 */
	public String getUnitCode() {
		return this.unitCode_;
	}

	/**
	 * Sets assignment 1 value
	 *
	 * @param assignment1 assignment 1 value
	 * @exception RuntimeException on invalid assignment mark
	 * @see RuntimeException
	 */
	public void setAssignment1(float assignment1) {
		if (assignment1 < 0 || assignment1 > UnitManager.unitManager().getUnit(this.unitCode_).getAssignment1Weight()) {
			throw new RuntimeException(
					"Mark cannot be less than zero or greater than assessment weight");
		}

		this.assignment1_ = assignment1;
	}

	/**
	 * gets assignment 1 mark
	 *
	 * @return assignment 1 mark
	 */
	public float getAssignment1() {
		return this.assignment1_;
	}

	/**
	 * Sets assignment 2 value
	 *
	 * @param asg2 assignment 2 value
	 * @exception RuntimeException on invalid assignment mark
	 * @see RuntimeException
	 */
	public void setAssignment2(float assignment2) {
		if (assignment2 < 0 || assignment2 > UnitManager.unitManager().getUnit(this.unitCode_).getAssignment2Weight()) {
			throw new RuntimeException(
					"Mark cannot be less than zero or greater than assessment weight");
		}

		this.assignment2_ = assignment2;
	}

	/**
	 * gets assignment 2 mark
	 *
	 * @return assignment 2 mark
	 */
	public float getAssignment2() {
		return this.assignment2_;
	}

	/**
	 * Sets exam value
	 *
	 * @param exam exam value
	 * @exception RuntimeException on invalid exam mark
	 * @see RuntimeException
	 */
	public void setExam(float exam) {
		if (exam < 0 || exam > UnitManager.unitManager().getUnit(this.unitCode_).getExamWeight()) {
			throw new RuntimeException(
					"Mark cannot be less than zero or greater than assessment weight");
		}

		this.exam_ = exam;

	}

	/**
	 * gets exam mark
	 *
	 * @return exam mark
	 */
	public float getExam() {
		return this.exam_;
	}

	/**
	 * gets the total mark for assignment 1, assignment2, and exam
	 *
	 * @return the sum of assignment 1, assignment 2, and exam
	 */
	public float getTotal() {
		return this.assignment1_ + this.assignment2_ + this.exam_;
	}
}
