package com.hanyuzhou.MemoryManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.hanyuzhou.MemoryManagement.MainFrame.*;
import static java.lang.System.exit;

public class ApplyAndReleasePanel extends JPanel {

    private JButton apply, release;
    private JLabel tip;
    private JLabel appTip, relTip, appName, appSize;
    private JTextField appin, relin, namein;

    ApplyAndReleasePanel() {
        setBounds(50, 110, 550, 110);
        setLayout(null);
        setBackground(Color.white);
        init();
        add(apply);
        add(release);
        add(tip);
        add(appTip);
        add(relTip);
        add(appin);
        add(namein);
        add(relin);
        add(appName);
        add(appSize);
        setVisible(true);
        repaint();
    }

    private void init() {
        tip = new JLabel("申请与释放");
        tip.setForeground(Color.BLUE);
        tip.setBounds(0, 0, 150, 30);

        appTip = new JLabel("申请");
        appTip.setBounds(10, 30, 100, 30);

        relTip = new JLabel("释放");
        relTip.setBounds(360, 30, 100, 30);

        appName = new JLabel("大小");
        appName.setBounds(40, 40, 80, 30);
        appName.setForeground(Color.GRAY);
        appSize = new JLabel("名称");
        appSize.setForeground(Color.GRAY);
        appSize.setBounds(130, 40, 80, 30);

        appin = new JTextField();
        appin.setBounds(10, 70, 80, 30);
        namein = new JTextField();
        namein.setBounds(110, 70, 80, 30);

        relin = new JTextField();
        relin.setBounds(360, 70, 80, 30);
        apply = new JButton("申请");
        apply.setBounds(200, 70, 80, 30);
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int temp = Integer.parseInt(appin.getText());
                int sa = -1;
                int tempSize = 0;
                int maxSize = 1000000;
                if (AloChoose == 0) {
                    for (int i = 0; i < memBlockList.size(); i++) {
                        if (memBlockList.get(i).getSize() >= temp && memBlockList.get(i).getName().equals("未分配")) {
                            sa = i;
                            break;
                        }
                    }
                    if (sa >= 0) {
                        if (memBlockList.get(sa).getSize() == temp) {
                            memBlockList.get(sa).setName(namein.getText());
                            memBlockList.get(sa).setSerial(count);
                            memBlockList.get(sa).setFserial(-1);
                            count++;
                        } else {
                            MemBlock memBlock = new MemBlock();
                            memBlock.setSize(Integer.parseInt(appin.getText()));
                            memBlock.setName(namein.getText());
                            memBlock.setSerial(count);
                            memBlock.setStartAddress(memBlockList.get(sa).getStartAddress());
                            count++;
                            memBlockList.get(sa).setSize(memBlockList.get(sa).getSize() - Integer.parseInt(appin.getText()));
                            memBlockList.get(sa).setStartAddress(memBlockList.get(sa).getStartAddress() - Integer.parseInt(appin.getText()));
                            memBlockList.add(sa, memBlock);
                        }
                    } else {
                        jincou(temp, namein.getText());
                    }
                } else if (AloChoose == 1) {
                    for (int i = 0; i < memBlockList.size(); i++) {
                        if (memBlockList.get(i).getName().equals("未分配")) {
                            if (memBlockList.get(i).getSize() < maxSize && memBlockList.get(i).getSize() >= temp) {
                                sa = i;
                                maxSize = memBlockList.get(i).getSize();
                            }
                        }
                    }
                    if (sa >= 0) {
                        if (maxSize == temp) {
                            memBlockList.get(sa).setName(namein.getText());
                            memBlockList.get(sa).setSerial(count);
                            memBlockList.get(sa).setFserial(-1);
                            count++;
                        } else {
                            MemBlock memBlock = new MemBlock();
                            memBlock.setSize(Integer.parseInt(appin.getText()));
                            memBlock.setName(namein.getText());
                            memBlock.setSerial(count);
                            memBlock.setStartAddress(memBlockList.get(sa).getStartAddress());
                            count++;
                            memBlockList.get(sa).setSize(memBlockList.get(sa).getSize() - Integer.parseInt(appin.getText()));
                            memBlockList.get(sa).setStartAddress(memBlockList.get(sa).getStartAddress() - Integer.parseInt(appin.getText()));
                            memBlockList.add(sa, memBlock);
                        }
                    } else {
                        jincou(temp, namein.getText());
                    }
                } else {
                    for (int i = 0; i < memBlockList.size(); i++) {
                        if (memBlockList.get(i).getName().equals("未分配")) {
                            if (memBlockList.get(i).getSize() > tempSize) {
                                tempSize = memBlockList.get(i).getSize();
                                sa = i;
                            }
                        }
                    }
                    if (tempSize == temp) {
                        memBlockList.get(sa).setName(namein.getText());
                        memBlockList.get(sa).setSerial(count);
                        memBlockList.get(sa).setFserial(-1);
                        count++;
                    } else if (tempSize > temp) {
                        MemBlock memBlock = new MemBlock();
                        memBlock.setSize(Integer.parseInt(appin.getText()));
                        memBlock.setName(namein.getText());
                        memBlock.setSerial(count);
                        memBlock.setStartAddress(memBlockList.get(sa).getStartAddress());
                        count++;
                        memBlockList.get(sa).setSize(memBlockList.get(sa).getSize() - Integer.parseInt(appin.getText()));
                        memBlockList.get(sa).setStartAddress(memBlockList.get(sa).getStartAddress() - Integer.parseInt(appin.getText()));
                        memBlockList.add(sa, memBlock);
                    } else {
                        jincou(temp, namein.getText());
                    }
                }
                freeListPanel.repaint();
                occupiedListPanel.repaint();
                showPanel.repaint();
                appin.setText("");
                namein.setText("");

            }
        });

        release = new JButton("释放");
        release.setBounds(450, 70, 80, 30);
        release.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sa = -1;
                for (int i = 0; i < memBlockList.size(); i++) {
                    if (memBlockList.get(i).getSerial() == Integer.parseInt(relin.getText())) {
                        sa = i;
                        break;
                    }
                }
                System.out.println("to free***" + sa);
                if (sa != -1) {
                    if (sa + 1 == memBlockList.size()) {
                        System.out.println("最后一个");
                        if (memBlockList.get(sa - 1).getName().equals("未分配")) {
                            memBlockList.get(sa - 1).setSize(memBlockList.get(sa - 1).getSize() + memBlockList.get(sa).getSize());
                            memBlockList.remove(sa);
                        } else {
                            memBlockList.get(sa).setName("未分配");
                            memBlockList.get(sa).setFserial(Fcount);
                            Fcount++;
                        }
                    } else if (sa == 0) {
                        System.out.println("第一个");
                        if (memBlockList.get(sa + 1).getName().equals("未分配")) {
                            memBlockList.get(sa).setSize(memBlockList.get(sa).getSize() + memBlockList.get(sa + 1).getSize());
                            memBlockList.get(sa).setName("未分配");
                            memBlockList.get(sa).setFserial(memBlockList.get(sa + 1).getFserial());
                            memBlockList.remove(sa + 1);
                        } else {
                            memBlockList.get(sa).setName("未分配");
                            memBlockList.get(sa).setFserial(Fcount);
                            Fcount++;
                        }
                    } else {
                        System.out.print("*******中间");
                        if (memBlockList.get(sa - 1).getName().equals("未分配") && memBlockList.get(sa + 1).getName().equals("未分配")) {
                            System.out.println("前后");
                            //前后空
                            memBlockList.get(sa-1).setSize(memBlockList.get(sa-1).getSize()+memBlockList.get(sa).getSize() + memBlockList.get(sa + 1).getSize());
                            memBlockList.remove(sa);
                            memBlockList.remove(sa);
                        } else if (memBlockList.get(sa - 1).getName().equals("未分配") && !memBlockList.get(sa + 1).getName().equals("未分配")) {
                            System.out.println("前");
                            //前空
                            memBlockList.get(sa - 1).setSize(memBlockList.get(sa - 1).getSize() + memBlockList.get(sa).getSize());
                            memBlockList.get(sa - 1).setName("未分配");
                            memBlockList.remove(sa);
                        } else if (!memBlockList.get(sa - 1).getName().equals("未分配") && memBlockList.get(sa + 1).getName().equals("未分配")) {
                            System.out.println("后");
                            //后空
                            memBlockList.get(sa).setSize(memBlockList.get(sa).getSize() + memBlockList.get(sa + 1).getSize());
                            memBlockList.get(sa).setName("未分配");
                            memBlockList.get(sa).setFserial(memBlockList.get(sa + 1).getFserial());
                            memBlockList.remove(sa + 1);
                        } else {
                            System.out.println("都不");
                            memBlockList.get(sa).setName("未分配");
                            memBlockList.get(sa).setFserial(Fcount);
                            Fcount++;
                        }
                    }
                } else {
                    // 发生错误
                    exit(8);
                }
                relin.setText("");
                freeListPanel.repaint();
                occupiedListPanel.repaint();
                showPanel.repaint();
            }
        });
    }

    private void jincou(int size, String name) {
        int freeSize = 0;
        for (MemBlock aMemBlockList : memBlockList) {
            if (aMemBlockList.getName().equals("未分配")) {
                freeSize = freeSize + aMemBlockList.getSize();
            }
        }
        if (freeSize < size)
            return;
        memBlockList.removeIf(memBlock -> memBlock.getName().equals("未分配"));
        for (int i = 0; i < memBlockList.size(); i++) {
            if (i == 0) {
                memBlockList.get(i).setStartAddress(memSize);
            } else {
                memBlockList.get(i).setStartAddress(memBlockList.get(i - 1).getStartAddress() - memBlockList.get(i - 1).getSize());
            }
        }
        MemBlock memBlock = new MemBlock();
        memBlock.setName(name);
        memBlock.setSize(size);
        memBlock.setStartAddress(freeSize);
        memBlock.setSerial(count);
        count++;
        memBlockList.add(memBlock);
        if (freeSize == size) {
            return;
        } else {
            MemBlock fBlock = new MemBlock();
            fBlock.setFserial(Fcount);
            Fcount++;
            fBlock.setStartAddress(freeSize - size);
            fBlock.setSize(freeSize - size);
            fBlock.setName("未分配");
            memBlockList.add(fBlock);
        }

    }
}
