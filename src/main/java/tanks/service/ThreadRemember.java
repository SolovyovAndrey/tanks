package tanks.service;

import java.io.IOException;
import java.util.List;

public class ThreadRemember implements  Runnable{

    StepSaver stepSaver;
    List<String>stepTank;


    public ThreadRemember(List<String>stepTank){

        this.stepTank = stepTank;
        stepSaver = new StepSaver(stepTank);

 }


    @Override
    public void run() {
        try {
            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void start() throws IOException, InterruptedException {

//            System.out.println("Start ThreadRemember");

            stepSaver.save();
    }
}
