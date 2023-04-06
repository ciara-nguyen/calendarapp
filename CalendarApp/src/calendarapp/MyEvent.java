/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;
import java.time.*;
import java.awt.*;
/**
 *
 * @author Admin
 */
public class MyEvent extends CalenObj {
    LocalDateTime dt2;
    String loca;

    public MyEvent(LocalDateTime dt2, String loca, int firstId, String name, LocalDateTime dt1, boolean repeat, int durationRepeat, int unitRepeat, int typeRepeat, int noCycles, LocalDate endDate, String category, String des, String note, boolean remind, int durationRemind, int unitRemind) {
        super(firstId, name, dt1, repeat, durationRepeat, unitRepeat, typeRepeat, noCycles, endDate, category, des, note, remind, durationRemind, unitRemind);
        this.dt2 = dt2;
        this.loca = loca;
    }

    @Override
    public String toString() {
        return(CalenObj.EVENT  + "%%" + super.toString() + "%%" + CalenObj.FMDT.format(dt2) + "%%" + loca + "%%" + ".");
    }

    @Override
    String printDetails() {
        StringBuffer s= new StringBuffer("");
        s.append("Name of event: " + name + "\n");
        s.append("From: " + dt1.format(CalenObj.FMDT)+ "\n");
        s.append("To: " + dt2.format(CalenObj.FMDT)+ "\n");
        s.append("At: " + loca +"\n");
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
            s.append("Remind " + durationRemind + " "+ EditFrame.UNITS[unitRemind] + "before this event");
        }
        else s.append("No remind \n");
        return s.toString();
    }
    
    @Override
    boolean isFinished() {
        if (LocalDateTime.now().compareTo(dt2) > 0) return true;
        else return false;
    }

    @Override 
    LocalDateTime[] getDateTime() {
        LocalDateTime[] dt= {dt1, dt2};
        return dt;
    }
    
    @Override 
    String getTextLabel(boolean isLong) {
        if (isLong) {
            StringBuilder sb= new StringBuilder();
            sb.append(dt1.format(FMTIME)).append(" - ").append(dt2.format(FMTIME));
            if (!(dt2.toLocalDate().equals(dt1.toLocalDate()))) {
                sb.append(" (+");
                sb.append(String.valueOf(Period.between(dt1.toLocalDate(), dt2.toLocalDate()).getDays()));
                sb.append(" days)");
            }
            sb.append(" : ").append(name).append(" at ").append(loca);
            return sb.toString();
        }
        else return name;
    }
    
    @Override
    Color getColor() {
        if(dt2.compareTo(LocalDateTime.now()) < 0) return Color.DARK_GRAY;
        else return Color.BLUE;
    }

    @Override
    CalenObj repeatObj(int duration, int unit) {
        return new MyEvent(CalenObj.plusDateTime(dt2, duration, unit), loca, firstId, name, CalenObj.plusDateTime(dt1, duration, unit), repeat, durationRepeat, unitRepeat, typeRepeat, noCycles, endDate, category, des, note, remind, durationRemind, unitRemind);
        
    }

    @Override
    void additionalInfoToFrame(EditFrame f) {
        f.iType.setSelectedIndex(CalenObj.EVENT);
        f.dp2.setLocalDate(dt2.toLocalDate());
        f.tp2.setTime(dt2.toLocalTime());
        f.iLoca.setText(loca);
    }

}
