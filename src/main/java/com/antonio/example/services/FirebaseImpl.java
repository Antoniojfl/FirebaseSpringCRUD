package com.antonio.example.services;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.antonio.example.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;


@Service
public class FirebaseImpl implements Firebase {
	
	public String createUser(User user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionsApiFuture = dbFirestore.collection("Users").document(user.getName()).set(user);
		return collectionsApiFuture.get().getUpdateTime().toString();
	
	}
	
	
	

}
