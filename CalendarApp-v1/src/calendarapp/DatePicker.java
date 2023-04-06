/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;

import java.awt.*;
import java.time.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JSpinner.DefaultEditor;

/**
 *
 * @author Admin
 */
public class DatePicker extends JPanel implements ChangeListener{
    JSpinner yyyy;
    JSpinner mm;
    JSpinner dd;
    DatePicker(LocalDate ld) {
        super();
        setSize(65,40);
        yyyy = new JSpinner(new SpinnerNumberModel(ld.getYear(), 1970, 2100,1));
        yyyy.setEditor(new JSpinner.NumberEditor(yyyy, "####"));
        yyyy.addChangeListener(this);
        mm = new JSpinner(new SpinnerNumberModel(ld.getMonthValue(), 1, 12,1));
        mm.addChangeListener(this);
        dd= new JSpinner(new SpinnerNumberModel(ld.getDayOfMonth(), 1, ld.lengthOfMonth(),1));
        yyyy.setBounds(0,0,25,20);
        mm.setBounds(25,0,15,20);
        dd.setBounds(40,0,15, 20);
        JLabel l= new JLabel("yyyy/MM/dd", JLabel.CENTER);
        l.setFont(ObjectLabel.DEFAULTFONT);
        l.setForeground(Color.gray);
        l.setBounds(0,20,65,40);
        add(yyyy); add(mm); add(dd); add(l);
        
        /*JLabel sep1 = new JLabel("/", JLabel.CENTER);
        sep1.setBounds(25,0,10,20);
        this.add(sep1);
        mm.setBounds(30,0,15,20);
        add(mm);
        JLabel sep2 = new JLabel("/", JLabel.CENTER);
        sep2.setBounds(45,0,10,20);
        add(sep2);
        dd.setBounds(55,0,15, 20);
        add(dd);*/
         /*       
        JLabel yLabel = new JLabel("YYYY", JLabel.CENTER);
        yLabel.setForeground(Color.gray);
        //yLabel.setBounds(0,20,15, 20);
        c.gridx=0;c.gridy=1;
        add(yLabel, c);
        JLabel mLabel = new JLabel("MM", JLabel.CENTER);
        mLabel.setForeground(Color.gray);
        c.gridx=1;
        c.gridy=1; 
        add(mLabel, c);
        add(mLabel);        
        JLabel dLabel = new JLabel("dd", JLabel.CENTER);
        yLabel.setForeground(Color.gray);
        c.gridx=2;
        add(dLabel, c);*/
    }
    DatePicker() {
        this(LocalDate.now());
    };
    
    public void changeYearMonth() {
        int max = YearMonth.of((int)(yyyy.getValue()), (int)(mm.getValue())).lengthOfMonth();
        int currentday = (int) (dd.getValue());
        dd.setModel(new SpinnerNumberModel(Math.min(max, currentday), 1, max, 1));
        System.out.println(yyyy.getValue() +" "+ mm.getValue()+" "+ dd.getValue());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        changeYearMonth();
    }
    
    public LocalDate getLocalDate() {
        return YearMonth.of((int)yyyy.getValue(),(int)mm.getValue()).atDay((int)dd.getValue());
    }
    
    
    public void setEditable(boolean b) {
        yyyy.setEnabled(b);
        //((DefaultEditor) yyyy.getEditor()).getTextField().setEditable(b);
        mm.setEnabled(b);
        dd.setEnabled(b);
    }
    
    void setLocalDate(LocalDate ld) {
        yyyy.setValue(ld.getYear());
        mm.setValue(ld.getMonthValue());
        dd.setModel(new SpinnerNumberModel(ld.getDayOfMonth(), 1, ld.lengthOfMonth(),1));
    }
}
