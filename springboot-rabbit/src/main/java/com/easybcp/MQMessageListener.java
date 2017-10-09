package com.easybcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

public class MQMessageListener implements MessageListener {

	private static final Logger log = LoggerFactory.getLogger(MQMessageListener.class);

	public Action consume(Message message, ConsumeContext context) {

		try {
			String body = new String(message.getBody(), "utf-8");
			log.debug(body);
			

			return Action.CommitMessage;
		} catch (Exception e) {
			// 消费失败
			return Action.ReconsumeLater;
		}
	}
	
}
