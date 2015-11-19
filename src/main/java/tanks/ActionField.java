package tanks;
import tanks.domain.*;
import tanks.service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActionField extends JPanel {

    private static final long serialVersionUID = 1L;

    final boolean COLORDED_MODE = false;

    private BattleField battleField;
    private AbstractTank tank;
    private Aggressor tankA;
    private Bullet bullet;
    protected JFrame frame;
    public int modelTank;
    public List <String> stepTank;
    public List <TankAction> stepDefaunder;
    public List <TankAction> stepAgressor;
    public List <Bullet> bullets;

    public ThreadVision tv;
    public ThreadAgressor ta;
    public ThreadDefaunder td;
    public ThreadRemember tr;

    public String aggreQuadrant;

    public StepSaver stS;

    public boolean savedReplay = true;
    public boolean saved = true;


    public void runTheGame() throws Exception {

       stS = new StepSaver(stepTank);
       stS.cleanFile();

        stepTank = new ArrayList<>();
        stepDefaunder = new ArrayList<>();
        stepAgressor = new ArrayList<>();
        stepTank.add(String.valueOf(modelTank));

        tankA.setAgressor(true);

        ta = new ThreadAgressor(tankA, stepAgressor);
        new Thread(ta).start();

        saveStepDefaunder(tank.getClass().getSimpleName(),"UP");

        td = new ThreadDefaunder(tank, savedReplay, stepTank, stepDefaunder);
        new Thread(td).start();

        tv = new ThreadVision(this);
        new Thread(tv).start();

        tr = new ThreadRemember(stepTank);
        new Thread(tr).start();

        saveStart();

  //      StepSaverD sd = new StepSaverD(stepDefaunder);
  //      while(true){
  //          Thread.sleep(20000);
   //         sd.save();
 //       }

    }


    private void saveStart() throws InterruptedException {
        while (saved){
            Thread.sleep(15000);
            tr.run();
        }
    }


    public AbstractTank setTank(int i) throws IOException {

        if (i == 1) {
            tank = new Tiger(this, battleField);
        }
        if (i == 2) {
            tank = new T34(this, battleField);
        }

        if (i == 3) {
            tank = new BT7(this, battleField);
        }

        modelTank = i;

        return tank;
    }


    public ActionField() throws Exception {

        battleField = new BattleField();
        bullet = new Bullet(-100, -100, -1);
        bullets = new ArrayList<>();
        bullets.add(bullet);
        tankA = new Tiger(this, battleField, 0, 0, Direction.DOWN);
        tank = new Tiger(this, battleField, 256, 512, Direction.DOWN);
        frame = new JFrame("BATTLE FIELD, DAY 2");
        frame.setLocation(550, 100);
        frame.setMinimumSize(new Dimension(battleField.getBfWidth() + 10, battleField.getBfHeight() + 40));
        frame.getContentPane().add(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    try {
                        if (!tank.getDirection().equals(Direction.UP)) {
                            saveStepDefaunder(tank.getClass().getSimpleName(), "UP");
                        }
                        if (tank.getDirection().equals(Direction.UP)) {
                            saveStepDefaunder(tank.getClass().getSimpleName(), "move");
                            System.out.println("---- Key Listener UP----");
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {
                        if (!tank.getDirection().equals(Direction.DOWN)) {
                            saveStepDefaunder(tank.getClass().getSimpleName(), "DOWN");
                        }
                        if (tank.getDirection().equals(Direction.DOWN)) {
                            saveStepDefaunder(tank.getClass().getSimpleName(), "move");
                            System.out.println("---- Key Listener ----");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    try {
                        if (!tank.getDirection().equals(Direction.LEFT)) {
                            saveStepDefaunder(tank.getClass().getSimpleName(), "LEFT");
                        }
                        if (tank.getDirection().equals(Direction.LEFT)) {
                            saveStepDefaunder(tank.getClass().getSimpleName(), "move");
                            System.out.println("---- Key Listener ----");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("---- Key Listener ----");
                    try {
                        if (tank.getDirection().equals(Direction.RIGHT)) {
                            saveStepDefaunder(tank.getClass().getSimpleName(), "RIGHT");
                        }
                        if (tank.getDirection().equals(Direction.RIGHT)) {
                            saveStepDefaunder(tank.getClass().getSimpleName(), "move");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            });

        frame.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    try {
                        saveStepDefaunder(tank.getClass().getSimpleName(), "fire");
                        System.out.println("---- Key Listener ----");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    try {
                        saveStepDefaunder(tank.getClass().getSimpleName(), "UP");

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {
                        saveStepDefaunder(tank.getClass().getSimpleName(), "DOWN");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    try {
                        saveStepDefaunder(tank.getClass().getSimpleName(), "LEFT");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    try {
                        saveStepDefaunder(tank.getClass().getSimpleName(), "RIGHT");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }


    public String getQuadrantXY(int v, int h) {
        int x = v;
        x = (x - 1) * 64;
        int y = h;
        y = (y - 1) * 64;
        return (x + "_" + y);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            battleField.draw(g);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tank.draw(g);
        tankA.draw(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }
    }


    public void processMove(AbstractTank tank) throws Exception {

        int step = 1;
        int covered = 0;

        // check limits x: 0, 513; y: 0, 513
        if ((tank.getDirection().getId() == 1 && tank.getY() == 0) || (tank.getDirection().getId() == 2 && tank.getY() >= 512)
                || (tank.getDirection().getId() == 3 && tank.getX() == 0) || (tank.getDirection().getId() == 4 && tank.getX() >= 512)) {
            return;
        }

        tank.action(tank.getDirection());
        int nextX;
        int nextY;

        while (covered < 64) {
            if (tank.getDirection().equals(Direction.UP)) {
                nextX = tank.getX() / 64;
                nextY = (tank.getY() - 1) / 64;
                BFObject bfObject = battleField.scanQuadrant(nextY, nextX);

                if (!bfObject.isDestroyed() && !(bfObject instanceof Water) && (!(bfObject instanceof Blank)) )
                {
                    tank.action("fire");
                    Thread.sleep(100);
                    saveFire(tank.getClass().getSimpleName(), "fire");
                }
                tank.updateY(-step);

            } else if (tank.getDirection().equals(Direction.DOWN)) {
                nextX = tank.getX() / 64;
                nextY = (tank.getY() + 64) / 64;
                BFObject bfObject = battleField.scanQuadrant(nextY, nextX);

                if (!bfObject.isDestroyed() && !(bfObject instanceof Water) && (!(bfObject instanceof Blank)))
                {
                    tank.fire(tank.isAgressor());
                    Thread.sleep(100);
                    saveFire(tank.getClass().getSimpleName(), "fire");
                }
                tank.updateY(step);

            } else if (tank.getDirection().equals(Direction.LEFT) && tank.getX() > 0) {
                nextX = (tank.getX() - 1) / 64;
                nextY = tank.getY() / 64;
                BFObject bfObject = battleField.scanQuadrant(nextY, nextX);

                if (!bfObject.isDestroyed() && !(bfObject instanceof Water) && (!(bfObject instanceof Blank)))
                {
                    tank.fire(tank.isAgressor());
                    Thread.sleep(100);
                    saveFire(tank.getClass().getSimpleName(), "fire");
                }
                tank.updateX(-step);

            } else {
                nextX = (tank.getX() + 64) / 64;
                nextY = tank.getY() / 64;
                BFObject bfObject = battleField.scanQuadrant(nextY, nextX);

                if (!bfObject.isDestroyed() && !(bfObject instanceof Water) && (!(bfObject instanceof Blank)))
                {
                    tank.action("fire");
                    Thread.sleep(100);
                    saveFire(tank.getClass().getSimpleName(), "fire");
                }
                tank.updateX(step);
            }
            covered += step;
            Thread.sleep(tank.getSpeed());
        }
        saveFire(tank.getClass().getSimpleName(), "move");
    }


    public void saveFire (String nameTank, String action) {
        if (savedReplay) {
            stepTank.add(nameTank + "+" + action);
        }
    }


    public void saveStepDefaunder(String nameTank, String action) {

        TankAction tankAction = new TankAction();
        tankAction.setAction(action);
        tankAction.setToDo(true);

        stepDefaunder.add(tankAction);

    }


    public void saveStepAgressor (String nameTank, String action) {

        TankAction tankAction = new TankAction();
        tankAction.setAction(action);
        tankAction.setToDo(true);

        stepAgressor.add(tankAction);
//        System.out.println("saveStepAgressor + " + nameTank + "+" + action);
    }


    public void processTurn(int i) throws Exception {
        String dir = null;
        if (i == 1 ){ dir = "UP";}
        if (i == 2 ){ dir = "DOWN";}
        if (i == 3 ){ dir = "LEFT";}
        if (i == 4 ){ dir = "RIGHT";}

        saveFire(tank.getClass().getSimpleName(), dir);
    }


    public void moveToQuadrant(AbstractTank tank, int v, int h) throws Exception {

        String coordinates = getQuadrantXY(v, h);
        int dotIndex = coordinates.indexOf("_");
        int x = Integer.valueOf(coordinates.substring(0, dotIndex));
        int y = Integer.valueOf(coordinates.substring(dotIndex + 1));

        while (tank.getY() != y) {
            if (tank.getY() > y) {
                saveFire(tank.getClass().getSimpleName(), "UP");
                saveStepAgressor(tank.getClass().getSimpleName(), "UP");
                tank.action(Direction.UP);
                saveFire(tank.getClass().getSimpleName(), "move");
                saveStepAgressor(tank.getClass().getSimpleName(), "move");
                tank.action("move");
            } else if (tank.getY() < y) {
                saveFire(tank.getClass().getSimpleName(), "DOWN");
                saveStepAgressor(tank.getClass().getSimpleName(), "DOWN");
                tank.action(Direction.DOWN);
                saveFire(tank.getClass().getSimpleName(), "move");
                saveStepAgressor(tank.getClass().getSimpleName(), "move");
                tank.action("move");
            }
        }
        while (tank.getX() != x) {
            if (tank.getX() > x) {
                saveFire(tank.getClass().getSimpleName(), "LEFT");
                saveStepAgressor(tank.getClass().getSimpleName(), "LEFT");
                tank.action(Direction.LEFT);
                saveFire(tank.getClass().getSimpleName(), "move");
                saveStepAgressor(tank.getClass().getSimpleName(), "move");
                tank.action("move");
            } else if (tank.getX() < x) {
                saveFire(tank.getClass().getSimpleName(), "RIGHT");
                saveStepAgressor(tank.getClass().getSimpleName(), "RIGHT");
                tank.action(Direction.RIGHT);
                saveFire(tank.getClass().getSimpleName(), "move");
                saveStepAgressor(tank.getClass().getSimpleName(), "move");
                tank.action("move");
            }
        }
    }


    public int namberShotX(AbstractTank tank, int x) throws Exception {

        int result = 0;

        for (int k = 0; k < 9; k++) {
            BFObject bfObject = battleField.scanQuadrant(x, k);
            if ((!(bfObject instanceof Blank) && !(bfObject instanceof Water)) && !bfObject.isDestroyed()) {
                result = result + 1;
                if (bfObject instanceof Rock) {
                    result = result + 1;
                }
            } else {
                if (!tank.isAgressor()) {
                    if ((k == tankA.getX() / 64 &&
                            x == tankA.getY() / 64)) {
                        if (tankA.getArmore() == 1) {
                            result = result + 2;
                        } else {
                            result = result + 1;
                        }
                    }
                }
            }
        }
        int i = 0;
        while (i < result) {
            tank.action("fire");
            Thread.sleep(500);
            i++;
        }
        return result;
    }


    public void namberShotY(AbstractTank tank, int y) throws Exception {

        int result = 0;

        for (int k = 0; k < 9; k++) {
            BFObject bfObject = battleField.scanQuadrant(k, y);
            if ((!(bfObject instanceof Blank) && !(bfObject instanceof Water))) {
                result = result + 1;
                if (bfObject instanceof Rock || bfObject instanceof Eagle) {
                    result = result + 1;
                }
            } //else {
              //  if ((y == tankA.getX() / 64 &&
              //          k == tankA.getY() / 64)) {
              //      if (tankA.getArmore() == 1) {
              //          result = result + 2;
              //      } else {
              //          result = result + 1;
              //      }
              //  }
            }
        int i = 0;
        while (i < result) {
            //saveStepAgressor(tank.getClass().getSimpleName(), "move");
            tank.action("fire");
            Thread.sleep(500);
            i++;
        }
    }


    public Runnable processFire(Bullet newBullet) throws Exception {

        bullets.add(newBullet);

        return new Runnable() {

            @Override
            public synchronized void run() {
                final int step = 1;

                for (int i = bullets.size()-1 ; i < bullets.size(); i++ ){

                    final int finalI = i;
                    System.out.println("name " + finalI);

                    new Thread(new Runnable() {
                        @Override
                        public synchronized void run() {

                            while ((bullets.get(finalI).getX() > -14 && bullets.get(finalI).getX() < 590) &&
                                    (bullets.get(finalI).getY() > -14 && bullets.get(finalI).getY() < 590)) {

                                if (bullets.get(finalI).getDirection() == 1) {
                                    bullets.get(finalI).updateY(-step);

                                } else if (bullets.get(finalI).getDirection() == 2) {
                                    bullets.get(finalI).updateY(step);

                                } else if (bullets.get(finalI).getDirection() == 3) {
                                    bullets.get(finalI).updateX(-step);

                                } else {
                                    bullets.get(finalI).updateX(step);
                                }
                               try {
                                    if (processInterception(bullets.get(finalI))) {
                                        bullets.get(finalI).destroy();
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    Thread.sleep(bullets.get(finalI).getSpeed());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (bullets.get(finalI).isDestroyed()) {
                                    break;
                                }
                                try {
                                    Thread.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }
            }
        };
    }


    private boolean processInterception(Bullet bullet) throws InterruptedException {

        this.bullet = bullet;
        int x = bullet.getX() / 64;
        int y = bullet.getY() / 64;

        if (x >= 0 && x < 9 && y >= 0 && y < 9) {
            SimpleBFObject bfObject = battleField.scanQuadrant(y, x);
            if (!bfObject.isDestroyed() && !(bfObject instanceof Blank) && !(bfObject instanceof Water)) {
                bullet.destroy();
                battleField.destroyObject(y, x, bfObject);
                if (bfObject.isDestroyed()) {
                    battleField.updateQuadrante(y, x, "");
                    if (bfObject instanceof Eagle) {
                        td.setGameOver(true);
                        ta.setGameOver(true);
                        System.out.println("AF - game over Eagle");
                        //                   tr.run();
                        saved = false;
                        gameOver(1);
                    }
                }
                return true;
            }

            if (!bullet.isAgressor()) {

                if (x == tankA.getX() / 64 && y == tankA.getY() / 64) {
                    System.out.println("Armor TA - " + tankA.getArmore());
                    bullet.destroy();
                    tankA.destroy();
                    System.out.println("Armor TA - " + tankA.getArmore());
                    if (tankA.isDestroyed) {
                        td.setGameOver(true);
                        ta.setGameOver(true);
                        System.out.println("AF - game over TS");
//                        tr.run();
                        saved = false;
                        gameOver(2);
                        return true;
                    }
                }
            }

            if (bullet.isAgressor()) {
                if (x == tank.getX() / 64 && y == tank.getY() / 64) {
                    bullet.destroy();
                    tank.destroy();
                    if (tank.isDestroyed) {
                        td.setGameOver(true);
                        ta.setGameOver(true);
                        System.out.println("AF - game over TD");
//                    tr.run();
                        saved = false;
                        gameOver(1);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private void gameOver(int i) {

        final JFrame fr = new JFrame("BATTLE FIELD, DAY 2");
        fr.setLocation(550, 100);
        fr.setMinimumSize(new Dimension(500, 500));

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel newName = new JLabel("Game Over");
        panel.add(newName, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        if (i == 1) {
            JLabel winner = new JLabel("Win Computer");
            panel.add(winner, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        }

        if (i == 2) {
            JLabel winner = new JLabel("You Win");
            panel.add(winner, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        }

        JButton btn1 = new JButton("Replay");
        panel.add(btn1, new GridBagConstraints(1, 8, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JButton btn2 = new JButton("New Game");
        panel.add(btn2, new GridBagConstraints(4, 8, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JButton btn3 = new JButton("Exit");
        panel.add(btn3, new GridBagConstraints(9, 8, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fr.setVisible(false);
                frame.setVisible(false);
                Replay replay = new Replay(stepTank);
                try {
                    replay.start();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                fr.setVisible(false);

                Launcher launcher = new Launcher();
                try {
                    launcher.simpleStart();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                fr.setVisible(false);
                System.exit(0);
            }
        });

        fr.getContentPane().add(panel);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);

    }


    public String getAggreQuadrant() {

        long t = System.currentTimeMillis();
        String time = String.valueOf(t);
        String a = time.substring(time.length() - 1);
        int i = Integer.valueOf(a);

        if (i == 1 || i == 4 || i == 7) {
            aggreQuadrant = "512_128";
        } else if (i == 2 || i == 5 || i == 8) {
            aggreQuadrant = "192_64";
        } else if (i == 3 || i == 0 || i == 9 || i == 6) {
            aggreQuadrant = "320_512";
        }
        return aggreQuadrant;

    }

}