package com.hanyuzhou.MemoryManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.hanyuzhou.MemoryManagement.MainFrame.*;

public class ChoosePanel extends JPanel {
    /*
     * @params
     * ff 首次适应
     * bf 最佳适应
     * wf 最坏适应
     */
    private JLabel tip, inputTip;
    private JButton begin, reset;
    private JRadioButton ff, bf, wf;
    private ButtonGroup choose;
    private JTextField initSize;

    ChoosePanel() {
        setBounds(50, 20, 550, 80);
        setLayout(null);
        setBackground(Color.WHITE);
        Init();
        add(tip);
        add(begin);
        add(reset);
        add(ff);
        add(bf);
        add(wf);
        add(initSize);
        add(inputTip);
        repaint();
    }

    void Init() {
        tip = new JLabel("信息设定");
        tip.setBounds(0, 5, 70, 30);
        tip.setForeground(Color.BLUE);
        begin = new JButton("开始");
        begin.setBounds(450, 5, 80, 30);
        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memSize = Integer.parseInt(initSize.getText());
                if (ff.isSelected()) {
                    AloChoose = 0;
                } else if (bf.isSelected()) {
                    AloChoose = 1;
                } else {
                    AloChoose = 2;
                }
                MemBlock memBlock = new MemBlock();
                memBlock.setStartAddress(memSize);
                memBlock.setSize(memSize);
                memBlock.setName("未分配");
                memBlock.setFserial(Fcount);
                Fcount++;
                memBlockList.add(memBlock);
                freeListPanel.repaint();
            }
        });
        reset = new JButton("重置");
        reset.setBounds(450, 40, 80, 30);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memSize = 0;
                initSize.setText("");
                choose.clearSelection();
                Fcount = 0;
                count = 0;
                memBlockList.clear();
                freeListPanel.repaint();
                occupiedListPanel.repaint();
                showPanel.repaint();
            }
        });
        ff = new JRadioButton("首次适应算法");
        ff.setBackground(Color.white);
        ff.setBounds(100, 5, 110, 30);
        bf = new JRadioButton("最佳适应算法");
        bf.setBackground(Color.white);
        bf.setBounds(220, 5, 110, 30);
        wf = new JRadioButton("最坏适应算法");
        wf.setBackground(Color.white);
        wf.setBounds(340, 5, 110, 30);
        inputTip = new JLabel("设定内存初始大小");
        inputTip.setBounds(100, 40, 125, 30);
        initSize = new JTextField();
        initSize.setBounds(230, 40, 100, 30);
        choose = new ButtonGroup();
        choose.add(ff);
        choose.add(bf);
        choose.add(wf);
    }
}
