package com.okunu.designMode.single;

public class Singleton2 {

	private static Singleton2 mInstance;
	private Singleton2(){};
	public static synchronized Singleton2 getInstance(){
		if (mInstance == null) {
			mInstance = new Singleton2();
		}
		return mInstance;
	}
}
