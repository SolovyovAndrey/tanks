package tanks.domain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by Андрій on 24.10.2015.
 */
public class Batle extends SimpleBFObject{

    public Batle(int x, int y) {
        super(x, y);

        color = new Color(180, 180, 180);
        try {
            image = ImageIO.read(new File("src/main/resources/greenBT.jpg").getAbsoluteFile());
        } catch (IOException e) {throw new IllegalStateException("Can't find tank images.");
        }
    }

    public void draw(Graphics g) {

            g.drawImage(image, 0, 0, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }

    @Override
    public void destroy() throws InterruptedException {

    }
}

