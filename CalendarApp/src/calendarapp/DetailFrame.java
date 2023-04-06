/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class DetailFrame extends JFrame implements ActionListener {
    int id;
    JTextArea t;
    JButton bOK, bDeleteOne, bDeleteAll, bEdit;
    DetailFrame(int id) {
        super("Details");
        this.id= id;
        setSize(500, 300);
        setResizable(false);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);
        t= new JTextArea();
        t.setText(MainFrame.MAP.get(id).printDetails());
        t.setEditable(false);
        c.fill= GridBagConstraints.BOTH;
        c.gridx=0; c.gridy=0; c.gridwidth = 4; c.gridheight = 10;
        add(t, c);
        bOK = new JButton("OK");
        bOK.addActionListener(this);
        bDeleteOne = new JButton("Delete this");
        
        bDeleteOne.addActionListener(this);
        bDeleteAll = new JButton("Delete all repeating");
        bDeleteAll.addActionListener(this);
        bEdit= new JButton("Edit this");
        bEdit.addActionListener(this);
        c.fill= GridBagConstraints.NONE;
        c.gridx=0; c.gridy=11; c.gridwidth=1; c.gridheight=1;
        add(bDeleteAll, c);
        c.gridx=1;
        add(bDeleteOne, c);
        c.gridx=2;
        add(bEdit, c);
        c.gridx=3; 
        add(bOK, c);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== bDeleteAll) {
            int a = JOptionPane.showConfirmDialog(this, "All events/tasks in the series will be removed?", "Confirm Dialog", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (a == JOptionPane.OK_OPTION) {
                MainFrame.MAP.removeAllRepeating(id);
                this.dispose();
            }
        }
        if (e.getSource()== bDeleteOne) {
            int a = JOptionPane.showConfirmDialog(this, "This event/task will be removed?", "Confirm Dialog", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (a == JOptionPane.OK_OPTION) {
                MainFrame.MAP.removeOne(id);
                this.dispose();
            }
        }
        if (e.getSource()== bEdit) {
            /*if (MainFrame.MAP.get(id).firstId != id) {
                int a = JOptionPane.showConfirmDialog(this, "Click Yes if you want to change all events/tasks in repeating series. \n Click No if you only want to change this event/task", "Apply changes for whole repeating series?", JOptionPane.YES_NO_OPTION);
                if (a== JOptionPane.YES_OPTION) {
                    EditFrame ef = new EditFrame(MainFrame.MAP.get(id).firstId);
                }
                else {
                    EditFrame ef = new EditFrame(id);
                }
            }
            else {
                EditFrame ef = new EditFrame(id);
            }*/
            EditFrame ef = new EditFrame(id);
            this.dispose();
        }
        if (e.getSource()== bOK) {
            this.dispose();
        }
    }
    
}
