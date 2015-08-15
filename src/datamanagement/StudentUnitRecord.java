package datamanagement;

/**
 * @author spider
 *
 */
public class StudentUnitRecord implements IStudentUnitRecord {
	private Integer studentID_;
	private String unitCode_;
	private float asg1_, asg2_, exam_;

	/**
	 * The constructor method for the class.
	 * 
	 * @param studentID the student ID
	 * @param unitCode the unitCode for the subject
	 * @param asg1 assignment 1 mark
	 * @param asg2 assignment 2 mark
	 * @param exam exam mark
	 */
	public StudentUnitRecord(Integer studentID, String unitCode, float asg1, float asg2, float exam) {
		this.studentID_ = studentID;
		this.unitCode_ = unitCode;
		this.setAsg1(asg1);
		this.setAsg2(asg2);
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
	 * @param asg1 assignment 1 value
	 * @exception RuntimeException on invalid assignment mark
	 * @see RuntimeException
	 */
	public void setAsg1(float asg1) {
		if (asg1 < 0 || asg1 > UnitManager.UM().getUnit(this.unitCode_).getAsg1Weight()) {
			throw new RuntimeException(
					"Mark cannot be less than zero or greater than assessment weight");
		}
		
		this.asg1_ = asg1;
	}

	/**
	 * gets assignment 1 mark
	 * 
	 * @return assignment 1 mark
	 */
	public float getAsg1() {
		return this.asg1_;
	}

	/**
	 * Sets assignment 2 value
	 * 
	 * @param asg2 assignment 2 value
	 * @exception RuntimeException on invalid assignment mark
	 * @see RuntimeException
	 */
	public void setAsg2(float asg2) {
		if (asg2 < 0 || asg2 > UnitManager.UM().getUnit(this.unitCode_).getAsg2Weight()) {
			throw new RuntimeException(
					"Mark cannot be less than zero or greater than assessment weight");
		}
		
		this.asg2_ = asg2;
	}

	/**
	 * gets assignment 2 mark
	 * 
	 * @return assignment 2 mark
	 */
	public float getAsg2() {
		return this.asg2_;
	}

	/**
	 * Sets exam value
	 * 
	 * @param exam exam value
	 * @exception RuntimeException on invalid exam mark
	 * @see RuntimeException
	 */
	public void setExam(float exam) {
		if (exam < 0 || exam > UnitManager.UM().getUnit(this.unitCode_).getExamWeight()) {
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
		return this.asg1_ + this.asg2_ + this.exam_;
	}
}
