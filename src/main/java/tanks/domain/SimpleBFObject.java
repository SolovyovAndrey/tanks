package tanks.domain;

import tanks.service.BFObject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;


public abstract class SimpleBFObject implements BFObject {

    private int x;
    private int y;

    protected int armor = 0;
    protected Color color;
    public Image image;

    private boolean isDestroyed = false;

    public SimpleBFObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void destroy(SimpleBFObject object) {
        if (object.getArmor() == 0){
            isDestroyed = true;
        } else {armor --;}
    }

    @Override
    public void draw(Graphics g) throws IOException {
        if (!isDestroyed) {
            if (image != null) {
                g.drawImage(this.image, this.x, this.y,new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            } else {
                    g.setColor(this.color);
                    g.fillRect(this.getX(), this.getY(), 64, 64);

            }
        }
    }

    public int getArmor() {
        return armor;
    }

    public boolean isDestroyed (){
        return isDestroyed;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}