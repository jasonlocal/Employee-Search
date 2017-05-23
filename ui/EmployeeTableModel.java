package employeesearch.ui;
import employeesearch.core.*;

import java.math.BigDecimal;
import java.util.*;
import javax.swing.table.AbstractTableModel;

class EmployeeTableModel extends AbstractTableModel {
	
	public static final int OBJECT_COL = -1;
	private static final int LAST_NAME_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int SALARY_COL = 3;

	private String[] columnNames = { "Last Name", "First Name", "Email",
			"Salary" };
	private List<Employee> employees;

	public EmployeeTableModel(List<Employee> theEmployees) {
		employees = theEmployees;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return employees.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row,int col) {

		Employee tempEmployee = employees.get(row);
	

		switch (col) {
		case LAST_NAME_COL:
			return tempEmployee.getLastName();
		case FIRST_NAME_COL:
			return tempEmployee.getFirstName();
		case EMAIL_COL:
			return tempEmployee.getEmail();
		case SALARY_COL:
			return tempEmployee.getSalary();
		default:
			return tempEmployee;
		}
		
	}
	
	

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public static void main(String[] args) throws Exception {
		BigDecimal bd2 = new BigDecimal("0.01");
		Employee e1 = new Employee("jason","zheng","jasonlocal",bd2);
		Employee e2 = new Employee("john","zheng","jasonlocal",bd2);
		List<Employee> es= new ArrayList<>();
		es.add(e1);
		es.add(e2);
		EmployeeTableModel et= new EmployeeTableModel(es);
		System.out.println(et.getValueAt(0,-1));
		
	}
	
	
}
