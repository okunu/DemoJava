package com.okunu.designMode.single;

public class Singleton4 {

	private Singleton4(){};
	
	public static Singleton4 getInstance(){
		return SingleHolder.sInstance;
	}
	
	private static class SingleHolder{
		private static final Singleton4 sInstance = new Singleton4();
	}
}
