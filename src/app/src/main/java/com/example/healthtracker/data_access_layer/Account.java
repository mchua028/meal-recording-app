package com.example.healthtracker.data_access_layer;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.healthtracker.Exceptions.account_exceptions.AlreadyLoggedInException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Data Access Object, connects with Account firebase
 * @Author: TANG YUTING
 */
public class Account {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private FirebaseAuth mAuth = FirebaseAuth.getInstance();
	private FirebaseUser user;

	/**
	 * Constructor of Account class
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * TODO: check input syntax
	 */
	public Account(String username, String password, String firstName, String lastName, String email) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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
	 * Authenticates the user with Firebase.If log-in fails, the message will printed to the user.
	 * @param context: Android Activity about log-in
	 * @throws AlreadyLoggedInException if the user is already logged-in
	 */
	public void signInWithEmailPassword(Activity context) throws AlreadyLoggedInException {
		// TODO - update UI
		FirebaseUser currentUser = mAuth.getCurrentUser();
		if(currentUser != null){
			throw new AlreadyLoggedInException();
		}

		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
//							Log.d(TAG, "signInWithEmail:success");
							user = mAuth.getCurrentUser();
							updateUI(user);
						} else {
							// If sign in fails, display a message to the user.
//							Log.w(TAG, "signInWithEmail:failure", task.getException());
							Toast.makeText(context, "Authentication failed.",
									Toast.LENGTH_SHORT).show();
							updateUI(null);
						}
					}
				});
	}

	/**
	 * Register the user with email and password to the database.
	 * If register fails, text message will be displayed.
	 * @param context: Android Activity about registration
	 */
	public void registerAccountViaEmailPassword(Activity context) {
		// TODO - update UI
		mAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
//							Log.d(TAG, "createUserWithEmail:success");
							user = mAuth.getCurrentUser();
//							updateUI(user);
						} else {
							// If sign in fails, display a message to the user.
//							Log.w(TAG, "createUserWithEmail:failure", task.getException());
							Toast.makeText(context, "Authentication failed.",
									Toast.LENGTH_SHORT).show();
							updateUI(null);
						}
					}
				});
	}



	/**
	 * Changes the password both in the entity and the database.
	 * @param newPassword: String, new password
	 */
	public void changePassword(String newPassword) {
		user.updatePassword(newPassword)
				.addOnCompleteListener(new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						if (task.isSuccessful()) {
//							Log.d(TAG, "User password updated.");
						}
					}
				});
	}

	/**
	 * Signs out the user.
	 */
	public void signOut() {
		FirebaseAuth.getInstance().signOut();
	}



}