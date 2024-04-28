package com.admin.employeemanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.admin.employeemanagement.bean.DummyEmployee;
import com.admin.employeemanagement.bean.Employee;

public class EmployeeDao 
{
	// Credentials
		private String jdbcURL = "jdbc:mysql://localhost:3306/EmployeeDB?useSSL=false";
		private String jdbcUsername = "root";
		private String jdbcPassword = "";
		private String jdbcDriver = "com.mysql.jdbc.Driver";
		
		private static final String ADD_EMPLOYEE = "INSERT INTO employee (Emp_Name,address,gender,salary,birthdate) VALUES (?,?,?,?,?)";
		private static final String ALL_EMPLOYEE = "SELECT * FROM employee";
		private static final String Del_EMPLOYEE = "DELETE FROM employee where employeeId=?";
		private static final String SELECT_EMPLOYEE = "SELECT * FROM employee WHERE employeeId=?";
		
		public EmployeeDao() {
			
		}
		
		protected Connection getConnection()
		{
			Connection connection=null;
			try {
				Class.forName(jdbcDriver);
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			return connection;
		}
		
	    //--------------- Add Employee --------------------//
		public void addEmployee(Employee employee)
		{
			System.out.println("Adding an Employee :)");
			
			try( Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(ADD_EMPLOYEE)){
						statement.setString(1, employee.getName());
						statement.setString(2, employee.getAddress());
						statement.setByte(3, employee.getGender());
						statement.setDouble(4, employee.getSalary());
						java.sql.Date sqlDate = new java.sql.Date(employee.getBirthdate().getTime());
						statement.setDate(5, sqlDate);
						statement.executeUpdate();
			}
			catch (SQLException e) {
				printSQLException(e);
			}
				
		}
		
		//--------------- Delete Employee -----------------//
		public boolean deleteEmployee(Integer employeeId) throws SQLException
		{
			boolean deleted;
			try(
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(Del_EMPLOYEE);)
			{
				statement.setInt(1, employeeId);
				deleted = statement.executeUpdate() > 0;
			}
			return deleted;
		}
		
		//--------------- Employee List -------------------//
		public List<DummyEmployee> getEmployee(int indicator)
		{
			System.out.println("Getting Employee List");
			List<DummyEmployee> employees = new ArrayList<DummyEmployee>();
			try(
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(ALL_EMPLOYEE);)
			{
				ResultSet resultSet = statement.executeQuery();
				
				resultSet.absolute(indicator);
				int i = indicator + 5;
				// Putting Data to Employee List
				while(resultSet.next() && indicator < i) {
					System.out.println("Employee Id : "+resultSet.getInt("employeeId"));
					System.out.println("Gender : "+resultSet.getInt("gender"));
					Integer sr = ++indicator;
					Integer employeeId = resultSet.getInt("employeeId");
					String name = resultSet.getString("Emp_Name");
					String address = resultSet.getString("address");
					Byte gender = resultSet.getByte("gender");
					Double salary = resultSet.getDouble("salary");
					Date birthdate = resultSet.getDate("birthdate");
					employees.add(new DummyEmployee(sr,employeeId, name, address, gender, salary, birthdate));
				}
			}
			catch (SQLException e) {
				printSQLException(e);
			}
			return employees;
		}
		
		public List<Employee> selectEmployee(int id)
		{
			List<Employee> employee = new ArrayList<Employee>();
			try (
					Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEE);) 
			{
				statement.setInt(1, id);
				ResultSet resultSet = statement.executeQuery();
				if(resultSet.next())
				{
					Integer employeeId = resultSet.getInt("employeeId");
					String name = resultSet.getString("Emp_Name");
					String address = resultSet.getString("address");
					Byte gender = resultSet.getByte("gender");
					Double salary = resultSet.getDouble("salary");
					Date birthdate = resultSet.getDate("birthdate");
					
					employee.add(new Employee(employeeId, name, address, gender, salary, birthdate));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return employee;
		}
		
		private void printSQLException(SQLException ex) 
		{
			for(Throwable e : ex) {
				if(e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState : "+ ((SQLException) e).getSQLState());
					System.err.println("Error Code : "+ ((SQLException) e).getErrorCode());
					System.err.println("Message : "+ e.getMessage());
					Throwable t = ex.getCause();
					while(t != null) {
						System.out.println("Cause : "+t);
						t = t.getCause();
					}
				}
			}
		}
}
