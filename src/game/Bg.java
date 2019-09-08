package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bg {
    int x =0;
    int y =0;
    BufferedImage img;
    Bg(){
        try {
            this.img = ImageIO.read(getClass().getResource("bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
