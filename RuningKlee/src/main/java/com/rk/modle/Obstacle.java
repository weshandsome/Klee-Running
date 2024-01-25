package com.rk.modle;
//import javafx.scene.layout.BackgroundImage;

import com.rk.view.BackgroundImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Obstacle {
    public int x,y;
    public BufferedImage image;
    private BufferedImage box;
    private  BufferedImage torch;
    private int spead;
    public Obstacle(){
        //随机生成火把或箱子
        try{
            //火炬
            torch = ImageIO.read(new File("images/z1.png"));
            //箱子
            box = ImageIO.read(new File("images/z2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        Random r = new Random();
        if(r.nextInt(2)==0){
            image = torch;
        }else {
            image = box;
        }
        x = 1024;
        y = 680- image.getHeight();
        spead = BackgroundImage.SPEED;//障碍速度和背景速度一致
    }
    public  void  move(){
        x-= spead;
    }
    public boolean isLive(){
    if(x<=-image.getWidth()){
        return false;
    }
        return true;
    }
    public Rectangle getBounds(){
        if(image ==torch){
            return new Rectangle(x,y,image.getWidth(),image.getHeight());
        }else
          return  new Rectangle(x,y,image.getWidth(),image.getHeight());
    }





}
