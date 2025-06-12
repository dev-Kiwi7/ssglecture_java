package animation;

public class Test {

	public static void main(String[] args) {
		Thread thread = new Thread(()->{
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("실행 ");
			}
		});
		
		thread.start();
		
	}

}
