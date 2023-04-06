/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class ListPanel extends JScrollPane {
    boolean up;
    LocalDate selectedDate;
    TreeSet<ObjectLabel> eventset;
    TreeSet<ObjectLabel> taskset;
    ListPanel() {
        super();
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setUpcoming();
        setVisible(true);
    }

    ListPanel(LocalDate ld) {
        super();
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setDate(ld);
    }
    void setUpcoming() {
        up = true;
        selectedDate = null;
        removeAll();
        eventset = new TreeSet<>();
        taskset = new TreeSet<>();
        LocalDateTime ldt;
        Set<Integer> keys= MainFrame.MAP.keySet();
        for(Integer key: keys) {
            ldt = MainFrame.MAP.get(key).dt1;
            if ((LocalDateTime.now().compareTo(ldt) <= 0)&&(LocalDateTime.now().plusHours(48).compareTo(ldt) >= 0)) {
                if (MainFrame.MAP.get(key) instanceof MyEvent) {
                    eventset.add(new ObjectLabel(key, true));
                }
                else taskset.add(new ObjectLabel(key, true));
            }
        }
        setAll();
    }
    void setDate(LocalDate ld) {
        up = false;
        selectedDate = ld;
        removeAll();
        eventset = new TreeSet<>();
        taskset = new TreeSet<>();
        Set<Integer> keys = MainFrame.MAP.keySet();
        for (Integer key: keys) {
            System.out.println(MainFrame.MAP.get(key).toString());
            System.out.println(MainFrame.MAP.get(key).dt1.toLocalDate().compareTo(ld));
            if((MainFrame.MAP.get(key).dt1.toLocalDate().compareTo(ld)) == 0) {
                System.out.println(MainFrame.MAP.get(key).toString());
                if (MainFrame.MAP.get(key) instanceof MyEvent) eventset.add(new ObjectLabel(key, true));
                else taskset.add(new ObjectLabel(key, true));
            }
        }
        setAll();
    }
    void setAll() {
        add(new JLabel("Event: "));
        Iterator<ObjectLabel> i = eventset.iterator();
        while(i.hasNext()) {
            add(i.next());
            }
        add(new JLabel("Task:"));
        i = taskset.iterator();
        while(i.hasNext()) {
            add(i.next());
        }
    }
    void reload() {
        if (up) setUpcoming(); 
        else setDate(selectedDate);
    }
}
