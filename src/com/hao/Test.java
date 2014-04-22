package com.hao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext(new String[] {"classpath:config/spring/spring-quartz.xml" });
	}

}
