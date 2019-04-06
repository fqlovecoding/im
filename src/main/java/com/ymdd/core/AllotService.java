package com.ymdd.core;

import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class AllotService {

	public static void buildRelation(String user, String agent) {
		DataService.relation.put(user, agent);
	}

	public static String getAgent(String user) {
		if (DataService.relation.get(user).isEmpty()) {
			return null;
		}
		return DataService.relation.get(user).get(0);
	}
	
	public static String allot(String user) {
		return allot(user,DataService.STRATEGY);
	}

	public static String allot(String user, int strategy) {
		// 分配策略:用户每次登录都会试图分配坐席
		String allotAgent = null;
		if (strategy == 1) {
			allotAgent = findAgentByRandom();
		}
		if (strategy == 2) {
			allotAgent = findAgentByMaxAllowed();
		}
		if (strategy == 3) {
			allotAgent = findAgentByHistory(user);
		}
		// 保存关系结果
		if (allotAgent == null) {
			DataService.queue.add(user);
			return null;
		}
		DataService.relation.put(allotAgent, user);
		DataService.service.add(user);

		// 打印结果
		pf();

		return allotAgent;
	}

	// 随机分配
	private static String findAgentByRandom() {
		return Lists.newArrayList(DataService.agentSet).get(0);
	}

	// 最大人数分配
	private static String findAgentByMaxAllowed() {
		for (String agent : DataService.agentSet) {
			if (DataService.relation.get(agent).size() < DataService.MAX_ALLOT) {
				return agent;
			}
		}
		return null;
	}

	// 历史数据优先--最大人数分配
	private static String findAgentByHistory(String user) {
		for (String history : DataService.relation.keySet()) {
			if (DataService.relation.get(history).contains(user)) {
				return history;
			}
		}
		return findAgentByMaxAllowed();
	}

	private static void pf() {
		System.out.println("分配策略(1随机 2最大人数 3历史优先)==" + DataService.STRATEGY);
		System.out.println("userSet==" + DataService.userSet.stream().collect(Collectors.toSet()));
		System.out.println("agentSet==" + DataService.agentSet.stream().collect(Collectors.toSet()));
		System.out.println("relation==" + DataService.relation.asMap());
		System.out.println("service==" + DataService.service.stream().collect(Collectors.toSet()));
		System.out.println("queue==" + DataService.queue.stream().collect(Collectors.toSet()));
	}
}
