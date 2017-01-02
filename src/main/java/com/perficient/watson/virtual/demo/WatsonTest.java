package com.perficient.watson.virtual.demo;

import java.util.Map;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class WatsonTest {
	
	public static final String WORKSPACE_ID="c09854f5-5d1c-4a61-abd7-be33d68168a6"; 

	public static void main(String[] args) {
		ConversationService service = new ConversationService("2016-09-20");
		service.setUsernameAndPassword("35576713-8a8c-4164-8fac-30d6232e2ee8", "xWPgfKb1qnU7");
		MessageRequest newMessage = new MessageRequest.Builder()
				  .inputText("")
				  .context(null)
				  .build();
		MessageResponse response = service.message(WORKSPACE_ID, newMessage).execute();
		System.out.println(response.getText().get(0));
		Map<String,Object>context = response.getContext();
		newMessage = new MessageRequest.Builder()
				  .inputText("I want to dispute a charge")
				  .context(context)
				  .build();
		response = service.message(WORKSPACE_ID, newMessage).execute();
		System.out.println(response.getIntents().get(0).getIntent());
	}

}
