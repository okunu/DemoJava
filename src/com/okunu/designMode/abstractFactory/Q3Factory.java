package com.okunu.designMode.abstractFactory;

public class Q3Factory extends CarFactory{

	@Override
	public ITire createTire() {
		return new NormalTire();
	}

	@Override
	public IEngine createEngine() {
		return new NormalEngine();
	}
}
