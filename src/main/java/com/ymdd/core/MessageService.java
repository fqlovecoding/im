package com.ymdd.core;

import com.ymdd.agent.AgentService;
import com.ymdd.user.UserService;

public class MessageService {

	public static void userPub(String user, String agent, String msg) {
		String key = user + ":" + agent;
		DataService.sendQueueMap.put(key, msg);
		AgentService.getClient(agent).sendEvent("msg", DataService.sendQueueMap.get(key));
	}

	public static void agentPub(String user, String agent, String msg) {
		String key = user + ":" + agent;
		DataService.replyQueueMap.put(key, msg);
		UserService.getClient(user).sendEvent("msg", DataService.replyQueueMap.get(key));
	}

}
