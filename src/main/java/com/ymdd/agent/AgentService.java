package com.ymdd.agent;

import com.corundumstudio.socketio.SocketIOClient;
import com.ymdd.core.DataService;

public class AgentService {
	
	public static void agentLogin(String agent) {
		DataService.agentSet.add(agent);
	}
	
	public static void saveClient(String agent,SocketIOClient client) {
		DataService.agentClient.put(agent, client);
	}
	
	public static SocketIOClient getClient(String agent) {
		return DataService.agentClient.get(agent);
	}

}
