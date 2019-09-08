package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class cursor {
    int x =0;
    int y  =0;
    BufferedImage img;
    cursor(){
        try {
            this.img = ImageIO.read(getClass().getResource("cursor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
