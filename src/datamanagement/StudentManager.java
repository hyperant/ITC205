package datamanagement;

import org.jdom.*;
import java.util.List;

public class StudentManager {
	private static StudentManager self = null;

	private StudentMap studentMap;
	private java.util.HashMap<String, StudentMap> unitMap;

	public static StudentManager get() {
		if (self == null)

			self = new StudentManager();
		return self;
	}

	private StudentManager() {

		studentMap = new StudentMap();
		unitMap = new java.util.HashMap<>();
	}

	public IStudent getStudent(Integer studentID) {
		IStudent student = studentMap.get(studentID);
		return student != null ? student : createStudent(studentID);
	}

	@SuppressWarnings("unchecked")
	private Element getStudentElement(Integer studentID) {
		for (Element studentElement : (List<Element>) XMLManager.getXML().getDocument().getRootElement().getChild("studentTable").getChildren("student"))
			if (studentID.toString().equals(studentElement.getAttributeValue("sid")))
				return studentElement;
		return null;
	}

	private IStudent createStudent(Integer studentID) {
		IStudent student;
		Element studentElement = getStudentElement(studentID);
		if (studentElement != null) {
			StudentUnitRecordList rlist = StudentUnitRecordManager.instance().getRecordsByStudent(studentID);
			student = new Student(new Integer(studentElement.getAttributeValue("sid")), studentElement.getAttributeValue("fname"),
					studentElement.getAttributeValue("lname"), rlist);

			studentMap.put(student.getID(), student);
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
		StudentMap studentRecord = unitMap.get(unitCode);
		if (studentRecord != null) {

			return studentRecord;
		}

		studentRecord = new StudentMap();
		IStudent student;
		StudentUnitRecordList ur = StudentUnitRecordManager.instance().getRecordsByUnit(unitCode);
		for (IStudentUnitRecord S : ur) {

			student = createStudentProxy(new Integer(S.getStudentID()));
			studentRecord.put(student.getID(), student);
		}
		unitMap.put(unitCode, studentRecord);
		return studentRecord;
	}
}
