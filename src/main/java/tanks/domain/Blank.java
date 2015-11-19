package tanks.domain;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Blank extends SimpleBFObject {

    private boolean isDestroyed = false;

    public Blank(int x, int y) {
        super(x, y);

        color = new Color(180, 180, 180);
        try {
            image = ImageIO.read(new File("src/main/resources/greenB.jpg").getAbsoluteFile());
        } catch (IOException e) {throw new IllegalStateException("Can't find tank images.");
        }
    }

    @Override
    public void destroy() throws InterruptedException {
        isDestroyed = false;
    }
}
