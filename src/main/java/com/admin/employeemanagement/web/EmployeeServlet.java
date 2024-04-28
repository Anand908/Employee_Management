package com.admin.employeemanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.employeemanagement.bean.DummyEmployee;
import com.admin.employeemanagement.bean.Employee;
import com.admin.employeemanagement.dao.EmployeeDao;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EmployeeDao employeeDao;
    
	public void init() throws ServletException 
	{
		employeeDao = new EmployeeDao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getServletPath();
		System.out.println("Inside Controller : "+action);
		switch (action) 
		{
			case "/new":{ 
				newForm(request, response);
				break;
			}
			case "/insert":{ 
				try {
					addEmployee(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "/delete":{
				deleteForm(request, response);
				break;
			}case "/deleteData":{
				try {
					deleteEmployee(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			default:
			try {
				employeeList(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		}
	}
	
	private void newForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("\nInside new Form");
		RequestDispatcher dispatcher = request.getRequestDispatcher("Employee_Form.jsp");
		dispatcher.forward(request, response);
	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
	{
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		Byte gender = Byte.parseByte(request.getParameter("gender"));
		Double salary = Double.parseDouble(request.getParameter("salary"));
		System.out.println("Form Date"+request.getParameter("birthdate"));
		Date birthdate = getDate(request.getParameter("birthdate"));
		System.out.println("Calendar Date of Util : "+new SimpleDateFormat("dd-MM-yyyy ").format(new Date()).toString());
		Employee employee = new Employee(name, address, gender, salary, birthdate);
		employeeDao.addEmployee(employee);
		
		response.sendRedirect("List");
	}
	
	private Date getDate(String birthDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
	    try {  
	        date = formatter.parse(birthDate.split("-")[2]+"/"+birthDate.split("-")[1]+"/"+birthDate.split("-")[0]); 
	    } catch (ParseException e) {e.printStackTrace();}  
	    System.out.println("Util Date : "+formatter.format(date).toString());
		return date;
	}
	
	private void deleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("\nInside Delete Form");
		Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
		try {
			List<Employee> employee = employeeDao.selectEmployee(employeeId);
			request.setAttribute("employee", employee);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Delete.jsp");
			dispatcher.forward(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException
	{
		Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
		try {
			employeeDao.deleteEmployee(employeeId);
			System.out.println("Data deleted successfully for id : "+employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("List");
	}
	
	// default
	private void employeeList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		try {
			int i=0;
			try {i = Integer.parseInt(request.getParameter("sr"));}
			catch (Exception e) {}
			System.out.println("Value of i : "+i);
			if(i<0) i=0;
			List<DummyEmployee> list = employeeDao.getEmployee(i);
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Employee_list.jsp");
			dispatcher.forward(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
