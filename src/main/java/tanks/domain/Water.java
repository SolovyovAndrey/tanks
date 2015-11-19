package tanks.domain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Water extends SimpleBFObject {

    private boolean isDestroyed = false;

    public Water(int x, int y) {
        super(x, y);
        color = new Color(0, 0, 255);
        try {
            image = ImageIO.read(new File("src/main/resources/wather.jpg").getAbsoluteFile());
        } catch (IOException e) {
        }
    }

    @Override
    public void destroy() throws InterruptedException {
        isDestroyed = false;
    }
}
