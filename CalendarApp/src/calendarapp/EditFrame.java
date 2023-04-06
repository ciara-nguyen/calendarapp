/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.time.*;


/**
 *
 * @author Admin
 */
public class EditFrame extends JFrame implements ActionListener, ItemListener, WindowListener {
    static final String[] TYPES = {"Event", "Task"};
    static final String[] UNITS={"minute", "hour", "day", "week", "month", "year"};
    GridBagLayout gbl;
    GridBagConstraints c;
    int id; 
    JLabel lType;
    JComboBox iType;
    JLabel lName;
    JTextField iName;
    JLabel nameConstraint;
    JLabel ldt1;
    DatePicker dp1; TimePicker tp1;
    JLabel ldt2;
    DatePicker dp2; TimePicker tp2;
    JLabel lLoca;
    JTextField iLoca;
    JCheckBox done;
    JCheckBox cbRepeat;
    JSpinner durationRepeat;
    JComboBox unitRepeat;
    JRadioButton repeatType1;
    JRadioButton repeatType2;
    JSpinner numberCycles;
    DatePicker dpEnd;
    JLabel lCategory;
    JComboBox iCategory;
    JLabel lDes;
    JTextArea iDes;
    JLabel lNote;
    JTextArea iNote;
    JCheckBox cbRemind;
    JSpinner durationRemind;
    JComboBox unitRemind;
    JLabel lBefore;
    JButton bSave;
    JButton bCancel;
    private EditFrame(boolean b) {
        super();
        setSize(600, 600);
        setResizable(false);
        id= 0;
        gbl = new GridBagLayout();
        c= new GridBagConstraints();
        c.fill= GridBagConstraints.BOTH;
        setLayout(gbl);
        lType = new JLabel("You want to add/edit");
        iType = new JComboBox(TYPES);
        //iType.addItemListener(this);
        iType.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                setElementsVisible(true);
                if (iType.getSelectedIndex() == 0) setForEvent();
                else if(iType.getSelectedIndex() == 1) setForTask();
            }
        });
        setVisible(true);
        lName = new JLabel("Name of Task/Event");
        iName = new JTextField ();
        iName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                bSave.setEnabled(Pattern.matches(".*[a-zA-Z]+.*",iName.getText()));
            }
            
        });
        nameConstraint = new JLabel("Name must contain at least 1 alphabetic character (a-z, A-Z)");
        nameConstraint.setFont(ObjectLabel.DEFAULTFONT);
        nameConstraint.setForeground(Color.gray);
        ldt1 = new JLabel();
        dp1= new DatePicker();
        tp1 = new TimePicker();
        ldt2= new JLabel();
        dp2= new DatePicker();
        tp2= new TimePicker();
        lLoca = new JLabel("Location");
        iLoca = new JTextField();
        done = new JCheckBox("Finished", false);
        cbRepeat = new JCheckBox("Repeat after ", false);
        cbRepeat.addItemListener(this);
        durationRepeat = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        unitRepeat = new JComboBox(UNITS);
            for (int i=0; i< unitRepeat.getItemCount(); i++) {
                if (((String)(unitRepeat.getItemAt(i))).equals("week")) {
                    unitRepeat.setSelectedIndex(i);
                    break;
                }
            }
                        
        repeatType1 = new JRadioButton("After a number of times", true);
        repeatType2 = new JRadioButton("Until ", false);
            ButtonGroup bg = new ButtonGroup();
            bg.add(repeatType1); bg.add(repeatType2);
            repeatType1.addItemListener(this);
            repeatType2.addItemListener(this);
            
       // set format for numberCycles
        numberCycles = new JSpinner(new SpinnerNumberModel(5, 1, 1000, 1));
        dpEnd = new DatePicker();
        
        lCategory = new JLabel("Category");
        iCategory = new JComboBox(CalenObj.getCategoryArray());
            for (int i=0; i< iCategory.getItemCount(); i++) {
                if (((String)(iCategory.getItemAt(i))).equals(CalenObj.UNCATEGORY)) {
                    iCategory.setSelectedIndex(i);
                    break;
                }
            }
        lDes = new JLabel("Description ");
        iDes = new JTextArea();
            iDes.setBorder(BorderFactory.createLineBorder(Color.black));
        lNote = new JLabel("Note ");
        iNote = new JTextArea();
            iNote.setBorder(BorderFactory.createLineBorder(Color.black));
        cbRemind = new JCheckBox("Remind me ");
            cbRemind.addItemListener(this);
        durationRemind = new JSpinner(new SpinnerNumberModel(30, 1, 1000, 1));
        unitRemind = new JComboBox(UNITS);
            for (int i=0; i< unitRemind.getItemCount(); i++) {
                if (((String)(unitRemind.getItemAt(i))).equals("minute")) {
                    unitRepeat.setSelectedIndex(i);
                    break;
                }
            }
        lBefore = new JLabel(" before this");
        bSave = new JButton("Save");
            bSave.addActionListener(this);
        bCancel = new JButton("Cancel");
            bCancel.addActionListener(this);
        // add elements to frame
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        add(lType,c);
        c.gridy=0;
        c.gridx=2;
        c.gridwidth=1;
        add(iType,c);
        c.gridx=0; c.gridy=1; c.gridwidth=1 ; c.gridheight = 1;
        add(lName, c);
        c.gridx=1; c.gridy=1; c.gridwidth=2; c.gridheight =1; 
        add(iName, c);
        c.gridx=1; c.gridy=2; c.gridwidth=2; c.gridheight=1;
        add(nameConstraint, c);
        c.gridx=0; c.gridy=3; c.gridwidth=1; c.gridheight=1;
        add(ldt1, c);
        c.gridx=1;
        add(dp1, c);
        c.gridx=2;
        add(tp1, c);
        c.gridx=0; c.gridy=4; c.gridwidth=1; c.gridheight=1;
        add(ldt2, c); 
        c.gridx=1;
        add(dp2, c);
        c.gridx=2; 
        add(tp2, c);
        c.gridx=1; c.gridy=5;
        add(done, c);
        c.gridx=0; c.gridy=6; 
        add(lLoca, c);
        c.gridx=1; c.gridy=6; c.gridwidth=2; 
        add(iLoca, c);
               
        c.gridx=0; c.gridy=7; c.gridwidth=1;
        add(cbRepeat, c);
        c.gridx=1; 
        add(durationRepeat, c);
        c.gridx=2;
        add(unitRepeat,c);
        c.gridx=1; c.gridy=8;
        add(repeatType1, c);
        c.gridx=2;
        add(numberCycles, c);
        c.gridx=1; c.gridy=9;
        add(repeatType2, c);
        c.gridx=2;
        add(dpEnd, c);
        c.gridx=0; c.gridy=10; c.gridwidth=1; c.gridheight=1;
        add(lCategory, c);
        c.gridx=1; c.gridy=10; c.gridwidth=2; c.gridheight=1;
        add(iCategory, c);
        c.gridx=0; c.gridy=11; c.gridwidth=1; c.gridheight =1;
        add(lDes, c);
        c.gridx=1; c.gridy=11; c.gridwidth=2; c.gridheight=4;
        add(iDes, c);
        c.gridx=0; c.gridy=15; c.gridwidth=1; c.gridheight=1;
        add(lNote, c);
        c.gridx=1; c.gridy=15; c.gridwidth=2; c.gridheight=2;
        add(iNote, c);
        c.gridx=0; c.gridy=17; c.gridwidth=1; c.gridheight=1;
        add(cbRemind, c);
        c.gridx=0; c.gridy=18;
        add(durationRemind, c);
        c.gridx=1; add(unitRemind, c);
        c.gridx=2; add(lBefore,c);
        c.fill= GridBagConstraints.NONE;
        c.gridheight=2; c.weighty = 0.5;
        c.gridx=1; c.gridy=20; add(bCancel, c);
        c.gridx=2; add(bSave, c);
        c.gridx=1;
        setElementsVisible(b);
        if (b) setTitle("Edit Task/Event");
        else setTitle("AddNew");
        setVisible(true);

    }
    EditFrame() {
        this(false);
        id = MapObject.maxId+1;
        setEnabledRepeat(false);
        cbRemind.setSelected(true);
        setEnabledRemind(true);
        bSave.setEnabled(false);
    }
    EditFrame(int i) {
        this(true);
        id= i;
        CalenObj o= MainFrame.MAP.get(i);
        iName.setText(o.name);
        dp1.setLocalDate(o.dt1.toLocalDate());
        tp1.setTime(o.dt1.toLocalTime());
        cbRepeat.setSelected(o.repeat);
        durationRepeat.setValue(o.durationRepeat);
        unitRepeat.setSelectedIndex(o.unitRepeat);
        repeatType1.setSelected((o.typeRepeat)==1);
        numberCycles.setValue(o.noCycles);
        cbRepeat.setEnabled(o.firstId == i);
        setEnabledRepeat((o.repeat) && (o.firstId==i));
        dpEnd.setLocalDate(o.endDate);
        for (int k=0; k< iCategory.getItemCount(); k++) {
            if (((String)(iCategory.getItemAt(k))).equals(o.category)) {
                    iCategory.setSelectedIndex(k);
                    break;
                }
        }
        iDes.setText(o.des);
        iNote.setText(o.note);
        cbRemind.setSelected(o.remind);
        durationRemind.setValue(o.durationRemind);
        unitRemind.setSelectedIndex(o.unitRemind);
        setEnabledRemind(o.remind);
        o.additionalInfoToFrame(this);
        if (o instanceof MyEvent) {
            iType.setSelectedIndex(0);
            setForEvent();
        }
        else {
            iType.setSelectedIndex(1);
            setForTask();
        }
        iType.setEnabled(false);

    }
    public void setForEvent() {
        ldt1.setText("From ");
        ldt2.setText("To ");
        dp2.setEditable(true);
        tp2.setEditable(true);
        done.setEnabled(false);
        iLoca.setEditable(true);
        
    }
    public void setForTask() {
        ldt1.setText("Deadline ");
        ldt2.setText("");
        dp2.setEditable(false);
        tp2.setEditable(false);
        done.setEnabled(true);
        iLoca.setEditable(false);
    }
    
    void setEnabledRepeat(boolean b) {
        durationRepeat.setEnabled(b);
        unitRepeat.setEnabled(b);
        repeatType1.setEnabled(b);
        repeatType2.setEnabled(b);
        numberCycles.setEnabled((b)&&(repeatType1.isSelected()));
        dpEnd.setEditable((b)&&(repeatType2.isSelected()));
    }
    
    void setEnabledRemind(boolean b) {
        durationRemind.setEnabled(b);
        unitRemind.setEnabled(b);
    }
    
    void setElementsVisible(boolean b) {
        lName.setVisible(b);
        iName.setVisible(b);
        nameConstraint.setVisible(b);
        ldt1.setVisible(b);
        dp1.setVisible(b);
        tp1.setVisible(b);
        ldt2.setVisible(b);
        dp2.setVisible(b);;
        tp2.setVisible(b);
        done.setVisible(b);
        lLoca.setVisible(b);
        iLoca.setVisible(b);
        cbRepeat.setVisible(b);
        durationRepeat.setVisible(b);
        unitRepeat.setVisible(b);
        repeatType1.setVisible(b);
        repeatType2.setVisible(b);
        numberCycles.setVisible(b);
        dpEnd.setVisible(b);
        lCategory.setVisible(b);
        iCategory.setVisible(b);
        lDes.setVisible(b);
        iDes.setVisible(b);
        lNote.setVisible(b);
        iNote.setVisible(b);
        cbRemind.setVisible(b);
        durationRemind.setVisible(b);
        unitRemind.setVisible(b);
        lBefore.setVisible(b);
        bCancel.setVisible(b);
        bSave.setVisible(b);
        if(b) {
            addWindowListener(this);
            this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
        
    }
    
    private CalenObj getOldCalenObj() {
        if (id > MapObject.maxId) return null;
        else return MainFrame.MAP.get(id);
    }

    private CalenObj getNewCalenObj() {
        CalenObj o;
        String name = iName.getText();
        LocalDateTime dt1 = dp1.getLocalDate().atTime(tp1.getTime());
        Boolean repeat= cbRepeat.isSelected();
        int dRepeat= (int)durationRepeat.getValue();
        int uRepeat= unitRepeat.getSelectedIndex();
        int typeRepeat;
        if (repeatType1.isSelected()) typeRepeat=1; else typeRepeat=2;
        int noCycles = (int)numberCycles.getValue();
        LocalDate endDate = dpEnd.getLocalDate();
        String category= (String) iCategory.getSelectedItem();
        String des = iDes.getText();
        String note = iNote.getText();
        Boolean remind = cbRemind.isSelected();
        int dRemind = (int)durationRemind.getValue();
        int uRemind = unitRemind.getSelectedIndex();
        int firstId;
        if(id > MapObject.maxId) firstId= id;
        else firstId= getOldCalenObj().firstId;
        if ((iType.getSelectedIndex())==CalenObj.EVENT) {
            LocalDateTime dt2 = dp2.getLocalDate().atTime(tp2.getTime());
            String loca = iLoca.getText();
            o = new MyEvent(dt2, loca, firstId, name, dt1, repeat, dRepeat, uRepeat, typeRepeat, noCycles, endDate, category, des, note, remind, dRemind, uRemind);
        }
        else {
            Boolean isDone = done.isSelected();
            o = new MyTask(isDone, firstId, name, dt1, repeat, dRepeat, uRepeat, typeRepeat, noCycles, endDate, category, des, note, remind, dRemind, uRemind);
        }
        if (id > MapObject.maxId) o.nextId=0; 
        else o.nextId= getOldCalenObj().nextId;
        return o;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== bSave) {
            CalenObj o= this.getOldCalenObj();
            CalenObj n= this.getNewCalenObj();
            if (o != null) {
                MainFrame.MAP.removeOne(id);
                MainFrame.MAP.addOne(id, n);
            }
            else {
                MainFrame.MAP.addOne(id, n); 
                MainFrame.MAP.createRepeat(id);
            }
            MainFrame.p1.reload();
            MainFrame.upcoming.reload();
            MainFrame.fulldate.reload();
            this.dispose();
        }
        else if(e.getSource()==bCancel) {
            int a= JOptionPane.showConfirmDialog(this, "Any changes will not be saved?", "Discard task/event", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (a==JOptionPane.OK_OPTION) this.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //cbRepeat; repeatType1; repeatType2; cbRemind
        if(e.getSource()== cbRepeat) setEnabledRepeat(cbRepeat.isSelected());
        if((e.getSource()== repeatType1) ||(e.getSource()==repeatType2)) {
            numberCycles.setEnabled(repeatType1.isSelected());
            dpEnd.setEditable(repeatType2.isSelected());
        };
        if(e.getSource()==cbRemind) setEnabledRemind(cbRemind.isSelected());
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int a= JOptionPane.showConfirmDialog(this, "Any changes will not be saved?", "Discard task/event", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (a==JOptionPane.OK_OPTION) this.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
}
