package com.okunu.designMode.single;

/*
 * µ¥ÀýÄ£Ê½
 */
public class Singleton {

	private static final Singleton mInstance = new Singleton();
	
	private Singleton(){}
	
	public static Singleton getSingleton(){
		return mInstance;
	}
}
