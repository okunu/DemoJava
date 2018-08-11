package com.okunu.deepcopy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CopyUser implements Cloneable, Serializable{

	public String name;
	public int age;
	public CopyJob job;
	
	public CopyUser(){}
	
	public CopyUser(String name, int age, CopyJob job){
		this.name = name;
		this.age = age;
		this.job = job;
	}
	
	@Override
	public String toString() {
		return "name = " + name + "  age = " + age + " " + job.toString();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		//return super.clone();
		//return deepClone1();
		return deepClone2();
	}
	
	private Object deepClone1(){
		try {
			CopyJob job = (CopyJob) this.job.clone();
			CopyUser user = (CopyUser) super.clone();
			user.job = job;
			return user;
		} catch (Exception e) {
		}
		return null;
	}
	
	private Object deepClone2(){
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(this);
			
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			return (oi.readObject());
		} catch (Exception e) {
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			CopyJob job1 = new CopyJob("student", 0);
			CopyUser user1 = new CopyUser("jim", 15, job1);
			System.out.println(user1);
			CopyUser user2 = (CopyUser) user1.clone();
			user2.name = "tom";
			user2.age = 21;
			user2.job.jobName = "programmer";
			user2.job.salary = 1000;
			System.out.println(user2);
			System.out.println(user1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
