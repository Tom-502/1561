public class TestRunnable extends Thread{
    String name;

    public TestRunnable(String name){
        this.name = name;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for(int i = 0;i<10;i++){
            System.out.println(name+"，沙师弟：大师兄，不好了，师傅被妖怪抓走了");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String [] args){
        TestRunnable test = new TestRunnable("沙师弟线程");
        test.start();

    }
}
