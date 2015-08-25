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

	private StudentUnitRecordList studentUnitRecordList_;

	public Unit(String unitCode, String unitNumber, float passCutoff, float creditCutoff, float distinctionCutoff,
			float highDistinctionCutoff, float additionalExaminationCutoff, int assignment1Weight,
			int assignment2Weight, int examWeight, StudentUnitRecordList studentUnitRecordList) {

		unitCode_ = unitCode;
		unitName_ = unitNumber;
		passCutoff_ = passCutoff;
		creditCutoff_ = creditCutoff;
		distinctionCutoff_ = distinctionCutoff;
		highDistinctionCutoff_ = highDistinctionCutoff;
		additionalExaminationCutoff_ = additionalExaminationCutoff;
		setAssessmentWeights(assignment1Weight, assignment2Weight, examWeight);
		if (studentUnitRecordList == null) {
			studentUnitRecordList_ = new StudentUnitRecordList();
		} else {
			studentUnitRecordList_ = studentUnitRecordList;
		}
	}

	public String getUnitCode() {
		return this.unitCode_;
	}

	public String getUnitName() {

		return this.unitName_;
	}

	public void setPassCutoff(float cutoff) {
		this.passCutoff_ = cutoff;
	}

	public float getPassCutoff() {
		return this.passCutoff_;
	}

	public void setCreditCutoff(float cutoff) {
		this.creditCutoff_ = cutoff;
	}

	public float getCreditCutoff() {
		return this.creditCutoff_;
	}

	public void setDistinctionCutoff(float cutoff) {
		this.distinctionCutoff_ = cutoff;
	}

	public float getDistinctionCuttoff() {
		return this.distinctionCutoff_;
	}

	public void setHighDistinctionCutoff(float cutoff) {
		this.highDistinctionCutoff_ = cutoff;
	}

	public float getHighDistinctionCutoff() {
		return this.highDistinctionCutoff_;

	}

	public void setAdditionalExaminationCutoff(float cutoff) {
		this.additionalExaminationCutoff_ = cutoff;
	}

	public float getAdditionalExaminationCutoff() {
		return this.additionalExaminationCutoff_;
	}

	public void addStudentRecord(IStudentUnitRecord record) {
		studentUnitRecordList_.add(record);
	}

	public IStudentUnitRecord getStudentRecord(int studentID) {
		for (IStudentUnitRecord record : studentUnitRecordList_) {
			if (record.getStudentID() == studentID)
				return record;
		}
		return null;
	}

	public StudentUnitRecordList listStudentRecords() {
		return studentUnitRecordList_;
	}

	@Override
	public int getAssignment1Weight() {
		return assignment1Weight_;
	}

	@Override
	public int getAssignment2Weight() {
		return assignment2Weight_;
	}

	@Override
	public int getExamWeight() {
		return examWeight_;
	}

	@Override
	public void setAssessmentWeights(int assignment1Weight, int assignment2Weight, int examWeight) {
		if (assignment1Weight < 0 || assignment1Weight > 100 || assignment2Weight < 0 || assignment2Weight > 100
				|| examWeight < 0 || examWeight > 100) {
			throw new RuntimeException("Assessment weights cant be less than zero or greater than 100");
		}
		if (assignment1Weight + assignment2Weight + examWeight != 100) {
			throw new RuntimeException("Assessment weights must add to 100");
		}
		this.assignment1Weight_ = assignment1Weight;
		this.assignment2Weight_ = assignment2Weight;
		this.examWeight_ = examWeight;
	}

	private void setCutoffs(float pass, float credit, float distinction, float highDistinction,
			float additionalExamination) {
		if (pass < 0 || pass > 100 || credit < 0 || credit > 100 || distinction < 0 || distinction > 100
				|| highDistinction < 0 || highDistinction > 100 || additionalExamination < 0
				|| additionalExamination > 100) {
			throw new RuntimeException("Assessment cutoffs cant be less than zero or greater than 100");
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
		float total = assignment1Mark + assignment2Mark + examMark;

		if (assignment1Mark < 0 || assignment1Mark > assignment1Weight_ || assignment2Mark < 0
				|| assignment2Mark > assignment2Weight_ || examMark < 0 || examMark > examWeight_) {
			throw new RuntimeException("Marks cannot be less than zero or greater than assessment weights");
		}

		if (total < additionalExaminationCutoff_) {
			return "FL";
		} else if (total < passCutoff_)
			return "AE";
		else if (total < creditCutoff_)
			return "PS";
		else if (total < distinctionCutoff_)
			return "CR";
		else if (total < highDistinctionCutoff_)
			return "DI";
		else
			return "HD";
	}

}