package employeesearch.dao;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import employeesearch.core.*;


public class EmployeeDAO {
	private Connection myConn;
	
	public EmployeeDAO() throws Exception{
	// get db properties
	Properties props = new Properties();
	props.load(new FileInputStream("demo.properties"));
	String user = props.getProperty("user");
	String password = props.getProperty("password");
	String dburl = props.getProperty("dburl");
	
	// connect to database
	myConn = DriverManager.getConnection(dburl, user, password);
	
	System.out.println("DB connection successful to: " + dburl);
}
	
	// update information for employees 
	
	public void updateEmployee(Employee theEmployee) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update employees"
					+ " set first_name=?, last_name=?, email=?, salary=?"
					+ " where id=?");
			
			// set params
			myStmt.setString(1, theEmployee.getFirstName());
			myStmt.setString(2, theEmployee.getLastName());
			myStmt.setString(3, theEmployee.getEmail());
			myStmt.setBigDecimal(4, theEmployee.getSalary());
			myStmt.setInt(5, theEmployee.getId());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	
	
	
	// add an employee to the database
	public void addEmployee(Employee theEmployee)throws Exception{
		PreparedStatement myStmt = null;
		try{
			// prepare statement
			myStmt = myConn.prepareStatement("insert into employees"
					+ "(first_name, last_name, email, salary)"
					+"values(?, ? , ?, ?)");
			
			// set parameters
			myStmt.setString(1, theEmployee.getFirstName());
			myStmt.setString(2, theEmployee.getLastName());
			myStmt.setString(3, theEmployee.getEmail());
			myStmt.setBigDecimal(4, theEmployee.getSalary());
			
			// execute sql
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt);
		}
		
	}
	
	// retrieve all fields from employees and make a list of employee objects
	public List<Employee> getAllEmployees() throws Exception{
		List<Employee> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from employees");
			
			while (myRs.next()) {
				Employee tempEmployee = convertRowToEmployee(myRs);
				list.add(tempEmployee);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	// retrieve a field that matches given employee
	
	public List<Employee> searchEmployees(String lastName) throws Exception {
		List<Employee> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from employees where last_name like ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Employee tempEmployee = convertRowToEmployee(myRs);
				list.add(tempEmployee);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
		
	
	
	
	
	
	
	
	
	// using ResultSet to retrieve filed for on employee table, and make a new employee
	private Employee convertRowToEmployee(ResultSet myRs)throws SQLException{
		int id = myRs.getInt("id");
		String lastName = myRs.getString("last_name");
		String firstName = myRs.getString("first_name");
		String email = myRs.getString("email");
		BigDecimal salary = myRs.getBigDecimal("salary");
		
		Employee tempEmployee = new Employee(id, lastName, firstName, email, salary);
		
		return tempEmployee;
	}
	

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close(); 
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}
	private void close(Statement myStmt) throws SQLException {
		close(null,myStmt,null);
	}
	
public static void main(String[] args) throws Exception {
	BigDecimal bd2 = new BigDecimal("0.01");
		EmployeeDAO dao = new EmployeeDAO();
		Employee e1= new Employee("jason","","",bd2);
		System.out.println(dao.searchEmployees("thom"));

		System.out.println(dao.getAllEmployees());
		dao.addEmployee(e1);
		System.out.println(dao.getAllEmployees()); // print out the result with added employee
	

}

}
