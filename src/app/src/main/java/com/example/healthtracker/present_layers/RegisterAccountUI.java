package com.example.healthtracker.present_layers;

public class RegisterAccountUI extends UI {
	public RegisterAccountUI() {
	}

	@Override
	public void display() {

	}

	@Override
	public void displayErrorMessage() {

	}

	@Override
	public void printSucceesulMessage() {

	}

	private void submitUserRegistrationInfo() {
		mEmail =
		Hashmap<String,String> registerInfo = new Hashmap<String,String>();
		registerInfo.put("username","");
		registerInfo.put("password","");
		registerInfo.put("firstname","");
		registerInfo.put("lastname","");
		AccountManager.registerAccount(registerInfo);

	}

	private void registerViaGoogle() {
		// TODO - implement com.example.healthtracker.interfaces.RegisterAccountUI.registerViaGoogle
		throw new UnsupportedOperationException();
	}

}