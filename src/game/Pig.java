package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pig implements Runnable{
    double x;
    double y;
    Speed speed ;
    double health;
    boolean isHitDead = false;
    boolean isHitAlive = false;
    int timer = 5;
    int thread = 200;
    BufferedImage img;
    Pig(double x,double y,double health){
        new  Thread(this).start();
        this.x= x;
        this.y = y;
        try {
            this.img = ImageIO.read(getClass().getResource("pig.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.health = health;
    }
    boolean isDead(Speed speed){


        this.isHitAlive = true;
        if(speed.x*speed.x+speed.y*speed.y > this.health){
            this.speed = new Speed(speed.x,speed.y);
            this.isHitDead = true;
            try {
                this.img = ImageIO.read(getClass().getResource("pigDead.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  true;
        }else {

            return  false;
        }

    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(this.thread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isHitDead){
                this.timer--;
                this.x+=this.speed.x;
                this.y+=this.speed.y;
            }
            if(this.timer<=1 ){
                try {
                    this.img = ImageIO.read(getClass().getResource("c2.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(timer<=2){
                try {
                    this.img = ImageIO.read(getClass().getResource("c3.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else  if(timer<=3){
                try {
                    this.img = ImageIO.read(getClass().getResource("c1.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(this.timer==0){
                this.x= -200;
                this.thread=100000;
            }



        }
    }
}
