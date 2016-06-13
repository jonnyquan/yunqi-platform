package com.yunqi.apis.user.model.enums;

/**
 * 资源类型枚举
 * @author zhangguosheng
 */
public enum ResourceType{
	
	URL("URL"),
	/**
	 * 菜单类型，该类型为用户可以见的
	 */
	MENU("菜单类型"),
	
	/**
	 * 按钮类型
	 */
	BUTTON("按钮类型"),
	
	/**
	 * 安全类型，该类型为shiro拦截的并且用户不可见的
	 */
	SEC("安全类型");
	
	private String text;

	private ResourceType(String text) {
		this.text = text;
	}
	
	public String getId() {
		return this.name();
	}

	public String getText() {
		return text;
	}
	
}
