package datamanagement;

public class Unit implements IUnit {
	private String unitCode_;
	private String unitName_;
	private float passCutoff_;
	private float creditCutoff_;
	private float distinctionCutoff_;
	private float highDistinctionCutoff_;
	private float additionalExaminationCutoff_;
	private int assignment1Weight_, assignment2Weight_, examWeight_;

	private StudentUnitRecordList rs;

	public Unit(String unitCode, String unitNumber, float passCutoff, float creditCutoff, float distinctionCutoff, float highDistinctionCutoff,
			float additionalExaminationCutoff, int assignment1Weight, int assignment2Weight, int examWeight, StudentUnitRecordList rl) {

		unitCode_ = unitCode;
		unitName_ = unitNumber;
		passCutoff_ = passCutoff;
		creditCutoff_ = creditCutoff;
		distinctionCutoff_ = distinctionCutoff;
		highDistinctionCutoff_ = highDistinctionCutoff;
		additionalExaminationCutoff_ = additionalExaminationCutoff;
		setAssessmentWeights(assignment1Weight, assignment2Weight, examWeight);
		rs = rl == null ? new StudentUnitRecordList() : rl;
	}

	public String getUnitCode() {
		return this.unitCode_;
	}

	public String getUnitName() {

		return this.unitName_;
	}

	public void setPsCutoff(float cutoff) {
		this.passCutoff_ = cutoff;
	}

	public float getPsCutoff() {
		return this.passCutoff_;
	}

	public void setCrCutoff(float cutoff) {
		this.creditCutoff_ = cutoff;
	}

	public float getCrCutoff() {
		return this.creditCutoff_;
	}

	public void setDiCutoff(float cutoff) {
		this.distinctionCutoff_ = cutoff;
	}

	public float getDiCuttoff() {
		return this.distinctionCutoff_;
	}

	public void setHdCutoff(float cutoff) {
		this.highDistinctionCutoff_ = cutoff;
	}

	public float getHdCutoff() {
		return this.highDistinctionCutoff_;

	}

	public void setAeCutoff(float cutoff) {
		this.additionalExaminationCutoff_ = cutoff;
	}

	public float getAeCutoff() {
		return this.additionalExaminationCutoff_;
	}

	public void addStudentRecord(IStudentUnitRecord record) {
		rs.add(record);
	}

	public IStudentUnitRecord getStudentRecord(int studentID) {
		for (IStudentUnitRecord r : rs) {
			if (r.getStudentID() == studentID)
				return r;
		}
		return null;
	}

	public StudentUnitRecordList listStudentRecords() {
		return rs;
	}

	@Override
	public int getAsg1Weight() {
		return assignment1Weight_;
	}

	@Override
	public int getAsg2Weight() {
		return assignment2Weight_;
	}

	@Override
	public int getExamWeight() {
		return examWeight_;
	}

	@Override
	public void setAssessmentWeights(int assignment1Weight_, int assignment2Weight_, int examWeight_) {
		if (assignment1Weight_ < 0 || assignment1Weight_ > 100 || assignment2Weight_ < 0 || assignment2Weight_ > 100 || examWeight_ < 0 || examWeight_ > 100) {
			throw new RuntimeException(
					"Assessment weights cant be less than zero or greater than 100");
		}
		if (assignment1Weight_ + assignment2Weight_ + examWeight_ != 100) {
			throw new RuntimeException("Assessment weights must add to 100");
		}
		this.assignment1Weight_ = assignment1Weight_;
		this.assignment2Weight_ = assignment2Weight_;
		this.examWeight_ = examWeight_;
	}

	private void setCutoffs(float pass, float credit, float distinction, float highDistinction, float additionalExamination) {
		if (pass < 0 || pass > 100 || credit < 0 || credit > 100 || distinction < 0 || distinction > 100
				|| highDistinction < 0 || highDistinction > 100 || additionalExamination < 0 || additionalExamination > 100) {
			throw new RuntimeException(
					"Assessment cutoffs cant be less than zero or greater than 100");
		}
		if (additionalExamination >= pass) {
			throw new RuntimeException("AE cutoff must be less than PS cutoff");
		}
		if (pass >= credit) {
			throw new RuntimeException("PS cutoff must be less than CR cutoff");
		}
		if (credit >= distinction) {
			throw new RuntimeException("CR cutoff must be less than DI cutoff");
		}
		if (distinction >= highDistinction) {
			throw new RuntimeException("DI cutoff must be less than HD cutoff");
		}

	}

	public String getGrade(float assignment1Mark, float assignment2Mark, float examMark) {
		float t = assignment1Mark + assignment2Mark + examMark;

		if (assignment1Mark < 0 || assignment1Mark > assignment1Weight_ || assignment2Mark < 0 || assignment2Mark > assignment2Weight_ || examMark < 0 || examMark > examWeight_) {
			throw new RuntimeException(
					"marks cannot be less than zero or greater than assessment weights");
		}

		if (t < additionalExaminationCutoff_) {
			return "FL";
		} else if (t < passCutoff_)
			return "AE";
		else if (t < creditCutoff_)
			return "PS";
		else if (t < distinctionCutoff_)
			return "CR";
		else if (t < highDistinctionCutoff_)
			return "DI";
		else
			return "HD";
	}

}