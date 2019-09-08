package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel  extends JPanel implements Runnable {
    Bird bird ;
    Walls walls ;
    int panelStatus = 0;
    int panelInit = 0;
    int panelGame = 1;
    int panelEnd = 2;
    int panelLast = 3;

    Origin origin = new Origin();
    Bg bg = new Bg();
    Menu menu = new Menu();
    boolean isInit = false;
    int Level = 1;
    int hitPigs = 0;
    Pigs pigs  ;
    Panel(){



    }
    void gameRestart(){
        this.hitPigs = 0;
         if(isWin()){
             if(this.Level==2){

             }else {
                 this.Level = this.Level+1;
                 this.initLevel(this.Level);
             }
         }else {
             this.initLevel(this.Level);
         }
    }
    boolean isWin(){
        return  this.hitPigs >= 20;
    }
    void isEndGame(){
        if(this.bird.birdLeft<=0&&this.walls.isMoveFinished()&&this.bird.isOut()){
            this.panelStatus = this.panelEnd;

        }
    }
    void initGame(){
         this.initLevel(this.Level);
         this.isInit = true;
         new Thread(this).start();
    }
    void initLevel(int level){
        this.hitPigs =0;
        System.out.println("again");
        this.bird = new Bird();
        this.walls = new Walls(level);
        this.pigs = new Pigs(level);
        this.panelStatus = this.panelGame;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.white);


        if(panelStatus == panelInit){
            //Welcome Page!😃
           g.drawImage(this.menu.img,0,0,null);

        }


        //游戏中
        if(panelStatus == panelGame){

            //地面
            g.drawImage(this.bg.img,0,0,null);
            //画弓
            g.drawImage(this.origin.img,this.origin.x-20,this.origin.y,null);
            //画鸟✈
            g.drawImage(this.bird.img,this.bird.getX(),this.bird.getY(),null );

            //画墙🏟
            this.walls.walls.forEach(wall-> g.drawImage(wall.img,(int)wall.x,(int)wall.y,null) );
            //画猪🐖
            g.setColor(Color.green);
            this.pigs.pigs.forEach(pig->  g.drawImage(pig.img,(int)pig.x,(int)pig.y,null) );
            //ui
            g.drawString("得分:"+this.hitPigs,0,10);
            g.drawString("剩余小鸟数量:"+this.bird.birdLeft,0,20);
            g.drawString("动画结束:"+this.walls.isMoveFinished(),0,30);

        }

        //游戏结束✌
        if(panelStatus == panelEnd){
           if(!this.isWin()){

               g.drawString("你输了 点击重新开始" ,500,500);
           }else {
               if(this.Level == 2){
                   g.drawString("你已经通过了所有关卡！！！" ,500,500);
               }else {
                   g.drawString("你赢了 点击加入下一关" ,500,500);
               }

           }
        }

    }


    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           if(this.panelStatus == this.panelGame){
               //重绘

               this.repaint();
               //鸟撞墙判定
               this.walls.walls.forEach(wall -> wall.isHistByBird(this.bird));
               //墙撞猪
               for (Pig  pig  :  this.pigs.pigs){
                   for( Wall wall : this.walls.walls){
                       if(wall.hitPig(pig)){
                           this.hitPigs++;
                       }
                   }
               }
               //墙撞墙
               this.walls.InnerHit();
               //游戏状态判断
               this.isEndGame();
           }
        }
    }
}
