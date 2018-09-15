package com.okunu.designMode.single;

public class Singleton3 {

	private static volatile Singleton3 mInstance;
	private Singleton3(){};
	
	public static Singleton3 getInstance(){
		if (mInstance == null) {
			synchronized (Singleton3.class) {
				if (mInstance == null) {
					mInstance = new Singleton3();
				}
			}
		}
		return mInstance;
	}
}
