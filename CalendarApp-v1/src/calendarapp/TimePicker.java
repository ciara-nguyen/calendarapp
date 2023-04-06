/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;

import java.awt.Color;
import java.time.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Admin
 */
public class TimePicker extends JPanel {
    JSpinner h;
    JSpinner m;
    TimePicker(LocalTime t) {
        super();
        setSize(30,40);
        h = new JSpinner(new SpinnerNumberModel((int)(t.getHour()), 00, 23,1));
        h.setEditor(new JSpinner.NumberEditor(h, "##"));
        m = new JSpinner(new SpinnerNumberModel((int)(t.getMinute()), 0, 59,1));
        m.setEditor(new JSpinner.NumberEditor(m, "##"));
        h.setBounds(0,0,15,20);
        m.setBounds(0,0,15,20);
        JLabel l= new JLabel("HH:mm", JLabel.CENTER);
        l.setFont(ObjectLabel.DEFAULTFONT);
        l.setForeground(Color.gray);
        l.setBounds(0,20,65,40);
        add(h); add(m); add(l);
    }
    
    TimePicker() {
        this(LocalTime.now());
    }
    
    public LocalTime getTime(){
        return LocalTime.of((int)(h.getValue()), (int) (m.getValue())); 
    }
    
    public void setEditable(boolean b) {
        h.setEnabled(b);
        m.setEnabled(b);
    }
    
    void setTime(LocalTime t) {
        h.setValue(t.getHour());
        m.setValue(t.getMinute());
    }
}
