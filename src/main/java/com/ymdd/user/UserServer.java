package com.ymdd.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

@Component
public class UserServer implements CommandLineRunner {

	@Autowired
	private UserEventListenner userEventListenner;
	
	@Override
	public void run(String... args) throws Exception {
		Configuration config = new Configuration();
		config.setHostname("localhost");
		config.setPort(9999);
		SocketIOServer server = new SocketIOServer(config);
		server.addListeners(userEventListenner);
		server.start();
	}
}
