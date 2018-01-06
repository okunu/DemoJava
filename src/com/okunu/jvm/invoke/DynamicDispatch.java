package com.okunu.jvm.invoke;

public class DynamicDispatch {

	static abstract class Human{
		protected abstract void sayHello();
	}
	static class Man extends Human{
		@Override
		protected void sayHello() {
			System.out.println("man say hello");
		}
	}
	static class Woman extends Human{
		@Override
		protected void sayHello() {
			System.out.println("woman say hello");
		}
	}
	public static void main(String[] args) {
		Human man = new Man();
		Human women = new Woman();
		man.sayHello();
		women.sayHello();
		man = new Woman();
		man.sayHello();
	}
	
	public int calc(){
		int a = 100;
		int b = 200;
		int c = 300;
		return (a + b) * c;
	}
}
