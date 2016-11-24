package com.yunqi.core.log;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;

public class RedisAppender extends ch.qos.logback.core.AppenderBase<ILoggingEvent> {

	private RedisPusher pusher;

	private Layout<ILoggingEvent> layout;

	@Override
	public void start() {
		super.start();
		pusher.init();
	}

	public void setPusher(RedisPusher pusher) {
		this.pusher = pusher;
	}

	@Override
	protected void append(ILoggingEvent eventObject) {
		try {
			StringBuilder builder = new StringBuilder();
			builder.append(layout.doLayout(eventObject));
			pusher.push(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setLayout(Layout<ILoggingEvent> layout) {
		this.layout = layout;
	}

}
