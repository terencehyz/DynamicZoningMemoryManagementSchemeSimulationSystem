package com.hanyuzhou.MemoryManagement;

import javax.swing.*;
import java.awt.*;

import static com.hanyuzhou.MemoryManagement.MainFrame.memBlockList;
import static com.hanyuzhou.MemoryManagement.MainFrame.memSize;

public class ShowPanel extends JPanel {

    private JLabel tip;

    ShowPanel(){
        setBounds(50,230,550,500);
        setLayout(null);
        setBackground(Color.white);
        init();
        add(tip);
        setVisible(true);
        repaint();
    }
    void init(){
        tip = new JLabel("图形演示");
        tip.setForeground(Color.BLUE);
        tip.setBounds(0,0,150,30);
    }

    public void paint(Graphics graphics){
        super.paint(graphics);
        removeAll();
        revalidate();
        graphics.drawLine(99,51,99,451);
        graphics.drawLine(99,51,401,51);
        graphics.drawLine(401,51,401,451);
        graphics.drawLine(99,451,401,451);
        add(tip);
        int count =0;
        for(int i=0;i<memBlockList.size();i++)
            if (!memBlockList.get(i).getName().equals("未分配")){
                String num=String.valueOf(memBlockList.get(i).getSerial());
                JLabel label=new JLabel(num);
                String num1=String.valueOf(memBlockList.get(i).getStartAddress());
                JLabel labeladd=new JLabel(num1);
                label.setBounds(20, 450-memBlockList.get(i).getStartAddress()*400/memSize, 50, memBlockList.get(i).getSize()*400/memSize);
                labeladd.setBounds(60, 440-memBlockList.get(i).getStartAddress()*400/memSize, 50, 30);
                panel aPanel=new panel(100, 450-memBlockList.get(i).getStartAddress()*400/memSize, 301, memBlockList.get(i).getSize()*400/memSize, "  占用进程名："+memBlockList.get(i).getName()+"              长度："+memBlockList.get(i).getSize());
                add(labeladd);
                add(label);
                count ++;
            }
        repaint();
    }
}
