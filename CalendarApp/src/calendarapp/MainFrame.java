/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Admin
 */
public class MainFrame extends JFrame implements ComponentListener, ChangeListener {
    static final MapObject MAP= new MapObject();
    JButton next, pre;
    JSpinner monthText, yearText;
    JLabel sep, dateLabel;
    static DatePicker dp = new DatePicker();
    static CalendarPanel p1 = new CalendarPanel(YearMonth.now());
    static JTabbedPane p2 = new JTabbedPane();
    static ListPanel upcoming = new ListPanel();
    static ListPanel fulldate = new ListPanel(LocalDate.now());
    JButton b;
    MainFrame() {
        this(400, 800);
    }
    
    MainFrame(int w, int h) {
        super("Calendar App");
        pre= new JButton("< Pre");
        pre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((int)monthText.getValue())==1) {
                    yearText.setValue((int)yearText.getValue() -1);
                    monthText.setValue(12);
                }
                else monthText.setValue((int)monthText.getValue()-1);
            }
            
        });
        add(pre);
        next= new JButton("Next >");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((int)monthText.getValue())==12) {
                    yearText.setValue((int)yearText.getValue() +1);
                    monthText.setValue(1);
                }
                else monthText.setValue((int)monthText.getValue()+1);
            }
        });
        add(next);
        monthText= new JSpinner(new SpinnerNumberModel(LocalDate.now().getMonthValue(), 1, 12, 1));
        monthText.addChangeListener(this);
        add(monthText);
        yearText = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), 1970, 2100, 1));
        yearText.setEditor(new JSpinner.NumberEditor(yearText, "####"));
        yearText.addChangeListener(this);
        add(yearText);
        sep = new JLabel("/");
        sep.setHorizontalAlignment(JLabel.CENTER);
        add(sep);
        dateLabel = new JLabel("Selected date: ");
        dp.yyyy.addChangeListener(this);
        dp.mm.addChangeListener(this);
        dp.dd.addChangeListener(this);
        p1.setBorder(BorderFactory.createLineBorder(Color.black));
        p2.add("Selected Date", fulldate);
        p2.add("Upcoming", upcoming);
        add(p1);
        add(p2);
        add(dateLabel);
        add(dp);
        b = new JButton("+");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                EditFrame nf= new EditFrame();
            }
        });
        add(b);
        setSize(w,h);
        this.setLayout(null);
        addComponentListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setVisible(true);
    }


    @Override
    public void componentResized(ComponentEvent e) {
        int h = getHeight();
        int w= getWidth();
        setSize(w, h);
    }

    @Override
    public void componentMoved(ComponentEvent e) {        
    }

    @Override
    public void componentShown(ComponentEvent e) {        
    }

    @Override
    public void componentHidden(ComponentEvent e) {      
    }
    
    @Override
    public void setSize(int w, int h) {
        super.setSize(w,h);
        pre.setBounds(20, 20, 80, 30);
        monthText.setBounds((int)(w/2-100), 20, 80, 30);
        sep.setBounds((int)(w/2-20),20, 30, 30);
        yearText.setBounds((int)(w/2+10),20, 80, 30);
        next.setBounds(w-110, 20, 80, 30);
        p1.setBounds(20, 70, w-50, (int)((h-150)* 0.7));
        dateLabel.setBounds(20, p1.getHeight() +90, 100, 30);
        dp.setBounds(120, p1.getHeight()+90, 200, 30);
        p2.setBounds(20, p1.getHeight() +120, w-120, h - 180 - p1.getHeight());
        b.setBounds(w-70, h-100, 40, 40);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if ((e.getSource()==monthText)|| (e.getSource()== yearText)) {
            p1.setYearMonth(YearMonth.of((int)yearText.getValue(), (int)monthText.getValue()));
        }
        else {
            fulldate.setDate(dp.getLocalDate());
        }
    }
    
}
