package datamanagement;

import org.jdom.*;
import java.util.List;
import java.util.HashMap;

public class StudentManager {
	private static StudentManager self = null;

	private StudentMap studentMap_;
	private HashMap<String, StudentMap> unitMap_;

	public static StudentManager get() {
		if (self == null)

			self = new StudentManager();
		return self;
	}

	private StudentManager() {

		this.studentMap_ = new StudentMap();
		this.unitMap_ = new HashMap<>();
	}

	public IStudent getStudent(Integer studentID) {
		IStudent student = this.studentMap_.get(studentID);
		return student != null ? student : createStudent(studentID);
	}

	private Element getStudentElement(Integer studentID) {
		for (Object studentElement : ((List<?>) XMLManager.getXML().getDocument().getRootElement().getChild("studentTable").getChildren("student"))){
			if (studentID.toString().equals(((Element) studentElement).getAttributeValue("sid"))){
				return (Element) studentElement;
			}
		}
		return null;
	}

	private IStudent createStudent(Integer studentID) {
		IStudent student;
		Element studentElement = getStudentElement(studentID);
		if (studentElement != null) {
			StudentUnitRecordList recordList = StudentUnitRecordManager.instance().getRecordsByStudent(studentID);
			student = new Student(new Integer(studentElement.getAttributeValue("sid")), studentElement.getAttributeValue("fname"), studentElement.getAttributeValue("lname"), recordList);

			this.studentMap_.put(student.getID(), student);
			return student;
		}
		throw new RuntimeException("DBMD: createStudent : student not in file");
	}

	private IStudent createStudentProxy(Integer studentID) {
		Element studentElement = getStudentElement(studentID);

		if (studentElement != null)
			return new StudentProxy(studentID, studentElement.getAttributeValue("fname"), studentElement.getAttributeValue("lname"));
		throw new RuntimeException("DBMD: createStudent : student not in file");
	}

	public StudentMap getStudentsByUnit(String unitCode) {
		StudentMap studentRecord = unitMap_.get(unitCode);
		if (studentRecord != null) {

			return studentRecord;
		}

		studentRecord = new StudentMap();
		IStudent student;
		StudentUnitRecordList unitRecord = StudentUnitRecordManager.instance().getRecordsByUnit(unitCode);
		for (IStudentUnitRecord studentUnitRecord : unitRecord) {

			student = createStudentProxy(new Integer(studentUnitRecord.getStudentID()));
			studentRecord.put(student.getID(), student);
		}
		
		this.unitMap_.put(unitCode, studentRecord);
		return studentRecord;
	}
}
