package com.okunu.designMode.state;

public class PowerOnState implements TvState{

	@Override
	public void nextChannel() {
		System.out.println("下一个频道");
	}

	@Override
	public void preChannel() {
		System.out.println("上一个频道");
	}

	@Override
	public void turnUp() {
		System.out.println("增大音量");
	}

	@Override
	public void turnDown() {
		System.out.println("减小音量");
	}

}
