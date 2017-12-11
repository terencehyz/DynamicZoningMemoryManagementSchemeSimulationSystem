package com.hanyuzhou.MemoryManagement;

import javax.swing.*;
import java.awt.*;

import static com.hanyuzhou.MemoryManagement.MainFrame.memBlockList;

public class FreeListPanel extends JPanel {
    private JLabel tip, serial, startAdd, endAdd, status;
    public JLabel[][] out;

    FreeListPanel() {
        setBounds(650, 20, 500, 350);
        setLayout(null);
        setBackground(Color.white);
        init();
        add(serial);
        add(startAdd);
        add(endAdd);
        add(status);
        add(tip);
        repaint();
    }

    void init() {
        tip = new JLabel("空闲分区");
        tip.setForeground(Color.BLUE);
        tip.setBounds(0, 5, 70, 30);
        serial = new JLabel("编号");
        serial.setBounds(50, 30, 100, 30);
        startAdd = new JLabel("起始地址");
        startAdd.setBounds(150, 30, 100, 30);
        endAdd = new JLabel("长度");
        endAdd.setBounds(250, 30, 100, 30);
        status = new JLabel("状态");
        status.setBounds(350, 30, 100, 30);

        out = new JLabel[8][4];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                out[i][j] = new JLabel();
                out[i][j].setBounds(50 + j * 100, 60 + i * 30, 100, 30);
                add(out[i][j]);
            }
        }
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        for (int i = 0; i < 10; i++) {
            graphics.drawLine(42, 30 + i * 30, 442, 30 + i * 30);
        }
        for (int i = 0; i < 5; i++) {
            graphics.drawLine(42 + i * 100, 30, 42 + i * 100, 300);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                out[i][j].setText("");
            }
        }
        if (memBlockList.size() > 0) {
            for (int i = 0, co = 0; i < memBlockList.size(); i++) {
                if (memBlockList.get(i).getName().equals("未分配")) {
                    for (int j = 0; j < 4; j++) {
                        if (j == 0) {
                            out[co][j].setText(memBlockList.get(i).getFserial() + "");
                        }
                        if (j == 1) {
                            out[co][j].setText(memBlockList.get(i).getStartAddress() + "");
                        }
                        if (j == 2) {
                            out[co][j].setText(memBlockList.get(i).getSize() + "");
                        }
                        if (j == 3) {
                            out[co][j].setText(memBlockList.get(i).getName());
                        }
                    }
                    co++;
                }
            }
        }
    }
}
