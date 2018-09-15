package com.okunu.designMode.factory;

public abstract class Factory {

	public abstract <T extends Product> T create(Class<T> clz);
}
