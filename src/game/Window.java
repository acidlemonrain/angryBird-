package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements MouseListener , MouseMotionListener ,Runnable{
    Panel panel = new Panel();
    boolean drawFlag = false;
    Cursor cursor;


    Window(int x,int y){
//        cursor=Toolkit.getDefaultToolkit().createCustomCursor
//                (new ImageIcon("game/cursor.png").getImage(),new Point(10,20), "stick");
//        this.setCursor(cursor);
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(getClass().getResource("cursor.png")).getImage(),
                new Point(0,0),"custom cursor"));
        //窗口大小
        this.setSize(x,y);
        this.setLocationRelativeTo(null);
        this.add(panel);


        //显现
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始成功打印init
        //add listener
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(this.panel.panelStatus == this.panel.panelInit){
            this.panel.initGame();
        }
        if(this.panel.panelStatus == this.panel.panelEnd){
            this.panel.gameRestart();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    if(this.panel.isInit){
        this.drawFlag = true;
    }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    if(this.panel.isInit  &&this.panel.bird.birdLeft>0){
        this.drawFlag = false;
        this.panel.bird.fly();

    }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {


    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

        if(this.drawFlag && this.panel.panelStatus== this.panel.panelGame &&this.panel.bird.birdLeft>0){
            this.panel.bird.MouseSetPosition(mouseEvent.getX()-150,mouseEvent.getY()-750);
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void run() {

    }

}
