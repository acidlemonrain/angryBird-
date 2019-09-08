package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Wall implements Runnable {
    double x;
    double y;
    boolean hitByWallFlag = false;
    double distance =0;
    int thread =  10;
    double g =0.02;
    boolean timer = false;
    boolean hitFlag = false;
    boolean outFlag = false;
    BufferedImage img;
    Speed speed =new Speed(0,0);
    Wall(double x,double y,double g){
        new Thread(this).start();
        this.x =x;
        this.y =y;
        this.g = g;
        try {
            this.img = ImageIO.read(getClass().getResource("brick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void isHistByBird(Bird bird){

        boolean rangex = bird.getX()+50>= this.x && bird.getX()<= this.x +50;
        boolean rangey = bird.getY()+50>= this.y && bird.getY()<= this.y +50;
        if(rangex && rangey && !this.hitFlag){
            this.hitFlag = true;
            this.speed.x = bird.speed.x/2;
            this.speed.y = bird.speed.y/2;
            bird.decSpeed();
        }else {

        }
    }

    void  outConfig(){
        this.outFlag = true;
        this.thread = 10000000;
        this.speed = new Speed(0,0);
    }


    boolean  hitPig(Pig pig){
        boolean bool = false;
        if(this.x>pig.x&&this.x<pig.x+30 &&this.y>pig.y&& this.y <pig.y+30 && !this.hitByWallFlag){
            this.hitByWallFlag = true;
            if(pig.isDead(this.speed)){
              bool = true;
            }else {
                this.speed.y  = -this.speed.y;
                bool = false;
            }
        }
        return  bool;
    }

    void setSpeed(Speed speed){
        this.timer = true;
        this.distance = 0;
        this.hitByWallFlag = true;
        this.hitFlag =true;
        System.out.println("强壮强 速度开始变化");
        System.out.println("原来的速度是");
        System.out.println(this.speed.x +"    "+this.speed.y);

        //我没有速度
        if(this.speed.isStatic()){
            this.speed.x = speed.x*1.1+2;
            this.speed.y = speed.y;
        }
       else {
           //我有速度 ， 他没速度
            if(speed.isStatic()){
                this.speed.x = -this.speed.x;
                this.speed.y =  -this.speed.y;

            }else {
                //我有速度 ， 他也有速度
                this.speed.x = -this.speed.x;
                this.speed.y+=10;
            }
        }
        System.out.println(this.speed.x +"    "+this.speed.y);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(thread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(hitFlag || hitByWallFlag){
                this.speed.y+=this.g;
                this.x+=this.speed.x;
                this.y+=this.speed.y;
            }

            if(this.timer){
                this.distance +=  this.speed.y*this.speed.y + this.speed.x * this.speed.x;
                if(this.distance >= 30 ){
                    this.hitByWallFlag = false;
                }
            }

            if(this.x<-200 || this.x >1200 ||this.y<-200||this.y>1200){
                this.outConfig();
            }

        }
    }
}
