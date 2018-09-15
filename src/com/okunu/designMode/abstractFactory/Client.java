package com.okunu.designMode.abstractFactory;

public class Client {

	public static void main(String[] args) {
		CarFactory q3 = new Q3Factory();
		q3.createEngine().engine();
		q3.createTire().tire();
		System.out.println("-------------");
		CarFactory q7 = new Q7Factory();
		q7.createEngine().engine();
		q7.createTire().tire();
	}
}
