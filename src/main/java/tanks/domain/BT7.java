package tanks.domain;

import tanks.ActionField;
import tanks.BattleField;
import tanks.service.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BT7 extends AbstractTank {

    public BT7(ActionField af, BattleField bf) {
        super(af, bf);
        speed = 8;
        tankColor = new Color(255, 0, 0);
        towerColor = new Color(0, 255, 0);
        setImages();
    }

    public BT7(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        speed = 8;
        tankColor = new Color(255, 0, 0);
        towerColor = new Color(0, 255, 0);
        setImages();

    }

    private void setImages(){
        images = new Image[4];
        try{
            images[0]= ImageIO.read(new File("src/main/resources/tankDS.png").getAbsoluteFile());
            images[1]= ImageIO.read(new File("src/main/resources/tankDS1.png").getAbsoluteFile());
            images[2]= ImageIO.read(new File("src/main/resources/tankDS2.png").getAbsoluteFile());
            images[3]= ImageIO.read(new File("src/main/resources/tankDS3.png").getAbsoluteFile());
        }catch (IOException e){
            throw new IllegalStateException("Can't find tank images.");
        }
    }

}