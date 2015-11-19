package tanks.domain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Rock extends SimpleBFObject {

    private boolean isDestroyed = false;

    public Rock(int x, int y) {
        super(x, y);
        color = new Color(255, 0, 255);
        armor = 1;
        try {
            image = ImageIO.read(new File("src/main/resources/Rock.jpg").getAbsoluteFile());
        } catch (IOException e) {
            throw new IllegalStateException("Can't find tank images.");
        }
    }

    public void destroy() throws InterruptedException {
        if (this.getArmor() == 0) {
            isDestroyed = true;
        } else {
            armor--;
        }
    }


}
