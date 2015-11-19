package tanks.domain;

import tanks.service.Destroyable;
import tanks.service.Drawable;

import java.awt.*;

public class Bullet implements Drawable, Destroyable {

    private int x;
    private int y;
    private int direction;
    public boolean agressor;

    private int Speed = 3;
    private boolean destroyed;

    public Bullet (int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;

    }


    public void updateX (int x){
        this.x += x;
    }

    public void updateY (int y){
        this.y += y;
    }

    public void destroy (){
        x = -100;
        y = -100;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(255, 255, 0));
        g.fillOval(this.getX(), this.getY(), 14, 14);
    }

    public int getSpeed(){

        return Speed;

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getDirection(){
        return direction;
    }

    public boolean isAgressor() {
        return agressor;
    }

    public void setAgressor(boolean agressor) {
        this.agressor = agressor;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}