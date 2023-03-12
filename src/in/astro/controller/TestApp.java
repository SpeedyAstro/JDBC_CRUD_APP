package in.astro.controller;

import java.util.Scanner;

import in.astro.dto.Student;
import in.astro.service.IStudentService;
import in.astro.servicefactory.StudentServiceFactory;

public class TestApp {
	
	public static void main(String[] args) {
		selectOperation();
	}
	private static void insertOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		String msg = studentService.addStudent("Sachin", 49, "MI");
		
		if(msg.equals("success"))
			System.out.println("record Inserted Successfully");
		else 
			System.out.println("record Insertion Failed!!");
	}
	private static void selectOperation() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Student SID :");
		int sid = sc.nextInt();
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student std = studentService.searchStudent(sid);
		if(std!=null) {
			System.out.println(std);
			System.out.println("SID\tName\tAGE\tADDRESS");
			System.out.println(std.getSid()+"\t"+std.getSname()+"\t"+std.getSage()+"\t"+std.getSaddress());
		}else {
			System.out.println("Record not found!! ::"+sid);
		}
		sc.close();
	}

}
