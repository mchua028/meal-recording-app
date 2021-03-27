package com.example.healthtracker.data_access_layer;

public class Account {

	private String username;
	private String password;
	private String firstName;
	private String lastName;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {this.username=username;}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	/**
	 * makes a query to users' database, if the username and the password matches, the information can be retrieved.
	 */
	public boolean verifyAccountForLogin() {
		if()
	}

	public void registerAccount() {

	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 */
	public Account(){}

	public Account(String username, String password, String firstName, String lastName) {
		this.username=username;
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
	}

	/**
	 * changes the password both in the entity and the database.
	 */
	public void changePassword() {
		// TODO - implement com.example.healthtracker.data_access_layer.Account.changePassword
		throw new UnsupportedOperationException();
	}

}