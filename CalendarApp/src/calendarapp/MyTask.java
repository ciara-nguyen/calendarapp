/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;

import static calendarapp.CalenObj.FMTIME;
import java.awt.Color;
import java.time.*;
/**
 *
 * @author Admin
 */
public class MyTask extends CalenObj{
    boolean done;

    public MyTask(boolean done, int firstId, String name, LocalDateTime dt1, boolean repeat, int durationRepeat, int unitRepeat, int typeRepeat, int noCycles, LocalDate endDate, String category, String des, String note, boolean remind, int durationRemind, int unitRemind) {
        super(firstId, name, dt1, repeat, durationRepeat, unitRepeat, typeRepeat, noCycles, endDate, category, des, note, remind, durationRemind, unitRemind);
        this.done = done;
    }
    
    @Override
    public String toString() {
        return(CalenObj.TASK  + "||" + super.toString() + "||" + String.valueOf(done) + "||" + ".");
    }

    @Override
    String printDetails() {
        StringBuffer s= new StringBuffer("");
        s.append("Name of event: " + name + "\n");
        s.append("Deadline: " + dt1.format(CalenObj.FMDT)+ "\n");
        if (done) s.append("You have already completed this task \n");
        else s.append("You haven't completed this task yet \n");
        s.append("Repeat: ");
        if (repeat) {
            s.append("after "+ durationRepeat + " " + EditFrame.UNITS[unitRepeat]);
            if (typeRepeat==1) s.append(" for "+ noCycles + " times " + "\n");
            else s.append(" until " + endDate.format(CalenObj.FMTIME) + "\n");
        }
        else s.append("no" + "\n");
        s.append("Category: " + category + "\n");
        s.append("Description: " + des +"\n");
        s.append("Note: " + note + "\n");
        if (remind) {
            s.append("Remind " + durationRemind + " "+ EditFrame.UNITS[unitRemind] + "before this task");
        }
        else s.append("No remind \n");
        return s.toString();
    }

    @Override
    boolean isFinished() {
        return done;
    }
    
    @Override 
    LocalDateTime[] getDateTime() {
        LocalDateTime[] dt= {dt1};
        return dt;
    }

    @Override 
    String getTextLabel(boolean isLong) {
        if (isLong) {
            StringBuilder sb= new StringBuilder();
            sb.append(dt1.format(FMTIME)).append(" : ").append(name).append(" - ");
            if (done) sb.append("Done");
            else sb.append("Not done yet");
            return sb.toString();
        }
        else return name;
    }
    
        @Override
    Color getColor() {
        if (done) return Color.GREEN;
        else return Color.RED;
    }

    @Override
    CalenObj repeatObj(int duration, int unit) {
        return new MyTask(done, firstId, name, CalenObj.plusDateTime(dt1, duration, unit), repeat, durationRepeat, unitRepeat, typeRepeat, noCycles, endDate, category, des, note, remind, durationRemind, unitRemind);
    }

    @Override
    void additionalInfoToFrame(EditFrame f) {
        f.iType.setSelectedIndex(CalenObj.TASK);
        f.done.setSelected(done);
    }
}
