package com.nikolin.factory;

import java.util.ResourceBundle;

import org.eclipse.egit.github.core.client.GitHubClient;

public class GithubClientFactory {
	
	public static GitHubClient getInstance() {
		//ResourceBundle , is used to store texts and components that are locale sensitive
		ResourceBundle labels = ResourceBundle.getBundle("application");
		GitHubClient client = new GitHubClient();
		//Configuring Authorization and Retrieving Access Token
		client.setOAuth2Token(labels.getString("oauth.token"));
		return client;
	}
}
