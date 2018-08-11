package com.okunu.Lock;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class DemoUnsafe {

	private static Unsafe getUnsafe(){
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			Unsafe unsafe = (Unsafe) field.get(null);
			return unsafe;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void testUnsafe(){
		Unsafe unsafe = getUnsafe();
		try {
			UnsafeUser user = (UnsafeUser) unsafe.allocateInstance(UnsafeUser.class);
			System.out.println(user);
			
			Field name = user.getClass().getDeclaredField("name");
			long nameOffset = unsafe.objectFieldOffset(name);
			unsafe.putObject(user, nameOffset, "jim");
			unsafe.putInt(user, 
					unsafe.objectFieldOffset(user.getClass().getDeclaredField("id")), 3);
			System.out.println(user);
			
			int[] array = new int[10];
			int baseOffset = unsafe.arrayBaseOffset(array.getClass());
			int indexScale = unsafe.arrayIndexScale(array.getClass());
			int ssfit = 31 - Integer.numberOfLeadingZeros(indexScale);
			System.out.println(baseOffset + "  " + indexScale + "  " + ssfit);
			for (int i = 0; i < array.length; i++) {
				//unsafe.putInt(array, baseOffset + i*indexScale, i);
				unsafe.putInt(array, (i << ssfit) + baseOffset, i);
			}
			
			unsafe.compareAndSwapInt(array, baseOffset, 0, 111);
			
			print(array);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		testUnsafe();
	}
	
	class UnsafeUser{
		public int id;
		public String name;
		
		public UnsafeUser(){
			this.id = 10;
			this.name = "test";
		}

		@Override
		public String toString() {
			return "id = " + id + " name = " + name;
		}
	}
	
	private static void print(int[] array){
		for (int i : array) {
			System.out.print(i + "   ");
		}
		System.out.println();
	}
}
