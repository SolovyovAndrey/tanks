package tanks.domain;

import tanks.ActionField;
import tanks.BattleField;
import tanks.service.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tiger extends Aggressor {

    private int armore;

    public  Tiger(ActionField af, BattleField bf) {
        super(af, bf);
        setArmore(1);
        speed = 12;
        tankColor = new Color(0, 100, 0);
        towerColor = new Color(0, 185, 0);
        setImages();
    }

    public Tiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        setArmore(1);
        speed = 12;
        setImages();

    }
    private void setImages(){
        images = new Image[4];
        try{
            images[0]= ImageIO.read(new File("src/main/resources/tankDA.png").getAbsoluteFile());
            images[1]= ImageIO.read(new File("src/main/resources/tankDA1.png").getAbsoluteFile());
            images[2]= ImageIO.read(new File("src/main/resources/tankDA2.png").getAbsoluteFile());
            images[3]= ImageIO.read(new File("src/main/resources/tankDA3.png").getAbsoluteFile());
        }catch (IOException e){
            throw new IllegalStateException("Can't find tank images.");
        }
    }

    public void destroy() throws InterruptedException {
        if (this.getArmore() == 0) {
            isDestroyed = true;
        } else {
            this.setArmore(0);
            isDestroyed = false;
        }
    }
}