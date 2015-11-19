package tanks.domain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Brick extends SimpleBFObject {
    private boolean isDestroyed = false;

    public Brick(int x, int y) {
        super(x, y);
        color = new Color(255, 0, 25);
        try {
            image = ImageIO.read(new File("src/main/resources/Brick.jpg").getAbsoluteFile());
        } catch (IOException e) {
        }
    }

    @Override
    public void destroy() throws InterruptedException {
        isDestroyed = true;
    }
}
