package com.ymdd.user;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.ymdd.bean.Message;
import com.ymdd.core.AllotService;
import com.ymdd.core.MessageService;

@Service
public class UserEventListenner {

	@OnConnect
	public void onConnect(SocketIOClient client) {
		String user = client.getHandshakeData().getSingleUrlParam("user");
		UserService.userLogin(user);
		String agent = AllotService.allot(user);
		if (agent == null) {
			UserService.userQueue(user);
			return;
		}
		AllotService.buildRelation(user, agent);
	}

	@OnEvent("msg")
	public void onMessage(SocketIOClient client, Message msg) {
		String user = client.getHandshakeData().getSingleUrlParam("user");
		String agent = AllotService.getAgent(user);
		UserService.saveClient(user, client);	
		MessageService.userPub(user, agent, msg.getMsg());
	}
}
