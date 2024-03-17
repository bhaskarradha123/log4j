package log4j_hibernate_ecample.controller;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import log4j_hibernate_ecample.dto.Person;



public class PersonMain {

	static Logger logger = Logger.getLogger(PersonMain.class);
	static Layout layout = new PatternLayout("%d{dd-mm-yy}--> %m");

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://Localhost:3306/person_log4j?createDatabaseIfNotExist=true", "root", "root");
		Statement s = connection.createStatement();
		s.execute("CREATE TABLE if not exists `person_log4j`.`person` ("
				+ "  `id` INT NOT NULL,"
				+ "  `name` VARCHAR(45) NULL,"
				+ "  `email` VARCHAR(45) NULL,"
				+ "  `pwd` VARCHAR(45) NULL,"
				+ "  `balance` VARCHAR(45) NULL,"
				+ "  PRIMARY KEY (`id`),"
				+ "  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE) ");
		
		return connection;
	}

	public void savePerson(Person person) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("insert into person values(?,?,?,?,?)");
		preparedStatement.setInt(1, person.getId());
		preparedStatement.setString(2, person.getName());
		preparedStatement.setString(3, person.getEmail());
		preparedStatement.setString(4, person.getPwd());
		preparedStatement.setDouble(5, person.getBalance());
		preparedStatement.execute();
		preparedStatement.close();
		connection.close();
	}
	
	public  void updatebalance(double balance,String mail) throws Exception{
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("update person set balance=? where email=?");
	    preparedStatement.setDouble(1, balance);
	    preparedStatement.setString(2, mail);
	    preparedStatement.execute();
	    preparedStatement.close();
	    connection.close();
		
		
	}

	public void validatePerson(String mail, String pwd) throws Exception {

		Scanner sc = new Scanner(System.in);
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from person where email=?");
		preparedStatement.setString(1, mail);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			//int id = resultSet.getInt(1);
			//String name = resultSet.getString(2);
			String mail2 = resultSet.getString(3);
			String pwd2 = resultSet.getString(4);
			double balance = resultSet.getDouble(5);
			

			Person person = new Person();
			person.setBalance(balance);
			person.setEmail(mail2);
			//person.setId(id);
			person.setPwd(pwd2);
			//person.setName(name);

			if (mail2.equals(mail) && pwd2.equals(pwd)) {

				logger.info("valid user info");
				boolean check = true;
				do {
					System.out.println();
					System.out.println(
							"Choose options \n 1.Check balance \n 2.You can Withdraw  \n 3.You can credit \n 4.Exit");
					int option = sc.nextInt();
					switch (option) {

					case 1: {

						logger.info(person.getBalance() + "  is the current balance");
					}
						break;
					case 2: {
						System.out.println("Enter amount to withdraw");
						double amount = sc.nextDouble();
						if (amount < person.getBalance()) {
							double cash = person.getBalance() - amount;
							person.setBalance(cash);
							new PersonMain().updatebalance(cash, mail);
							

							logger.info(cash + "  is the current balance");
						} else
							logger.warn("balance is not enough");

					}
						break;
					case 3: {
						System.out.println("Enter amount to credit");
						double amount = sc.nextDouble();
						double cash = person.getBalance() + amount;
						person.setBalance(cash);
						new PersonMain().updatebalance(cash, mail);

						logger.info(cash + "  is the current balance");

					}
						break;
					case 4:
						check = false;
					}
				} while (check);

				System.exit(0);
			} // if end
			else

				logger.debug("invalid user info");

		}
	}

	// System.exit(0);
	// return;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PersonMain crud = new PersonMain();
		Appender appender = new ConsoleAppender(layout);
		logger.addAppender(appender);

		boolean check = true;
		do {

			System.out.println("enter your choice \n 1.Sigin  2.Login  3.Exit ");
			int choice = sc.nextInt();
			switch (choice) {

			case 1: {
				System.out.println("Enter the criteria");
				System.out.println("enter id");
				int id = sc.nextInt();
				System.out.println("enter name");
				String name = sc.next();
				System.out.println("enter mail");
				String mail = sc.next();
				System.out.println("enter password");
				String pwd = sc.next();
				System.out.println("Enter balance");
				double balance = sc.nextDouble();

				Person person = new Person();
				person.setBalance(balance);
				person.setEmail(mail);
				person.setName(name);
				person.setPwd(pwd);
				person.setId(id);

				try {
					crud.savePerson(person);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
				break;
			case 2: {

				int count = 3;
				while (count > 0) {
					System.out.println();
					System.out.println("Enter email");
					String mail = sc.next();
					System.out.println("Enter password");
					String pwd = sc.next();

					try {
						crud.validatePerson(mail, pwd);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count--;

				}
				logger.warn("account is blocked u can try after 1 hour");

				

			}break;
			case 3:{
				check = false;
				System.out.println();
			}
				

			}

		} while (check);
		System.out.println("====thank you====");

	}

}
