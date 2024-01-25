package com.rk.service;

import com.rk.view.GamePanel;
import com.rk.view.MainFrame;
import com.rk.view.ScoreDialog;

import java.awt.*;

public class FreshThread extends Thread{
    public  static final int FREASH = 144;
    GamePanel p;
    public FreshThread(GamePanel p){
        this.p =p;
    }
    public  void run(){
        while (!p.isFinish()){
            p .repaint();
            try {
                Thread.sleep(FREASH);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Container c = p.getParent();
        while (!(c instanceof MainFrame)){
            c = c.getParent();
        }
        MainFrame frame = (MainFrame) c;
        new ScoreDialog(frame);
        frame.restart();
    }

}
