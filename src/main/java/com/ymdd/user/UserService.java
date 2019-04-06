package com.ymdd.user;

import com.corundumstudio.socketio.SocketIOClient;
import com.ymdd.core.DataService;

public class UserService {
	
	public static void userLogin(String user) {
		DataService.userSet.add(user);
	}
	
	public static void userQueue(String user) {
		DataService.queue.add(user);
	}
	
	public static void saveClient(String user,SocketIOClient client) {
		DataService.userClient.put(user, client);
	}
	
	public static SocketIOClient getClient(String user) {
		return DataService.userClient.get(user);
	}
}
