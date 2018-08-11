package com.okunu.deepcopy;

import java.io.Serializable;

public class CopyJob implements Cloneable, Serializable{

	public String jobName;
	public int salary;
	
	public CopyJob(){}
	
	public CopyJob(String name, int salary){
		this.jobName = name;
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "jobname = " + jobName + "  salary = " + salary;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
