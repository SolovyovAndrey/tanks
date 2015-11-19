package tanks.domain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Eagle extends SimpleBFObject {

    private boolean isDestroyed = false;

    public Eagle(int x, int y) {
        super(x, y);
        color = new Color(255, 255, 0);
        armor = 1;
       try {
            image = ImageIO.read(new File("src/main/resources/Eagle.jpg").getAbsoluteFile());
        } catch (IOException e) {
        }
    }

    public void destroy() throws InterruptedException {
        if (this.getArmor() == 0){
            isDestroyed = true;
        } else {armor --;}
    }
}
