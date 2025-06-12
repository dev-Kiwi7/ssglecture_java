package util;

import exception.ProductException;

public class ExceptionTest {

	public static void main(String[] args) {
		
		int x=0;
		
		try {
			if(x<1) {
				throw new ProductException("에러났네요");
			}
			x=9;
		} catch (ProductException e) {
			e.printStackTrace();
		}
		
		System.out.println(x);
		
	}

}
