package in.astro.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.NonReadableChannelException;
import java.util.Scanner;


import in.astro.dto.Student;
import in.astro.service.IStudentService;
import in.astro.servicefactory.StudentServiceFactory;

public class TestApp {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("Enter Choice :");
			System.out.println("Press 1 for Insert operation");
			System.out.println("Press 2 for select operation");
			System.out.println("Press 3 for Update operation");
			System.out.println("Press 4 for Delete operation");
			System.out.println("Press 5 for Exit");
			int choice = Integer.parseInt(br.readLine());
			switch (choice) {
			case 1: {
				insertOperation();
				break;
			}case 2:{
				selectOperation();
				break;
			}case 3:{
				UpdateOperation();
				break;
			}case 4:{
				DeleteOperation();
				break;
			}case 5:{
				System.out.println("Program Terminated Successfully!!");
				System.exit(0);
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		}
	}
	@SuppressWarnings("unused")
	private static void insertOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter name ::");
		String name = scanner.next();
		System.out.println("Enter age :: ");
		int age = scanner.nextInt();
		System.out.println("Enter Address ::");
		String address = scanner.next();
		String msg = studentService.addStudent(name,age,address);
		
		if(msg.equals("success"))
			System.out.println("record Inserted Successfully");
		else 
			System.out.println("record Insertion Failed!!");
	}
	@SuppressWarnings("unused")
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
	}
	@SuppressWarnings("unused")
	private static void DeleteOperation() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Student ID");
		int sid = scanner.nextInt();
		IStudentService studentService = StudentServiceFactory.getStudentService();
		String msg =  studentService.deleteStudent(sid);
		if (msg.equalsIgnoreCase("success"))
			System.out.println("Success");
		else if (msg.equalsIgnoreCase("Not found"))
			System.out.println("record not available for given id ::"+sid);
		else System.out.println("record deletion failed ..");
	}
	private static void  UpdateOperation() throws IOException {
		Student newStudent = new Student();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Student ID to be updated ::");
		String sid = br.readLine();
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student student = studentService.searchStudent(Integer.parseInt(sid));
		if(student!=null) {
			newStudent.setSid(Integer.parseInt(sid));
			System.out.println("Student id is :"+sid);
			System.out.println("Student Old Name is ::"+student.getSname()+"  Enter new name ::	");
			String newName = br.readLine();
			if (newName.equals("")||newName=="") {
				newStudent.setSname(student.getSname());
			} else {
				newStudent.setSname(newName);
			}
			System.out.println("Student Old Age is ::"+student.getSage()+"  Enter new age ::	");
			String newage = br.readLine();
			if (newage.equals("")||newage=="") {
				newStudent.setSage(student.getSage());
			} else {
				newStudent.setSage(Integer.parseInt(newage));
			}
			System.out.println("Student Old address is ::"+student.getSaddress()+"  Enter new address ::	");
			String newaddress = br.readLine();
			if (newaddress.equals("")||newaddress=="") {
				newStudent.setSaddress(student.getSaddress());
			} else {
				newStudent.setSname(newaddress);
			}
			String status = studentService.updateStudent(newStudent);
			if(status.equals("success"))
				System.out.println("record Updated Successfully");
			else 
				System.out.println("record Updation Failed!! , of id ::"+sid);
		}else {
			System.out.println("Studnet record not available for given id +"+sid);
		}
	}

}
