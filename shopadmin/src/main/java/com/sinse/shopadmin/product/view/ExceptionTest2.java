package com.sinse.shopadmin.product.view;

public class ExceptionTest2 {
	public void test() {
		
		try {
			test2();
		} catch (NumberConvertFailException e) {
			System.out.println("내가 만든 예외가 전달되었네..."+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void test2() throws NumberConvertFailException{
		String s="100k";

		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			//우리만의 예외를 메모리에 생성하고, 에러를 일부러 일으킨다!!!
			throw new NumberConvertFailException("숫자로 못바꿨어", e);
		}
		
	}
	
	public static void main(String[] args) {
		new ExceptionTest2().test();
	}

}
