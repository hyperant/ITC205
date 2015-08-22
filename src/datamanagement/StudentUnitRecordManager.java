package datamanagement;

import java.util.List;
import org.jdom.Element;

public class StudentUnitRecordManager {

	private static StudentUnitRecordManager studentUnitRecordManager_ = null;
	private StudentUnitRecordMap recordMap_;
	private java.util.HashMap<String, StudentUnitRecordList> unitCodeRecord_;
	private java.util.HashMap<Integer, StudentUnitRecordList> studentIdRecord_;

	public static StudentUnitRecordManager instance() {
		if (studentUnitRecordManager_ == null) {
			studentUnitRecordManager_ = new StudentUnitRecordManager();
		}
		return studentUnitRecordManager_;
	}

	private StudentUnitRecordManager() {
		recordMap_ = new StudentUnitRecordMap();
		unitCodeRecord_ = new java.util.HashMap<>();
		studentIdRecord_ = new java.util.HashMap<>();
	}

	public IStudentUnitRecord getStudentUnitRecord(Integer studentID, String unitCode) {
		IStudentUnitRecord iStudentUnitRecord = recordMap_.get(studentID.toString() + unitCode);
		if (iStudentUnitRecord == null) {
			iStudentUnitRecord = createStudentUnitRecord(studentID, unitCode);
		}
		return iStudentUnitRecord;
	}

	private IStudentUnitRecord createStudentUnitRecord(Integer unitCode, String studentId) {
		IStudentUnitRecord iStudentUnitRecord;
		for (Object studentUnitRecordElement : (List<?>) XMLManager.getXML().getDocument().getRootElement()
				.getChild("studentUnitRecordTable").getChildren("record")) {
			if (unitCode.toString().equals(((Element) studentUnitRecordElement).getAttributeValue("sid"))
					&& studentId.equals(((Element) studentUnitRecordElement).getAttributeValue("uid"))) {
				iStudentUnitRecord = new StudentUnitRecord(
						new Integer(((Element) studentUnitRecordElement).getAttributeValue("sid")),
						((Element) studentUnitRecordElement).getAttributeValue("uid"),
						new Float(((Element) studentUnitRecordElement).getAttributeValue("asg1")).floatValue(),
						new Float(((Element) studentUnitRecordElement).getAttributeValue("asg2")).floatValue(),
						new Float(((Element) studentUnitRecordElement).getAttributeValue("exam")).floatValue());
				recordMap_.put(iStudentUnitRecord.getStudentID().toString() + iStudentUnitRecord.getUnitCode(),
						iStudentUnitRecord);
				return iStudentUnitRecord;
			}
		}
		throw new RuntimeException("DBMD: createStudent : student unit record not in file");
	}

	public StudentUnitRecordList getRecordsByUnit(String unitCode) {
		StudentUnitRecordList studentUnitRecordList = unitCodeRecord_.get(unitCode);
		if (studentUnitRecordList != null) {
			return studentUnitRecordList;
		}
		studentUnitRecordList = new StudentUnitRecordList();
		for (Object unitRecordElement : (List<?>) XMLManager.getXML().getDocument().getRootElement()
				.getChild("studentUnitRecordTable").getChildren("record")) {
			if (unitCode.equals(((Element) unitRecordElement).getAttributeValue("uid"))) {
				studentUnitRecordList.add(
						new StudentUnitRecordProxy(new Integer(((Element) unitRecordElement).getAttributeValue("sid")),
								((Element) unitRecordElement).getAttributeValue("uid")));
			}
		}
		if (studentUnitRecordList.size() > 0) {
			unitCodeRecord_.put(unitCode, studentUnitRecordList); // be careful - this could be empty
		}
		return studentUnitRecordList;
	}

	public StudentUnitRecordList getRecordsByStudent(Integer studentID) {
		StudentUnitRecordList studentUnitRecordList = studentIdRecord_.get(studentID);
		if (studentUnitRecordList != null) {
			return studentUnitRecordList;
		}
		studentUnitRecordList = new StudentUnitRecordList();
		for (Object studentRecordElement : (List<?>) XMLManager.getXML().getDocument().getRootElement()
				.getChild("studentUnitRecordTable").getChildren("record"))
			if (studentID.toString().equals(((Element) studentRecordElement).getAttributeValue("sid"))) {
				studentUnitRecordList.add(new StudentUnitRecordProxy(
						new Integer(((Element) studentRecordElement).getAttributeValue("sid")),
						((Element) studentRecordElement).getAttributeValue("uid")));
			}
		if (studentUnitRecordList.size() > 0) {
			studentIdRecord_.put(studentID, studentUnitRecordList); // be careful - this could be empty
		}
		return studentUnitRecordList;
	}

	public void saveRecord(IStudentUnitRecord irec) {
		for (Object recordElement : (List<?>) XMLManager.getXML().getDocument().getRootElement()
				.getChild("studentUnitRecordTable").getChildren("record")) {
			if (irec.getStudentID().toString().equals(((Element) recordElement).getAttributeValue("sid"))
					&& irec.getUnitCode().equals(((Element) recordElement).getAttributeValue("uid"))) {
				((Element) recordElement).setAttribute("asg1", new Float(irec.getAsg1()).toString());

				((Element) recordElement).setAttribute("asg2", new Float(irec.getAsg2()).toString());
				((Element) recordElement).setAttribute("exam", new Float(irec.getExam()).toString());
				XMLManager.getXML().saveDocument(); // write out the XML file
													// for continuous save
				return;
			}
		}

		throw new RuntimeException("DBMD: saveRecord : no such student record in data");
	}
}
