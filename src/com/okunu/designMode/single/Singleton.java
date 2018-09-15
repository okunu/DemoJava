package com.okunu.designMode.single;

/*
 * ����ģʽ
 */
public class Singleton {

	private static final Singleton mInstance = new Singleton();
	
	private Singleton(){}
	
	public static Singleton getSingleton(){
		return mInstance;
	}
}
