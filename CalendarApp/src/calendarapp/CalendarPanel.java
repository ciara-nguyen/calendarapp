/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;
import javax.swing.*;

/**
 *
 * @author Admin
 */
class CalendarPanel extends JPanel {
    GridBagLayout gbl;
    GridBagConstraints c;
    YearMonth ym;
    JLabel[] wd;
    DayPanel[][] dp;
    CalendarPanel() {
        super();
        setSize(350,490);
        //With gbc
        gbl= new GridBagLayout();
        c= new GridBagConstraints();
        setLayout(gbl);
        wd= new JLabel[7];
        int i, j;
        c.fill = GridBagConstraints.HORIZONTAL;
        for (i=0; i<7; i++) {
            if (i==0) j=i+7; 
            else j=i;
            wd[i] = new JLabel(DayOfWeek.of(j).getDisplayName(TextStyle.SHORT, Locale.US));
            wd[i].setHorizontalAlignment(JLabel.CENTER);
            c.weightx=1.0;
            c.weighty=1.0;
            c.gridx=i;
            c.gridy=1;
            add(wd[i], c);
        }
        dp= new DayPanel[6][7];
        for (i=0; i<6; i++) {
            c.fill = GridBagConstraints.BOTH;
            for (j=0; j<7; j++) {
                dp[i][j] = new DayPanel();
                c.weightx=1.0;
                c.weighty=1.0;
                c.gridx=j;
                c.gridy=i*2+2;
                c.gridheight=2;
                add(dp[i][j],c);
            }
        }
        for (i=0; i<6; i++) {
            for (j=0; j<7; j++) {
                dp[i][j].mainLabel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int h, k;
                        for (k=0; k<6; k++) {
                            for (h=0; h<7; h++) {
                                dp[k][h].mainLabel.setBackground(getBackground());
                            }
                        }
                        ((JLabel)(e.getSource())).setBackground(Color.yellow);
                       
                        int day= Integer.parseInt(((JLabel)(e.getSource())).getText());
                        MainFrame.dp.setLocalDate(ym.atDay(day));
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        ((JLabel)(e.getSource())).setFont(DayPanel.FOCUSFONT);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        ((JLabel)(e.getSource())).setFont(DayPanel.DEFAULTFONT);
                    }
                    
                });
            }
        }
        ym = YearMonth.now();
    }
    CalendarPanel(YearMonth ym) {
        this();
        this.setYearMonth(ym);
    }
    final void setYearMonth(YearMonth ym) {
        int day, week, dayofweek;
        this.reset();
        dayofweek = DayOfWeek.from(ym.atDay(1)).getValue();
        if (dayofweek==7) dayofweek=0;
        week =0;
        LocalDate selected = MainFrame.dp.getLocalDate();
        for (day=1; day <= ym.lengthOfMonth(); day++) {
            dp[week][dayofweek].setDate(ym, day);
            if (selected.compareTo(ym.atDay(day)) ==0) dp[week][dayofweek].mainLabel.setBackground(Color.yellow);
            else dp[week][dayofweek].mainLabel.setBackground(getBackground());
            if (dayofweek == 6) {
                dayofweek=0;
                week++;
            }
            else dayofweek++;
        }
    }
    
    void reset() {
        for (int i= 0; i<6; i++) {
            for (int j=0; j<7; j++) {
                dp[i][j].reset();
            }
        }
    }
    
    void reload() {
        for (int i= 0; i<6; i++) {
            for (int j=0; j<7; j++) {
                dp[i][j].reload();
            }
        }
    }

}