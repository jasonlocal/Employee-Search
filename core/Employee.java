package employeesearch.core;
import java.math.BigDecimal;

public class Employee {
	private int id;
	private String lastName; 
	private String firstName;
	private String email;
	private BigDecimal salary;
	
public Employee(int id, String lastName, String firstName, String email, 
		BigDecimal salary){
	super(); // ?????? why use super
	this.id= id; 
	this.lastName=lastName;
	this.firstName=firstName;
	this.email=email;
	this.salary=salary;
}
public Employee(String lastName, String firstName, String email,
		BigDecimal salary) {

	this(0, lastName, firstName, email, salary);
}



public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public BigDecimal getSalary() {
	return salary;
}

public void setSalary(BigDecimal salary) {
	this.salary = salary;
}

@Override
public String toString() {
	return String
			.format("Employee [id=%s, lastName=%s, firstName=%s, email=%s, salary=%s]",
					id, lastName, firstName, email, salary);


}

public static void main(String[] args) throws Exception{
	BigDecimal bd2 = new BigDecimal("0.01");
	Employee e1= new Employee(1, "jason", "Zheng", "Jasonlocal@hotmail",
		bd2	);
	System.out.println(e1.toString());
}

}



