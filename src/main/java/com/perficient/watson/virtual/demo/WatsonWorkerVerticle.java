package com.perficient.watson.virtual.demo;

import java.util.Map;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public class WatsonWorkerVerticle extends AbstractVerticle {

	public static final String WORKSPACE_ID = "c09854f5-5d1c-4a61-abd7-be33d68168a6";
	public static final String USER_ID = "35576713-8a8c-4164-8fac-30d6232e2ee8";
	public static final String PASSWORD = "xWPgfKb1qnU7";
	ConversationService service;
	

	@Override
	public void start() {
		service = new ConversationService("2016-09-20");
		service.setUsernameAndPassword(USER_ID, PASSWORD);
		vertx.eventBus().consumer("send-msg").handler(this::sendHandler);
	}

	private void sendHandler(Message<Object> message) {
		Map<String,Object> context = null;
		JsonObject jmsg = new JsonObject(message.body().toString());
		if (jmsg.containsKey("context")) {
			JsonObject ctx = new JsonObject(jmsg.getString("context"));
			context = ctx.getMap();
		}
		MessageRequest watsonMsg = new MessageRequest.Builder()
				  .inputText(jmsg.getString("msg"))
				  .context(context)
				  .build();
		MessageResponse response = service.message(WORKSPACE_ID, watsonMsg).execute();
		
		message.reply(response.toString());
		
	}
}