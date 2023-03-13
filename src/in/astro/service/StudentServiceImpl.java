package in.astro.service;

import in.astro.daofactory.StudentDaoFactory;
import in.astro.dto.Student;
import in.astro.persistence.IStudentDao;

public class StudentServiceImpl implements IStudentService {
	IStudentDao studentDao;
	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		studentDao = StudentDaoFactory.getStudentDao();
		if(studentDao!=null) return studentDao.addStudent(sname, sage, saddress);
		else return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.searchStudent(sid);
	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
		return null;
	}

	@Override
	public String deleteStudent(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.deleteStudent(sid);
	}

}
