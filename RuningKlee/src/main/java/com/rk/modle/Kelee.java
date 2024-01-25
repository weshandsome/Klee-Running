
//kelee 类
package com.rk.modle;

import com.rk.service.FreshThread;
import com.rk.service.Sound;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Kelee {
    public BufferedImage image;
    private  BufferedImage image1,image2,image3;
    public int x,y;
    private int jumpValue = 0;
    private boolean jumpState = false;
    private int stepTimer = 0;
    private final int JUMP_HIGHT = 150;
    private final  int LOWEST_Y = 330;
    private final  int FREASH = FreshThread.FREASH;
    public  Kelee(){
        x = 50;
        y = LOWEST_Y;
        try{
            image1 = ImageIO.read(new File("images/1.png"));
            image2 = ImageIO.read(new File("images/2.png"));
            image3 = ImageIO.read(new File("images/3.png"));
        }catch (IOException e){
        e.printStackTrace();
        }
    }
    private  void  step(){
        //跑步 每40毫秒更换一张图
        int tmp = stepTimer/40%3;
        switch (tmp){
            case 1 : image = image1;break;
            case 2:  image = image2;break;
            default: image = image3;break;
        }
        stepTimer += FREASH;
    }
    public  void  jump(){
        //跳跃 会播放跳跃音效
        if(!jumpState){
            Sound.jump();
        }
        jumpState = true;
    }
    public  void  move(){
        //移动
        /*move()方法会判断恐龙是否处于跳跃状态，
        如果处于跳跃状态，并且恐龙站在地上，
        就让jumpValue跳跃增变量值变为-4，
        让恐龙的纵坐标不断与jumpValue相加，
        纵坐标值越来越小，这样恐龙的图片位置就会越来越高。
        当恐龙纵坐标达到跳跃最大高度时，再让jumpValue的值变为4，
        纵坐标值越来越大，恐龙的图片就会越来越低。当恐龙再次回到地面上时，
        取消跳跃状态。至此，恐龙就完成了一次跳跃动作。
        * */
        step();
        if(jumpState){
            if(y >=LOWEST_Y ){
                jumpValue = -100;
            }
            if(y <=LOWEST_Y-JUMP_HIGHT ){
                jumpValue = 100 ;
            }
        y+=jumpValue;
            if(y >= LOWEST_Y){
                jumpState = false;
            }
        }
    }
    public Rectangle getFootBounds(){
        //获取脚部边界
        return new Rectangle(x+30,y+59,29,18);
    }
    public  Rectangle getHeadBounds(){
        //用于获取头边界
        return new Rectangle(x+66,y+25,32,22);
    }
}
