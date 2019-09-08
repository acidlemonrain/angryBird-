package game;

import java.util.ArrayList;

public class Pigs {
    ArrayList<Pig> pigs = new ArrayList<Pig>() ;
    Pigs(int level){
        if(level ==1){
            for (int x = 0 ; x<5 ;x++ ){
                for (int y =0; y<10 ; y ++){
                    this.pigs.add(new Pig(750+x*50,250+y*50,4));
                }
            }
        }
        //关卡2
        if(level == 2){
            for (int x=0 ; x< 5 ; x++){
                for (int y=0; y<5;y++){
                    this.pigs.add(new Pig(500+x*50,500+y*50,3));
                }
            }
        }
    }
}
