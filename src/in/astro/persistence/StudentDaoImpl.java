package in.astro.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.astro.dto.Student;
import in.astro.util.JdbcUtil;
//Persistence logic using JDBC API
public class StudentDaoImpl implements IStudentDao {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultset = null;
	Student student = null;
	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		String sqlInsertQuery = "insert into students(`name`,`age`,`address`) values (?,?,?)";
		try {
			connection = JdbcUtil.getConnection();
			if(connection!=null) {
				statement = connection.prepareStatement(sqlInsertQuery);
				if(statement!=null) {
					statement.setString(1, sname);
					statement.setInt(2, sage);
					statement.setString(3, saddress);
					int rowaffected = statement.executeUpdate();
					if(rowaffected == 1) return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		String query = "select id,name,age,address from students where id = ?";
		try {
			connection = JdbcUtil.getConnection();
			if(connection!=null) {
				statement = connection.prepareStatement(query);
				if(statement!=null) {
					statement.setInt(1, sid);
					resultset = statement.executeQuery();
					if(resultset!=null) {
						System.out.println(query);
						if(resultset.next()) {
							student = new Student();
							student.setSid(1);
							student.setSname(resultset.getString(2));
							student.setSage(resultset.getInt(3));
							student.setSaddress(resultset.getString(4));
							return student;
						}
					}
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteStudent(Integer sid) {
		String query = "delete from students where id=?";
		try {
			connection = JdbcUtil.getConnection();
			if(connection!=null) {
				statement = connection.prepareStatement(query);
				if(statement!=null) {
					statement.setInt(1, sid);
					int rowaffected = statement.executeUpdate();
					if(rowaffected == 1) return "success";
					else return "not found";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return "failure";
		}
		return "failure";
	}
}
