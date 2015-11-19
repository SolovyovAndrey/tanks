package tanks.service;

import tanks.domain.Aggressor;

import java.util.List;


public class ThreadAgressor implements Runnable {

    private Aggressor tankA;
    public List<TankAction> stepAgressor;
    private boolean gameOver;


    public ThreadAgressor ( Aggressor tankA, List<TankAction> stepAgressor){

        this.tankA = tankA;
        this.stepAgressor = stepAgressor;

        setGameOver(false);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void start () throws Exception {

        while (!isGameOver()) {

    //               tankA.processDestroyShtab();
            tankA.moveRandom();
            System.out.println("Ta - not game over");

        }
        if (isGameOver()){
            System.out.println("ta - game over");
        }
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

