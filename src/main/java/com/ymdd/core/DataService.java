package com.ymdd.core;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.corundumstudio.socketio.SocketIOClient;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;

public class DataService {

	public static final int STRATEGY = 2; // 1随机 2最大人数 3历史优先
	public static final int MAX_ALLOT = 1;
	public static Set<String> userSet = Sets.newConcurrentHashSet();
	public static Set<String> agentSet = Sets.newConcurrentHashSet();
	public static ArrayListMultimap<String, String> relation = ArrayListMultimap.create();
	public static Set<String> service = Sets.newConcurrentHashSet();
	public static Queue<String> queue = Queues.newConcurrentLinkedQueue();
	//TODO SocketIOClient暂不持支持分布式
	public static Map<String, SocketIOClient> userClient = Maps.newHashMap();
	public static Map<String, SocketIOClient> agentClient = Maps.newHashMap();
	public static Map<String, String> sendQueueMap = Maps.newConcurrentMap();
	public static Map<String, String> replyQueueMap = Maps.newConcurrentMap();
}
	