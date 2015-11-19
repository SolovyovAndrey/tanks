package tanks.service;

import tanks.domain.AbstractTank;

import java.util.List;

public class ThreadDefaunder implements Runnable {

    private AbstractTank tank;
    private boolean savedReplay;
    public List <String> stepTank;
    public List <TankAction> stepDefaunder;
    private boolean gameOver;

    public ThreadDefaunder ( AbstractTank tank, boolean savedReplay, List<String> stepTank, List <TankAction> stepDefaunder ){

        this.tank = tank;
        this.savedReplay = savedReplay;
        this.stepTank = stepTank;
        this.stepDefaunder = stepDefaunder;
        setGameOver(false);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void start () throws Exception {

        System.out.println("--- Start thread defaunder ---");

//        if (savedReplay) {

            while(!isGameOver()){
                if (stepDefaunder.size() > 0) {
                    System.out.print("-");
                    if (stepDefaunder.get(stepDefaunder.size()-1).getToDo()) {
                        tank.action(stepDefaunder.get(stepDefaunder.size() - 1).getAction());
                        stepDefaunder.get(stepDefaunder.size() - 1).setToDo(false);
                        Thread.sleep(50);
                    }
                    Thread.sleep(20);
                }
  //              continue;
            }
        if (isGameOver()){
            System.out.println("td - game over");
        }
  //      }
//        if (!savedReplay){
 //           System.out.println("Replay---");
 //           for (int i = 1; i <= stepTank.size(); i++){
 //               tank.action(stepTank.get(i).substring(stepTank.get(i).indexOf("+") + 1, stepTank.get(i).length()));
 //           }
  //      }

    }


    @Override
    public void run() {

        try {
            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
