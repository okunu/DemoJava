package com.okunu.designMode.state;

public class TvController implements PowerController{

	TvState mState;
	
	public void setState(TvState tvState){
		mState = tvState;
	}
	
	@Override
	public void powerOn() {
		System.out.println("开机了");
		setState(new PowerOnState());
	}

	@Override
	public void powerOff() {
		System.out.println("关机了");
		setState(new PowerOffState());
	}
	
	public void nextChannel() {
		mState.nextChannel();
	}

	public void preChannel() {
		mState.preChannel();
	}

	public void turnUp() {
		mState.turnUp();
	}

	public void turnDown() {
		mState.turnDown();
	}

	public static void main(String[] args) {
		TvController controller = new TvController();
		controller.powerOn();
		controller.nextChannel();
		controller.turnDown();
		controller.powerOff();
		controller.turnUp();
	}
}
