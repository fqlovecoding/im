package com.ymdd.agent;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.ymdd.bean.Message;
import com.ymdd.core.AllotService;
import com.ymdd.core.MessageService;

@Service
public class AgentEventListenner {

	@OnConnect
	public void OnConnect(SocketIOClient client) {
		String agent = client.getHandshakeData().getSingleUrlParam("agent");
		AgentService.agentLogin(agent);
		AgentService.saveClient(agent, client);
	}

	@OnEvent("msg")
	public void onMessage(SocketIOClient client, Message msg) {
		String user = client.getHandshakeData().getSingleUrlParam("user");
		String agent = AllotService.getAgent(user);
		MessageService.agentPub(user, agent, msg.getMsg());
	}
}
