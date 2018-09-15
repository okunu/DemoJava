package com.okunu.designMode.abstractFactory;

public abstract class CarFactory {

	public abstract ITire createTire();
	
	public abstract IEngine createEngine();
}
