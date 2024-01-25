package com.rk.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayer implements Runnable {

File soundFile;
Thread thread;
boolean circulate;
public  MusicPlayer(String filepath,boolean circulate) throws FileNotFoundException {
        this.circulate =circulate;
        soundFile = new File(filepath);
        if(!soundFile.exists()){
    throw new FileNotFoundException("not found mp ");
        }

}
public  void run(){
    byte[] auBuffer  = new byte[1024*128000];
    do {
        AudioInputStream audioInputStream = null;
        SourceDataLine auline = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
            auline.start();
        int btyteCount = 0;
        while (btyteCount!=-1){
            btyteCount = audioInputStream.read(auBuffer,0,auBuffer.length);
            if(btyteCount > 0){
                auline.write(auBuffer,0,btyteCount);
            }
        }
        }catch (IOException e){
            e.printStackTrace();
        }catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        }catch (LineUnavailableException e){
            e.printStackTrace();
        }finally {
            auline.drain();
            auline.close();
        }
    }while(circulate);
}
public void play(){
    thread = new Thread(this);
    thread.start();
}
public  void  stop(){
    thread.stop();
}
}
