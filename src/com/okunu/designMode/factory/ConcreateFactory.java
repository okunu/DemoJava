package com.okunu.designMode.factory;

public class ConcreateFactory extends Factory{

	@Override
	public <T extends Product> T create(Class<T> clz) {
		Product p = null;
		try {
			p = (Product) Class.forName(clz.getName()).newInstance();
		} catch (Exception e) {
		}
		return (T) p;
	}
	
	public static void main(String[] args) {
		Factory factory = new ConcreateFactory();
		Product p = factory.create(ProductB.class);
		p.method();
		p = factory.create(ProductA.class);
		p.method();
		
	}

}
