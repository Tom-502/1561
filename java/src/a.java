

public class a{

    /**
     * @param args
     */
    public static void main(String[] args) {
// TODO Auto-generated method stub

        TongBu1 t = new TongBu1();
        Thread p1 = new Thread(t);
        p1.setName("线程1");
        p1.start();

        Thread p2 = new Thread(t);
        p2.setName("线程2");
        p2.start();
    }

}


class TongBu1 implements Runnable{

    static int count =1;
    public synchronized void run() {
        for (int i = count; i <= 50; i++) {
            try {
                System.out.println(Thread.currentThread().getName()+"输入："+i);
                if (i%2==0) {
                    notify();
                    wait();
                }

            } catch (Exception e) {
// TODO: handle exception
            }
        }
    }
}
