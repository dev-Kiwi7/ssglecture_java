package com.sinse.shopadmin.product.view2;

public class Test {

	public static void main(String[] args) {
		/*
		for(int i=0;i<5;i++) {
			System.out.println(System.currentTimeMillis());
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		*/
		String path="c://test/babo/mario.test.jpg";
		
		//1) 가장 마지막점의 위치를 알아낸다.  lastIndexOf() 
		//2) 가장 마지막점의 위치 바로 다음 위치부터~~ 가장 마지막 문자열까지 추출한다
		//    전체 문자열에서 일부 문자열 substring(  )
		/*
		int index=path.lastIndexOf(".");
		System.out.println("가장 마지막점이 발견된 위치는 "+index);
		String ext=path.substring(index+1 , path.length());
		System.out.println(ext);
		*/
		
		String v="a100";
		String v2="100";
		
		try {
			Integer.parseInt(v);
		} catch (NumberFormatException e) {
			System.out.println("올바른 정수값이 아니네요");
			e.printStackTrace();
		}
		
	}
}









