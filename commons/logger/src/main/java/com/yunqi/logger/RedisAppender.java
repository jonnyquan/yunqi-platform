package com.yunqi.logger;

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
			String level = eventObject.getLevel().levelStr.toLowerCase();
			StringBuilder builder = new StringBuilder();
			builder.append(layout.doLayout(eventObject));
			pusher.push(level, builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setLayout(Layout<ILoggingEvent> layout) {
		this.layout = layout;
	}

}
