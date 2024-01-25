package com.rk.view;

import com.rk.service.ScoreRecorder;
import com.rk.service.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class MainFrame extends JFrame{
    public MainFrame() {
            restart();
            setBounds(340,150,1024,820);
            setTitle("RunningKelee");
        Sound.background();
        ScoreRecorder.init();
        addListener();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public  void restart(){
        Container c = getContentPane();
        c.removeAll();
        GamePanel panel = new GamePanel();
        c.add(panel);
        addKeyListener(panel);
        c.validate();
    }
    private  void  addListener(){
        addWindowListener(new WindowAdapter(){
        public  void  windowClosing(WindowEvent e){
            ScoreRecorder.saveScore();
        }
    });
}

}


