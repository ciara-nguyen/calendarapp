/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;

import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.time.*;
import java.util.*;

/**
 *
 * @author Admin
 */
public class DayPanel extends JPanel {
    static final Font DEFAULTFONT= new Font("Calibri", Font.PLAIN, 12);
    static final Font FOCUSFONT= new Font("Calibri", Font.BOLD, 12);
    YearMonth ym;
    int dayOfMonth;
    GridBagLayout gbl;
    GridBagConstraints c;
    JLabel mainLabel;
    ObjectLabel[] objLabel;
    DayPanel() {
        super();
        ym= YearMonth.now();
        dayOfMonth =0;
        gbl = new GridBagLayout();
        c= new GridBagConstraints();
        setBorder(BorderFactory.createLineBorder(Color.gray));
        setLayout(gbl);
        mainLabel = new JLabel();
        mainLabel.setFont(DEFAULTFONT);
        mainLabel.setOpaque(true);
        mainLabel.setBackground(this.getBackground());
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        c.fill= GridBagConstraints.BOTH;
        c.gridx =0;
        c.gridy=0;
        c.weightx=1;
        c.weighty=1;
        c.gridheight=2;
        add(mainLabel,c);
        objLabel = new ObjectLabel[3];
        for (int i=0; i<3; i++) {
            objLabel[i]= new ObjectLabel();
            c.gridx=0;
            c.gridy=i+2;
            c.gridheight=1;
            add(objLabel[i], c);
        }
    }
    DayPanel(YearMonth ym, int day) {
        this();
        setDate(ym, day);
    }

    public void setDate(YearMonth ym, int day) {
        this.ym=ym;
        this.dayOfMonth= day;
        mainLabel.setText(String.valueOf(day));
        reload();
    }
    
    public void reset() {
        int DayOfMonth =0;
        mainLabel.setText("");
        for (int i=0; i<3; i++) objLabel[i].setText("");
    }
    
    public void reload() {
        if (dayOfMonth != 0) {
            int i=0;
            for(Map.Entry m:MainFrame.MAP.entrySet()) {
                if (((CalenObj)(m.getValue())).dt1.toLocalDate().compareTo(ym.atDay(dayOfMonth))==0) {
                    objLabel[i].setObj((int)m.getKey(), false);
                     i++;
                }
                if (i==3) break;
            }
        }
    }
}
