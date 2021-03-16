package com.example.healthtracker.data_access;


public class Account {
	private String username;
	private String password;
	private String firstName;
	private String lastName;

	public String getUsername() {
		return this.username;
	}

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
		// TODO - implement com.example.healthtracker.data_access.Account.verifyAccountForLogin
		throw new UnsupportedOperationException();
	}

	public void registerAccount() {
		// TODO - implement com.example.healthtracker.data_access.Account.registerAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 */
	public Account Account(String username, String password, String firstName, String lastName) {
		// TODO - implement com.example.healthtracker.data_access.Account.com.example.healthtracker.data_access.Account
		throw new UnsupportedOperationException();
	}

	/**
	 * changes the password both in the entity and the database.
	 */
	public void changePassword() {
		// TODO - implement com.example.healthtracker.data_access.Account.changePassword
		throw new UnsupportedOperationException();
	}

}