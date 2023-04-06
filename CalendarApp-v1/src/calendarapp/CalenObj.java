/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.awt.*;
/**
 *
 * @author Admin
 */
public abstract class CalenObj implements Comparable<CalenObj> {
    static final int EVENT = 0;
    static final int TASK = 1;
    //category of event and task
    static final String UNCATEGORY= "Uncategory (Chua phan loai)";//phan loaidoi tuong
    static final String BUSINESS = "Business (Cong viec)";//phan loaidoi tuong
    static final String PERSONAL = "Personal (Ca nhan)";//phan loaidoi tuong
    //format of time
    static final DateTimeFormatter FMTIME = DateTimeFormatter.ofPattern("HH:mm");
    static final DateTimeFormatter FMDT= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    static final DateTimeFormatter FMDATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // unit of duration
    static final int MINUTE=0;
    static final int HOUR=1;
    static final int DAY=2;
    static final int WEEK=3;
    static final int MONTH=4;
    static final int YEAR=5;
    // type of repeat
    static final int NUMBERCYCLES = 1;
    static final int TOENDDATE = 2;
    static HashSet<String> list;
    static {
        list = new HashSet<String>();
        list.add(UNCATEGORY);
        list.add(BUSINESS);
        list.add(PERSONAL);
        
    }
    int firstId;//=0 if this is the first event/task in a series of repeating event
    int nextId; //=0 
    String name;
    LocalDateTime dt1;
    boolean repeat;
    int durationRepeat;
    int unitRepeat;
    int typeRepeat;
    int noCycles;
    LocalDate endDate;
    String category;
    String des;
    String note;
    boolean remind;
    int durationRemind;
    int unitRemind;

    public CalenObj(int firstId, String name, LocalDateTime dt1, boolean repeat, int durationRepeat, int unitRepeat, int typeRepeat, int noCycles, LocalDate endDate, String category, String des, String note, boolean remind, int durationRemind, int unitRemind) {
        this.firstId = firstId;
        this.nextId=0;
        this.name = name;
        this.dt1 = dt1;
        this.repeat = repeat;
        this.durationRepeat = durationRepeat;
        this.unitRepeat = unitRepeat;
        this.typeRepeat = typeRepeat;
        this.noCycles = noCycles;
        this.endDate = endDate;
        this.category = category;
        this.des = des;
        this.note = note;
        this.remind = remind;
        this.durationRemind = durationRemind;
        this.unitRemind = unitRemind;
    }

    
    static public String[] getCategoryArray() {
        String[] a = new String[list.size()];
        list.toArray(a);
        return a;
    }
    static LocalDateTime plusDateTime(LocalDateTime dt, int duration, int unit) {
        switch(unit) {
            case (MINUTE) -> {
                return dt.plusMinutes(duration);
            }
            case (HOUR) -> {
                return dt.plusHours(duration);
            }
            case (DAY) -> {
                return dt.plusDays(duration);
            }
            case (WEEK) -> {
                return dt.plusWeeks(duration);
            }
            case (MONTH) -> {
                return dt.plusMonths(duration);
            }
            case (YEAR) -> {
                return dt.plusYears(duration);
            }
            default -> { 
                return dt;
            }
                     
        }
    }
    
    public String toString() {
        return (firstId + "%%" + nextId + "%%" + name  + "%%" + FMDT.format(dt1) + "%%" + String.valueOf(repeat) + "%%" + durationRepeat + "%%" + unitRepeat + "%%" + typeRepeat + "%%" + noCycles + "%%" + FMDATE.format(endDate) + "%%" + category + "%%" + des + "%%" + note + "%%" + String.valueOf(remind) + "%%" + durationRemind + "%%" + unitRemind);
    };  
    abstract String printDetails();
    abstract boolean isFinished();
    abstract LocalDateTime[] getDateTime();
    abstract String getTextLabel(boolean isLong);//white text
    abstract Color getColor();
    abstract CalenObj repeatObj(int duration, int unit);
    abstract void additionalInfoToFrame(EditFrame f);
    void changeRepeat(){
        //Delete all obj with the same id 
        //create new obj with new repeat settings
        
    }
    public int compareTo(CalenObj o2) {
        if (dt1.compareTo(o2.dt1)!= 0) {
            return dt1.compareTo(o2.dt1);
        }
        else {
            return name.compareTo(o2.name);
        }
    }
  
  
}
