package databasetesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class DataTest  {
	
	WebDriver driver =new ChromeDriver();
	Connection con ;
	Statement stmt ;
	ResultSet rs ;
	Random rand = new Random ();
	Random rand2 = new Random ();

	
	
	@BeforeTest 
	
	public void mySetup () throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","thair912000@@");
	}
	@Test (priority = 1)
	public void AddToThedataBase() throws SQLException {
		int randomnumber = rand.nextInt(889) * rand2.nextInt(453);
		System.out.println(randomnumber);
		String query = "insert into customers(customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit)values(505,'Sunrise Ventures','Anderson','Michael','212-555-1234','123 Main Street','Suit 200','New York','NY','10001','USA',1621,500000.00)";
//		String query2 = "update customers set customerNumber = randomnumber where customername ='Sunrise Ventures'"
		stmt=con.createStatement();
		int rowInserted = stmt.executeUpdate(query);
//		int rowupdate = stmt.executeUpdate(query);
		System.out.println(rowInserted);
//		System.out.println(rowupdate);
	}
	

	@Test (priority = 2)
	public void updateData() throws SQLException{
		String query = "update customers set contactFirstName ='nooralhuda' where customerNumber = 505 ";
		
		stmt=con.createStatement();
		int NumberOfRowUpdated = stmt.executeUpdate(query);
		System.out.println(NumberOfRowUpdated);
	}
	
	@Test (priority=3)
	public void myFirstTestGetData () throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from customers where customerNumber=505");
		
		while(rs.next()) {
			int CustomerNumberInTheDataBase = rs.getInt("customerNumber");
			String CustomerNameInTheDataBase = rs.getString("customerName");
			
			System.out.println(CustomerNumberInTheDataBase);
			System.out.println(CustomerNameInTheDataBase);
			String firstname = rs.getString("contactFirstName");
			String lastname = rs.getString("contactLastName");

		
		driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
		WebElement FirstNameInput = driver.findElement(By.id("firstname"));
		WebElement LastNameInput = driver.findElement(By.id("lastname"));
		

		FirstNameInput.sendKeys(firstname);
		LastNameInput.sendKeys(lastname);
		}
	}
	@Test (priority = 4 )
	public void DeleteData() throws SQLException{
		String query = "delete from customers where customerNumber=505";
		
		stmt=con.createStatement();
		
		int rowInserted = stmt.executeUpdate(query);
		
		System.out.println(rowInserted);
		
	}

	
}
