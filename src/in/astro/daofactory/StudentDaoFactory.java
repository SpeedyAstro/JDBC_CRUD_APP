package in.astro.daofactory;

import in.astro.persistence.IStudentDao;
import in.astro.persistence.StudentDaoImpl;

public class StudentDaoFactory {
	private StudentDaoFactory() {
		
	}
	private static IStudentDao studentDao = null;
	
	public static IStudentDao getStudentDao() {
		if(studentDao==null)
			studentDao = new StudentDaoImpl();
		return studentDao;
	}
}
