package com.rk.service;

import java.io.FileNotFoundException;

public class Sound {
   static  final  String DIR = "music/";
   static  final  String BACKGROUND = "background.wav";
   static  final  String JUMP1 = "jump1.wav";
   static  final  String JUMP2 = "jump2.wav";
   static  final  String HIT1 = "hit1.wav";
   static  final  String HIT2 = "hit2.wav";
   static  int vflag = 1;//旗帜 判断语音类型
   private  static  void  play(String file,boolean circulate){
      try {
         MusicPlayer player = new MusicPlayer(file,circulate);
         player.play();
      }catch (FileNotFoundException e){
         e.printStackTrace();
      }

   }
static  public void  jump(){//跳跃音效
      if(vflag==1){
         play(DIR+JUMP1,false);
         vflag =  vflag*-1;
      }else {
         play(DIR+JUMP2,false);
         vflag =  vflag*-1;
      }
}

   static  public void  hit(){//受击音效
      if(vflag==1){
         play(DIR+HIT1,false);
         vflag =  vflag*-1;
      }else {
         play(DIR+HIT2,false);
         vflag =  vflag*-1;
      }
   }

   static  public  void background(){
      play(DIR+BACKGROUND,true);
   }
}
