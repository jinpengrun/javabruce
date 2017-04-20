package com.jd.www.book.youhua.designoptimize.observer.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zhujinpeng on 2017/4/20.
 */
public class BtnListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

    }

    public static void main(String[] args) {
        JFrame p = new JFrame();
        JButton btn = new JButton("FUCK ME");
        btn.addActionListener(new BtnListener());
        btn.addActionListener(new BtnListener());
        p.add(btn);
        p.pack();
        p.setVisible(true);
    }
}
