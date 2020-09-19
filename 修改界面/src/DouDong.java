public class DouDong extends Thread{

    ChatFrame frame;

    public DouDong(ChatFrame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        try {
            System.out.print("Location: "+frame.getX()+","+frame.getY());
            for (int i = 0; i < 3; i++) {
                frame.setLocation(frame.getX()-28, frame.getY());
                System.out.print("Location1: "+frame.getX()+","+frame.getY());
                Thread.sleep(88);
                frame.setLocation(frame.getX(), frame.getY()-28);
                System.out.print("Location2: "+frame.getX()+","+frame.getY());
                Thread.sleep(88);
                frame.setLocation(frame.getX()+28, frame.getY());
                System.out.print("Location3: "+frame.getX()+","+frame.getY());
                Thread.sleep(88);
                frame.setLocation(frame.getX(), frame.getY()+28);
                System.out.print("Location4: "+frame.getX()+","+frame.getY());
                System.out.print("for in dd1");
                Thread.sleep(88);
                System.out.print("for in dd2");
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

}
