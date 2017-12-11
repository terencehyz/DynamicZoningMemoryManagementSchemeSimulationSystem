package com.hanyuzhou.MemoryManagement;

import javax.swing.*;
import java.awt.*;

import static com.hanyuzhou.MemoryManagement.MainFrame.showPanel;

public class panel extends JPanel {

    private static final long serialVersionUID = 1L;

    public panel(int x, int y, int width, int height, String text) {
        JLabel jLabel = new JLabel(text);
        jLabel.setOpaque(true);
        jLabel.setVisible(true);
        jLabel.setBackground(new Color(113, 191, 234));
        jLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        jLabel.setBounds(x, y, width, height);
        showPanel.add(jLabel);
    }

    private Color RGBColor(int i, int j, int k) {
        // TODO Auto-generated method stub
        return null;
    }
}