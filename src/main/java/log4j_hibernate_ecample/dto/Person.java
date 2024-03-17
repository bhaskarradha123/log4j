package log4j_hibernate_ecample.dto;

public class Person {
private int id;
private String name;
private String email;
private String pwd;
private double balance;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
@Override
public String toString() {
	return "Person [id=" + id + ", name=" + name + ", email=" + email + ", pwd=" + pwd + ", balance=" + balance + "]";
}






}
