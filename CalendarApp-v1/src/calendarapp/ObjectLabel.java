/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;

import javax.swing.*;
import java.time.*;
import java.time.format.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author Admin
 */
public class ObjectLabel extends JLabel implements MouseListener, Comparable<ObjectLabel> {
    static final Font DEFAULTFONT= new Font("Calibri", Font.PLAIN, 10);
    static final Font FOCUSFONT= new Font("Calibri", Font.BOLD, 10);
    int i;
    Boolean isLong;
    ObjectLabel(int i, Boolean isLong) {
        this();
        setObj(i, isLong);
        
    }
    
    ObjectLabel() {
        super();
        i=0;
        isLong = false;
    }
    
    public void setObj(int i, Boolean isLong) {
        this.i=i;
        CalenObj calenObj= MainFrame.MAP.get(i);
        this.isLong = isLong;
        setText(calenObj.getTextLabel(isLong));
        setForeground(calenObj.getColor());
        setFont(DEFAULTFONT);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DetailFrame df = new DetailFrame(i);//Open new JFrame to see Details of Event/Task
    }

    @Override
    public void mousePressed(MouseEvent e) {};

    @Override
    public void mouseReleased(MouseEvent e) {};

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setFont(FOCUSFONT);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setFont(DEFAULTFONT);
    }

    @Override
    public int compareTo(ObjectLabel o) {
        return MainFrame.MAP.get(i).dt1.compareTo(MainFrame.MAP.get(o.i).dt1);
    }
}
