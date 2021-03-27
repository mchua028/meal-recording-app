package com.example.healthtracker.start_up;
import com.google.firebase.auth.FirebaseAuth;


public class Configurator {

	private static int singleton;

	private Configurator() {
		// TODO - implement com.example.healthtracker.start_up.Configurator.com.example.healthtracker.start_up.Configurator
	}

	public static Configurator getSingleton() {
		// TODO - implement com.example.healthtracker.start_up.Configurator.getSingleton
	}

	public void initialize() {
		FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
		if(firebaseAuth.getCurrentUser()!=null) {
		}
	}

}