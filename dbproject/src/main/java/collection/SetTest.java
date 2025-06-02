package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		//컬렉션 프레임웍이 지원하는 순서 없는 객체 중 하나인 Set을 학습 
		Set<String> set=new HashSet<>(); //java 7부터 다이아몬드 연산자
															//스스로 추정하므로, 자료형 생략 가능
		set.add("BMW");
		set.add("Benz");
		set.add("Audi");
		set.add("K9");
		
		//순서 없는 Set은 크기는 알수는 잇지만 직접적으로 for문을 수행불가 
		//풀어놓아야 함...
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {//다음 요소가 존재할때까지..
			String obj=it.next();// 현재 위치에서 다음 요소로 접근
			System.out.println(obj);
		}
		
		
	}

}



