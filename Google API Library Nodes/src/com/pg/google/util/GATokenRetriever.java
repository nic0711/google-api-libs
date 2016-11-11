package com.pg.google.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;


public class GATokenRetriever {

	public static final String REDIRECT_URL = "urn:ietf:wg:oauth:2.0:oob";
	
	// Generally useful SCOPES
	public static final String GA_SCOPE_READONLY = "https://www.googleapis.com/auth/analytics.readonly";
	public static final String GA_SCOPE_MANAGEUSERS = "https://www.googleapis.com/auth/analytics.manage.users";
	public static final String GA_SCOPE = "https://www.googleapis.com/auth/analytics";
	public static final String ADWORDS_SCOPE = "https://adwords.google.com/api/adwords/";	
	public static final String WEBMASTER_SCOPE = "https://www.googleapis.com/auth/webmasters.readonly";
	
	public static final String CLIENT_ID = "644671684317.apps.googleusercontent.com";	
	public static final String CLIENT_SECRET = "rojY3Y5J_T2H6v80pa4R6h7O";
	
	
	public static void main(String[] args) throws IOException {
		
		
		String proxySetting = "false";
		
		if ( "true".equals(proxySetting) ) {
			System.out.println("Using Proxy euproxy.pg.com:8080" );
			System.setProperty("http.proxyHost", "euproxy.pg.com");
			System.setProperty("http.proxyPort", "8080");
			System.setProperty("https.proxyHost", "euproxy.pg.com");
			System.setProperty("https.proxyPort", "8080");
		}	
		
		System.out.println("Refresh Token:" + GET_AUTH_CODE());	
	}

	
	public static String GET_AUTH_CODE() throws IOException {
		
		Collection<String> requestedScopes = new ArrayList<String>();
		Collections.addAll(requestedScopes, ADWORDS_SCOPE);
		
		// Generate the URL to send the user to grant access.
		String authorizationUrl = new GoogleAuthorizationCodeRequestUrl(
										CLIENT_ID, 
										REDIRECT_URL, 
										requestedScopes).build();
		
		
		// Direct user to the authorization URI.
		System.out.println("Go to the following link in your browser:");
		System.out.println(authorizationUrl);

		// Get authorization code from user.
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is the authorization code?");
		
		String authorizationCode = null;
		try {
		  authorizationCode = in.readLine();
		} catch (IOException ioe) {
		  ioe.printStackTrace();
		}
		
		GoogleTokenResponse response = new GoogleAuthorizationCodeTokenRequest(
				new NetHttpTransport(), 
				new JacksonFactory(),
				CLIENT_ID, 
				CLIENT_SECRET, 
				authorizationCode, 
				REDIRECT_URL	
		).execute();

		return response.getRefreshToken();
	}	
	
}
