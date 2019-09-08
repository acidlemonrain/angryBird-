package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Origin {
    int x = 150;
    int y = 750;
    BufferedImage img;
    Origin(){
        try {
            this.img = ImageIO.read(getClass().getResource("origin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
