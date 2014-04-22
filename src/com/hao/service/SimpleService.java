package com.hao.service;

import java.io.Serializable;

public class SimpleService implements Serializable {
	private static final long serialVersionUID = 1L;

	public void testMethod1() {
		//这里执行定时调度业务  
		System.out.println("testMethod1.......一");
	}

	public void testMethod2() {
		System.err.println("testMethod2.......二");
	}
}
