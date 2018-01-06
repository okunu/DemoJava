package com.okunu.jvm.invoke;

public class StaticDispatch {

	static abstract class Human{
	}
	
	static class Man extends Human{}
	
	static class Women extends Human{};
	
	public void sayHello(Human guy){
		System.out.println("hello, guy");
	}
	
	public void sayHello(Man guy){
		System.out.println("hello, man");
	}
	
	public void sayHello(Women guy){
		System.out.println("hello, lady");
	}
	
	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Women();
		StaticDispatch dispatch = new StaticDispatch();
		dispatch.sayHello(man);
		dispatch.sayHello(woman);
	}
}
