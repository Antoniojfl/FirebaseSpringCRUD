package com.antonio.example.controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.antonio.example.models.User;
import com.antonio.example.services.Firebase;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.StorageClient;




@RestController
public class UserController {
	
	@Autowired
	private Firebase service;
	
	public UserController (Firebase service){
		this.service = service;
	}
	
	@PostMapping("/create")
	public String createUser (@RequestBody User user)throws InterruptedException, ExecutionException {
		return service.createUser(user);
	}
	
	@PostMapping("/upload")
	public URL uploadFile (@RequestParam MultipartFile file ) throws IOException {
		Bucket bucket = StorageClient.getInstance().bucket();
		InputStream content = file.getInputStream();
		String bucketName = file.getOriginalFilename();
		String fileType = file.getContentType();
		Blob blob = bucket.create(bucketName, content, fileType);
		
		URL signedUrl =
				   blob.signUrl(
				     14,
				     TimeUnit.DAYS,
				     SignUrlOption.signWith(
				       ServiceAccountCredentials.fromStream(new FileInputStream("./serviceAccount.json"))));
				 // [END signUrlWithSigner]
				 return signedUrl;
		
		//return "File: "+bucketName+" uploaded";
	}
	
	@GetMapping("/download")
	public String downloadFile () {
		Storage storage = StorageOptions.getDefaultInstance().getService();
		
		Path path = Paths.get("C:\\Users\\Antonio Fernandez\\Downloads");

		// Get specific file from specified bucket
		Blob blob = storage.get(BlobId.of("fir-spring-crud.appspot.com","publickeybitbucket.jpg"));

		// Download file to specified path
		blob.downloadTo(path);
		
		return "exitoso";
		
	}
	
	

}
