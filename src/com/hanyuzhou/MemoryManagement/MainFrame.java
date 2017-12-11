package com.hanyuzhou.MemoryManagement;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class MainFrame extends JFrame {

    public static ChoosePanel choosePanel = null;
    public static ApplyAndReleasePanel applyAndReleasePanel = null;
    public static FreeListPanel freeListPanel = null;
    public static OccupiedListPanel occupiedListPanel = null;
    public static ShowPanel showPanel = null;

    public static List<MemBlock> memBlockList = new LinkedList<MemBlock>();

    public static int count = 0; //申请id
    public static int Fcount = 0;
    public static int AloChoose = 0; //算法选择
    public static int memSize = 0;


    MainFrame() {
        setTitle("采用动态分区存储器管理方案的模拟系统");
        setBounds(0, 0, 1200, 770);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        choosePanel = new ChoosePanel();
        add(choosePanel);
        applyAndReleasePanel = new ApplyAndReleasePanel();
        add(applyAndReleasePanel);
        freeListPanel = new FreeListPanel();
        add(freeListPanel);
        occupiedListPanel = new OccupiedListPanel();
        add(occupiedListPanel);
        showPanel = new ShowPanel();
        add(showPanel);
        repaint();
    }

    public static MainFrame mainFrame;

    public static void main(String[] args) {
        mainFrame = new MainFrame();
    }
}
