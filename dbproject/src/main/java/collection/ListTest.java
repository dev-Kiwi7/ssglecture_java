package collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		//자바의 컬렉션 프레임웍은 java.util 패키지에서 지원하며 
		//그 중, 순서있는 집합을 처리하는데 대표적인 List에 대해 알아본다 
		//List vs 배열
		//공통점 - 순서를 가지며 인덱스로 접근 가능
		//차이점 - 배열은 생성 시, 반드시 크기를 명시해야 함, 기본 자료형도 담을수있다
		//			컬렉션의 대상은 오직 객체만을 대상으로..
		//컬렉션 프레임웍은, 최상위 인터페이스들의 메서드를 주로 사용하기 때문에
		//하위의 어떠한 구현 객체를 사용하더라도, 메서드 사용이 일관성이 있다.
		// 담을때는 거의 add, 길이는 거의 size()
		//<자료형> 을 명시하면, 컴파일러가 다른 자료형을 거부한다.. 즉 컴파일 타임
		//에 자료형 체크, 제너릭(Generic) 타입이라 한다..
		List<String> list=new ArrayList();//고무줄 배열(js랑 동일)
		list.add("apple");
		list.add("banana");
		list.add("grape");
		list.add("orange");
		
		//고전적 반복문
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
		//5부터 개선 for(improved for)
		for(Object obj : list) {
			System.out.println(obj);
		}
		
	}

}
