package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu {
    BufferedImage img;
    Menu(){
        try {
            this.img = ImageIO.read(getClass().getResource("menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
