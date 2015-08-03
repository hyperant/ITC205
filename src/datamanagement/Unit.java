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

	public Unit(String UC, String un, float f1, float f2, float f3, float f4,
			float f5, int i1, int i2, int i3, StudentUnitRecordList rl) {

		unitCode_ = UC;
		unitName_ = un;
		passCutoff_ = f1;
		creditCutoff_ = f2;
		this.distinctionCutoff_ = f3;
		highDistinctionCutoff_ = f4;
		this.additionalExaminationCutoff_ = f5;
		this.setAssessmentWeights(i1, i2, i3);
		rs = rl == null ? new StudentUnitRecordList() : rl;
	}

	public String getUnitCode() {
		return this.unitCode_;
	}

	public String getUnitName() {

		return this.unitName_;
	}

	public void setPsCutoff1(float cutoff) {
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

	public void HDCutoff(float cutoff) {
		this.highDistinctionCutoff_ = cutoff;
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

	private void setCutoffs(float ps, float cr, float di, float hd, float ae) {
		if (ps < 0 || ps > 100 || cr < 0 || cr > 100 || di < 0 || di > 100
				|| hd < 0 || hd > 100 || ae < 0 || ae > 100) {
			throw new RuntimeException(
					"Assessment cutoffs cant be less than zero or greater than 100");
		}
		if (ae >= ps) {
			throw new RuntimeException("AE cutoff must be less than PS cutoff");
		}
		if (ps >= cr) {
			throw new RuntimeException("PS cutoff must be less than CR cutoff");
		}
		if (cr >= di) {
			throw new RuntimeException("CR cutoff must be less than DI cutoff");
		}
		if (di >= hd) {
			throw new RuntimeException("DI cutoff must be less than HD cutoff");
		}

	}

	public String getGrade(float f1, float f2, float f3) {
		float t = f1 + f2 + f3;

		if (f1 < 0 || f1 > assignment1Weight_ || f2 < 0 || f2 > assignment2Weight_ || f3 < 0 || f3 > examWeight_) {
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