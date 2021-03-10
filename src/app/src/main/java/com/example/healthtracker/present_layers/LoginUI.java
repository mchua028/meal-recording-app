package com.example.healthtracker.present_layers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LoginUI extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}


	public void onLoginButtonClick (View view) {
		// go to main page
		startActivity(new Intent(view.getContext(), MainActivity.class));
	}


	/**
	 * Logs in the user automatically by using the cache file that contains username and password.
	 */
	private boolean autoLogIn() {
		// TODO - implement com.example.healthtracker.interfaces.LoginUI.autoLogIn
		throw new UnsupportedOperationException();
	}

	private void LogInViaFacebook() {
		// TODO - implement com.example.healthtracker.interfaces.LoginUI.LogInViaFacebook
		throw new UnsupportedOperationException();
	}

	private void LogInViaGoogle() {
		// TODO - implement com.example.healthtracker.interfaces.LoginUI.LogInViaGoogle
		throw new UnsupportedOperationException();
	}

	private void submitLogInInfo() {
		// TODO - implement com.example.healthtracker.interfaces.LoginUI.submitLogInInfo
		throw new UnsupportedOperationException();
	}

}