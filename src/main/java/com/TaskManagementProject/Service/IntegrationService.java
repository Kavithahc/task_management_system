package com.TaskManagementProject.Service;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagementProject.Client.IssueClient;
import com.TaskManagementProject.Enum.IssueStatus;

@Service
public class IntegrationService {
	@Autowired
	private IssueClient issueClient;
	
	
	public void commitmessage(String msg, String author) {
		
		String regex = "([A - Z]+-\\d";
		
		Matcher matcher = Pattern.compile(regex).matcher(msg);
		if(matcher.find()) {
			Long issueId = Long.parseLong(matcher.group(1).split("-")[1]);
			
			issueClient.updateStatus(issueId,IssueStatus.DONE, author);
			issueClient.addComment(issueId,author,"close via comment:"+msg);
		}
	}
	
	
	
	public void handlePullRequest(String title, String author) {
	
		String regex = "([A - Z]+-\\d";
		Matcher matcher = Pattern.compile(regex).matcher(title);
		
		if(matcher.find()) {
			Long issueId = Long.parseLong(matcher.group(1).split("-")[1]);
			
			issueClient.updateStatus(issueId,IssueStatus.IN_PROGRESS, author);
			issueClient.addComment(issueId,author,"Pull Request opened:"+title);
		}
}
}
