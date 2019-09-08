package game;

import java.util.ArrayList;

public class Walls {
    ArrayList<Wall> walls;
    int Length ;
    Walls(int n){
        walls = new ArrayList<Wall>();
        //关卡一
        if(n==1){
            for (int x=0 ; x< 8 ; x++){
                for (int y=0; y<10;y++){
                    this.walls.add(new Wall(300+x*40,400+40*y,0.05));
                }
            }
            this.Length = 80 ;
        }
        //关卡二
        if(n == 2){
            for (int x = 0 ; x <10 ;x++ ){
                for (int y =0 ; y < 5 ; y++){
                    this.walls.add(new Wall(500+x*50,100+y*50,0.06));
                }
            }
        }
        this.Length = 50 ;
    }



    //墙装墙（单）
    void isHitByWall(Wall walla,Wall wallb){
        boolean flag1 = !walla.hitByWallFlag && !wallb.hitByWallFlag;
        if(walla.x>=wallb.x && walla.x <wallb.x+10 && walla.y> wallb.y && walla.y < wallb.y+ 10 && flag1){
                walla.setSpeed(wallb.speed);
                wallb.setSpeed(walla.speed);
        }
    }

    //墙撞墙（群）
    void InnerHit (){

        //每个进行判断
        for (int i = 0 ; i<this.Length ; i++){
           //判断逻辑
            for (int k =  i ; k < this.Length ; k++ ){
                    isHitByWall(this.walls.get(i),this.walls.get(k));
            }
        }
    }

    boolean isMoveFinished(){
            boolean bool = true;
            for (Wall wall : this.walls){
                if(wall.speed.speedValue()>0.3 && !wall.outFlag){
                    bool = false;
                }
            }
            return bool;
    }

}
