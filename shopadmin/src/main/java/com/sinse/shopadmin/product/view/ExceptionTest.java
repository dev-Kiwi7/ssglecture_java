package com.sinse.shopadmin.product.view;

/*
 * 에러(개발자의 범위 벗어난 문제 상황) - java의 관심대상이 아님 
 * 예외? 자바에서 말하는 에러는 개발자가 처리 가능한 에러를 말한다.. 
 *         에러중 개발자가 처리 가능한 수준의 에러를 특히를 예외라한다..
 * 예외) 
 *  1) 컴파일러 처리를 강요하는 예외 - 강요된 예외(컴파일러에 의해 예외처리를 강제당하는것)
 *  2) 강제하지 않는 예외 - 개발자가 원하면 처리할 수 있는 예외 (RuntimeException)
 *                               심각하지 않은 예외, 주로 개발자의 논리적 실수..arr[5]         
 * 
 * */
public class ExceptionTest {
	public void test() throws ClassNotFoundException {
		load();
	}
	
	//throws 의미? 나를 호출한 메서드에 책임을 전가시키겠다!!
	public void load() throws ClassNotFoundException{
		Class.forName("babo.Babo");
	}
	
	public static void main(String[] args) throws ClassNotFoundException{
		ExceptionTest et = new ExceptionTest();
		et.test();
	}
}











