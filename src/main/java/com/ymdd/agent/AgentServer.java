package com.ymdd.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

@Component
public class AgentServer implements CommandLineRunner {

	@Autowired
	private AgentEventListenner agentEventListenner;
	
	@Override
	public void run(String... args) throws Exception {
		Configuration config = new Configuration();
		config.setHostname("localhost");
		config.setPort(8888);
		SocketIOServer server = new SocketIOServer(config);
		server.addListeners(agentEventListenner);
		server.start();
	}
}
