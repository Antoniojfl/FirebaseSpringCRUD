package com.antonio.example.repositories;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

//import org.springframework.stereotype.Repository;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

@Repository
public class FirebaseInitializer {
	
	@PostConstruct
	public void initializeDB() {
		try {
		FileInputStream serviceAccount = new FileInputStream("./serviceAccount.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://fir-spring-crud.firebaseio.com")
				  .setStorageBucket("gs://fir-spring-crud.appspot.com")
				  .build();

				FirebaseApp.initializeApp(options);
				Bucket bucket = StorageClient.getInstance().bucket();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
