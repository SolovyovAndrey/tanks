package tanks.service;

import tanks.ActionField;


public class ThreadVision implements Runnable {


    private ActionField af;


    public ThreadVision (ActionField actionField){

        this.af = actionField;
    }


    public void start () throws InterruptedException {

       while (true) {
           af.repaint();
           Thread.sleep(1000/60);
       }
    }


    @Override
    public void run() {

        try {
            this.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
