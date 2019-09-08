package game;

public class Speed {
    double x =0;
    double y =0;
    Speed(double x, double y ){
        this.x = x;
        this.y = y;
    }

   static Speed mixSpeed(Speed speeda,Speed speedb){
        return  new Speed((speeda.x+speedb.x)/2,(speeda.y+speedb.y/2));
    }



    static Speed  Reverse(Speed speed){
        return  new Speed(-speed.x*0.8 + 2,-speed.y *0.8 +3);
    }

    boolean isStatic(){
        if(this.x<0.1&&this.y<0.1){
            return true;
        }else {
            return  false;
        }
    }
    double speedValue(){
        return  this.x+this.y;
    }
}
