package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bird implements  Runnable{
    double x=0;
    double y=0;
    boolean flyFlag = false;
    int birdLeft = 3;
    BufferedImage img;
    int getX(){
        return  (int)x+150 - 25;
    }
    int getY(){
        return (int)y+750 - 25;
    }
    Speed speed = new Speed(0,-0);
    void MouseSetPosition(int x, int y){
           if(!this.flyFlag){
               this.x = x -8 ;
               this.y= y -32;
               if(this.x*this.x+this.y+this.y*this.y>=5000){
                   while (this.x*this.x+this.y+this.y*this.y>=5000){
                       this.range();
                   }
               }
           }

    }
    boolean isOut(){
        if(this.getX()<0||this.getX()>1000||this.getY()<0||this.getY()>1000){
            return  true;
        }else {
            return  false;
        }
    }

    void decSpeed(){
        this.speed.x= this.speed.x/4 +2;
        this.speed.y= this.speed.y*1.04;
    }

    void init(){
        int x= 0;
        int t= 0;
        this.flyFlag = false;
        this.speed = new Speed(0,0);
    }
    void range(){
        if(this.x>0){
            this.x-=5;
        }else {
            this.x+=5;
        }
        if(this.y>0){
            this.y-=5;
        }else {
            this.y+=5;
        }
    }
    Bird(){
        try {
            this.img = ImageIO.read(getClass().getResource("bird.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(this).start();

    }
    void  fly(){
        if(this.x*this.x+this.y+this.y*this.y>=5000 ||this.x*this.x+this.y*this.y<1000){

        }else {
            this.flyFlag = true;
            this.speed.x = -this.x*0.4;
            this.speed.y = -this.y*0.3;
            this.birdLeft--;
        }


    }
    @Override
    public void run() {
     while (true){
         try {
             Thread.sleep(10);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
                if(this.flyFlag){
                    this.speed.y+= 0.19;
                    this.x+=this.speed.x;
                    this.y+=this.speed.y;
                }
                if(this.getX()<-500||this.getX()>1500||this.getY()<-500||this.getY()>1500){
                    this.init();
                }
     }
    }
}
