package com.nikolin.rest;

import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nikolin.exception.ResourceNotFoundException;
import com.nikolin.factory.GithubClientFactory;
import com.nikolin.model.Resume;
import com.nikolin.model.Statistics;

@RestController
@RequestMapping("/rest")
public class ResumeRestService {

	//A Logger object is used to log messages for the system
	private static final Logger log = Logger.getLogger(ResumeRestService.class);
	private GitHubClient client = GithubClientFactory.getInstance();

	//MediaType.APPLICATION_JSON_VALUE is a "String equivalent of MediaType.APPLICATION_JSON" + getting user
	@RequestMapping(value = "/{username}/profile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("username") String username) {
		UserService userService = new UserService(client);
		
		User user = null;
		try {
			user = userService.getUser(username);

		} catch (IOException e) {
			log.error("Failed to get details for user " + username, e);
			throw new ResourceNotFoundException();
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
    }
	//Getting repositories
	@RequestMapping(value = "/{username}/repositories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Repository>> getRepositories(@PathVariable("username") String username) {
		RepositoryService repositoryService = new RepositoryService(client);
		
		Collection<Repository> repositories = null;
		try {
			repositories = repositoryService.getRepositories(username);	
		} catch (IOException e) {
			log.error("Failed to get repositories list for user " + username, e);
			throw new ResourceNotFoundException();
		}
		
		return new ResponseEntity<>(repositories, HttpStatus.OK);
    }
	//Resume
	@RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Resume> getResume(@PathVariable("username") String username) {
		UserService userService = new UserService(client);
		RepositoryService repositoryService = new RepositoryService(client);
		
		User user = null;
		Collection<Repository> repositories;

		try {
			user = userService.getUser(username);
			repositories = repositoryService.getRepositories(username);
		} catch (IOException e) {
			log.error("Failed to get resume for user " + username, e);
			throw new ResourceNotFoundException();
		}
		
		Resume resume = new Resume(user, repositories, new Statistics(repositories));
		
		return new ResponseEntity<>(resume, HttpStatus.OK);
    }
}
