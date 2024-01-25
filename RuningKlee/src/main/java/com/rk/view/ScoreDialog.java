package com.rk.view;

import com.rk.modle.Obstacle;
import com.rk.service.ScoreRecorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreDialog extends JDialog {
    public ScoreDialog(JFrame frame){
        super(frame,true);
        int scorse[] = ScoreRecorder.getScores();
        JPanel scoreP = new JPanel(new GridLayout(4,1));
        scoreP.setBackground(Color.white);
        JLabel title = new JLabel("排行榜",JLabel.CENTER);
        title.setFont(new Font("黑体",Font.BOLD,20));
        title.setForeground(Color.pink);
        JLabel first =  new JLabel("第一名"+scorse[2],JLabel.CENTER);
        JLabel secound =  new JLabel("第二名"+scorse[1],JLabel.CENTER);
        JLabel third =  new JLabel("第三名"+scorse[0],JLabel.CENTER);
        JButton restart  = new JButton("重新开始");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    scoreP.add(title);
    scoreP.add(first);
    scoreP.add(secound);
    scoreP.add(third);
    Container c  = getContentPane();
    c.setLayout(new BorderLayout());
    c.add(scoreP,BorderLayout.CENTER);
    c.add(restart,BorderLayout.SOUTH);
    setTitle(
            "游戏结束"
    );
    int width,height;
    width = height =200;
    int x = frame.getX()+(frame.getWidth() -width)/2;
        int y = frame.getY()+(frame.getHeight() -height)/2;
    setBounds(x,y,width,height);
    setVisible(true);
    }
}
