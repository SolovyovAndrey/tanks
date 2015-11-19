package tanks.domain;

import tanks.ActionField;
import tanks.BattleField;
import tanks.service.Destroyable;
import tanks.service.Direction;
import tanks.service.Drawable;
import tanks.service.TankAction;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

public abstract class AbstractTank implements Destroyable, Drawable {

    protected int speed = 10;

    private int x;
    private int y;
    private int armore;

    public boolean isDestroyed = false;
    public boolean Agressor = false;

    private Direction direction;
    private ActionField af;
    private BattleField bf;
    private Bullet bullet;
    public List <TankAction> stepAgressor;

    protected Color tankColor;
    protected Color towerColor;

    protected Image[] images;


    public AbstractTank(ActionField af, BattleField bf) {
        this(af, bf, 64, 512, Direction.RIGHT);
    }


    public AbstractTank(ActionField af, BattleField bf, int x, int y, Direction direction) {

        this.af = af;
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    public void turn(Direction direction) throws Exception {

        this.direction = direction;
        af.processTurn(direction.getId());
    }


    public void draw(Graphics g) {

        if (!isDestroyed) {
            g.drawImage(images[getDirection().getId() - 1], getX(), getY(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }
    }


    public void move() throws Exception {

        af.processMove(this);
    }


    public void fire(boolean Agressors) throws Exception {

        int xB = x;
        int yB = y;
        int i = direction.getId();

            if (i == 1) {
                bullet = new Bullet((xB + 25), (yB - 5), i);
            }

            if (i == 4) {
                bullet = new Bullet((xB + 55), (yB + 25), i);
            }

            if (i == 2) {
                bullet = new Bullet((xB + 25), (yB + 55), i);
            }

            if (i == 3) {
                bullet = new Bullet((xB - 5), (yB + 25), i);
            }

            if (Agressors) {
                bullet.setAgressor(true);
            }
            new Thread(af.processFire(bullet)).start();
    }


    @Override
    public void destroy() throws InterruptedException {

        if (armore == 0) {
            isDestroyed = true;
            updateX(-1000);
            updateY(1000);

        } else {
            armore--;
            isDestroyed = false;
        }
    }


    public void moveToQuadrant(int v, int h) throws Exception {
        af.moveToQuadrant(this, v, h);
    }


    public void moveRandom() throws Exception {

//        while (true) {
            long t = System.currentTimeMillis();
            String time = String.valueOf(t);
            String a = time.substring(time.length() - 1);
            int i = Integer.valueOf(a);
            System.out.println("action");
            if (i == 1 || i == 5) {
                this.action("DOWN");
            } else if (i == 2 || i == 6 || i == 9) {
                this.action("UP");
            } else if (i == 3 || i == 0) {
                this.action("LEFT");
            } else if (i == 4 || i == 7 || i == 8) {
                this.action("RIGHT");
            }
            this.action("move");
            this.action("fire");
 //       }
    }


    public int getArmore() {
        return armore;
    }

    public void setArmore(int armore) {
        this.armore = armore;
    }

    public void updateX(int x) {
        this.x += x;
    }

    public void updateY(int y) {
        this.y += y;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isAgressor() {
        return Agressor;
    }

    public void setAgressor(boolean agressor) {
        Agressor = agressor;
    }

    public void clean() throws Exception {

        this.moveToQuadrant(9, 9);
        this.action("LEFT");

        for (int newX = 8; newX >= 0; newX--) {

            af.namberShotX(this, newX);
            af.saveStepAgressor(this.getClass().getSimpleName(),"UP");
            af.saveStepAgressor(this.getClass().getSimpleName(),"move");
            af.saveStepAgressor(this.getClass().getSimpleName(),"LEFT");

            this.action("UP");
            this.action("move");
            this.action("LEFT");
        }
    }


    public void sleepTank() throws InterruptedException {
        Thread.sleep(400);
    }


    public void action(String actionField) throws Exception {
        if (actionField.equals("move")){
            this.move();
        }
        if (actionField.equals("sleepTank")){
            this.sleepTank();
        }
        if (actionField.equals("UP")){
            this.turn(Direction.UP);
        }
        if (actionField.equals("DOWN")){
            this.turn(Direction.DOWN);
        }
        if (actionField.equals("LEFT")){
            this.turn(Direction.LEFT);
        }
        if (actionField.equals("RIGHT")){
            this.turn(Direction.RIGHT);
        }
        if (actionField.equals("fire")){
            this.fire(isAgressor());
        }
    }


    public void action(Direction direction) throws Exception {

            this.turn(direction);
    }


    public boolean isDestroyed () {

        return isDestroyed;
    }

}