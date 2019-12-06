public class duoxian extends Thread{
    public duoxian(String n){
        super(n);
    }

    /**
     * 重写run方法
     */
    @Override
    public void run() {
        for(int i = 0;i<10;i++){
            System.out.println("猪八戒：大师兄，不好了，师傅被妖怪抓走了");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String [] args){
        duoxian test = new duoxian("猪八戒线程");
        test.start();

    }
}
