package com.okunu.designMode.abstractFactory;

public class Q7Factory extends CarFactory{

	@Override
	public ITire createTire() {
		return new SuvTire();
	}

	@Override
	public IEngine createEngine() {
		return new ImportEngine();
	}

}
