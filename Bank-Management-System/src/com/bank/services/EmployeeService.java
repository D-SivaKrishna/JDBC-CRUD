package com.bank.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.bank.entities.Employee;

public class EmployeeService
{

	static Scanner sc=new Scanner(System.in);
	
	private static Connection con=null;
	
	//private static Statement stmt=null;  ====enter values manually==========
	
	
	private static PreparedStatement pstmt=null;
	private static ResultSet rs=null;

	final static String url="jdbc:mysql://localhost:3306/bank";
	final static String user="root";
	final static String password="admin";

	//final static String query="insert into employee (name,phone,email,city) values('Siva',98765432,'sk@gmail.com','banglore')";
	final static String query="insert into employee (name,phone,email,city) values(?,?,?,?)";
	
	private static final String UPDATE = "UPDATE employee SET email = ?, phone = ?, city = ? WHERE Id = ?";
	
	final static String DELETE ="DELETE from employee where Id =?";
	
	final static String show="select * from employee";
	
	//======================Insert the data======================================
	
	static void insert()
	{
		try
		{
			
			//=========Step 3================================
			
			pstmt= con.prepareStatement(query);

			System.out.println("Enter the Employee name..!!");
			String name=sc.nextLine();

			System.out.println("Enter the employee Phone number..!!!");
			long phone=sc.nextLong();

			System.out.println("Enter the Employee Email..!!");
			String email=sc.next();

			System.out.println("Enter the Employee City..!!");
			String city=sc.next();


			pstmt.setString(1, name);
			pstmt.setLong(2, phone);
			pstmt.setString(3, email);
			pstmt.setString(4, city);

			System.out.println("Created platform or Statement Successfully..!!!");

			//=======step 4=======================================

			int result=pstmt.executeUpdate();
			System.out.println(result);
			System.out.println("Execute the Sql query..!!!");


		} catch ( SQLException e1) {
			e1.printStackTrace();
		}
	}

	//=======================Update the data==========================================

	static void update() throws SQLException
	{
		System.out.println("Enter details to update: Email, Phone, or City");
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter phone: ");
        Long phone = sc.nextLong();
        sc.nextLine(); // Consume newline left-over
        System.out.print("Enter city: ");
        String city = sc.nextLine();
        System.out.print("Enter id: ");
        int id = sc.nextInt();

       
        pstmt = con.prepareStatement(UPDATE);

        
        pstmt.setString(1, email);
        pstmt.setLong(2, phone);
        pstmt.setString(3, city);
        pstmt.setInt(4, id);

        
        int rowsUpdated = pstmt.executeUpdate();
        System.out.println(rowsUpdated + " rows updated.");
	}
	
//===========================Delete the data=================================
	
	static void Delete() throws SQLException
	{
		pstmt=con.prepareStatement(DELETE);
		
		System.out.println("Enter the Employee Id ..");
		int id = sc.nextInt();
		pstmt.setInt(1,id);
		int ed = pstmt.executeUpdate();
		System.out.println(ed);
	}

	// ===================Display the data===================================
	
	static void Display()
	{
		try {

			pstmt=con.prepareStatement(show);
			rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			for(int i=1;i<=metaData.getColumnCount();i++)
			{

				System.out.print(metaData.getColumnName(i)+"    ");
			}
			System.out.println();

			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				long phone = rs.getLong("phone");
				String email = rs.getString("email");
				String city = rs.getString("city");

				System.out.println("----------------------------------------------");
				System.out.printf("%-3d|%-6s|%-6d|%-4s|%s\n",id,name,phone,email,city);
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		try {

			// ==============Step 1=============================

			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Load and Register the Driver class..!!!");

			// ===============Step 2============================

			//con=DriverManager.getConnection("jdbc.mysql://localhost:3306/bank?user:root&password:admin");

			con=DriverManager.getConnection(url, user, password);

			System.out.println("Established Connection between java application and database server Successfully...!!!!");

			//Display();
			//insert();
			update();
			//Delete();
			
			}
		catch(ClassNotFoundException | SQLException e1)
			{
				e1.printStackTrace();
			}


		finally {

			//  ===========Step 6==============================

				System.out.println("Closed all costly resources..!!!");
				try {
					pstmt.close();
					} 
				catch (SQLException e1) 
					{
					e1.printStackTrace();
					}
	
				try {
					con.close();
					} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
	}
}
