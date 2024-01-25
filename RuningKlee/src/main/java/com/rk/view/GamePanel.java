package com.rk.view;

import com.rk.modle.Kelee;
import com.rk.modle.Obstacle;
import com.rk.service.FreshThread;
import com.rk.service.ScoreRecorder;
import com.rk.service.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GamePanel  extends JPanel implements KeyListener {
  private BufferedImage image;
  private  BackgroundImage background;
  private Kelee kelee;
  private Graphics2D g2;//
  private  int addObstacleTimer = 0;
  private  boolean finish = false;
  private List<Obstacle> list = new ArrayList<Obstacle>();
  private final  int FREASH = FreshThread.FREASH;
  int score = 0;
  int scoreTimer = 0;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
       if(code==KeyEvent.VK_SPACE){
           kelee.jump();}
    }



    @Override
    public void keyReleased(KeyEvent e) {


    }

    public GamePanel() {
    image = new BufferedImage(1024,820,BufferedImage.TYPE_INT_BGR);
    g2 = image.createGraphics();
    background = new BackgroundImage();
    kelee = new Kelee();
    list.add(new Obstacle());
    FreshThread t = new FreshThread(this);
    t.start();
    }
    public  void paintImage(){
        background.roll();
        kelee.move();
        g2.drawImage(background.image,0,0,this);
        if (addObstacleTimer == 1300){//每1300毫秒生成一个障碍物
            if (Math.random()*100>40){
                list.add(new Obstacle());
            }
            addObstacleTimer = 0;
        }
        for (int i =0;i<list.size();i++){
            Obstacle o = list.get(i);
            if(o.isLive()){
                o.move();//如果 障碍物存活 开始移动
                g2.drawImage(o.image,o.x,o.y,this);
                if((o.getBounds().intersects(kelee.getFootBounds())||(o.getBounds().intersects(kelee.getHeadBounds())))){
                    Sound.hit();
                    gameOver();
                }else {
                    list.remove(i);
                    i--;
                }
            }
            }
        g2.drawImage(kelee.image,kelee.x,kelee.y,this);
        if(scoreTimer >= 500){
            score+=10;
            scoreTimer =0;
        }
        g2.setColor(Color.RED);
        g2.setFont(new Font("黑体",Font.BOLD,24));
        g2.drawString(String.format("%06d",score),500,30);
        addObstacleTimer+=FREASH;
        scoreTimer+=FREASH;
        }
    public  void paint(Graphics g){
        paintImage();
        g.drawImage(image,0,0,this);
    }
    public  void  gameOver(){
        ScoreRecorder.addNewScore(score);
        finish = true;
    }
    public boolean isFinish(){
        return finish;
    }

    }

