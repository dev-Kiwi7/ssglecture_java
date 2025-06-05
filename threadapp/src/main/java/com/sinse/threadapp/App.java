package com.sinse.threadapp;


public class App{
    public static void main( String[] args ){
    	//하나의 프로세스 내에서 각각 독립적으로 실행할 수있는 단위인 쓰레드를 2개 생성하여
    	//각각 별도로 작동하게 해보자 
    	ThreadA t1=new ThreadA();
    	ThreadB t2=new ThreadB();
    	
    	//스레드를 생성한다고 하여 os가 관여하는게 아니라, start()로 밀어넣어야 함 
    	//jvm에게 맡겨야 한다...start()
    	t1.start();
    	t2.start();
    	
    	/*
        for(int i=1;i<=50;i++) {
        	System.out.println("A");
        }
        
        for(int i=1;i<=50;i++) {
        	System.out.println("B");
        }
        */

    }
}
