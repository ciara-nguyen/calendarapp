/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendarapp;
import java.util.TreeMap;
import java.io.*;
import java.time.*;
import java.util.logging.*;
/**
 *
 * @author Admin
 */
public class MapObject extends TreeMap<Integer, CalenObj>{
    static int maxId;
    static final File F= new File("calendar.txt");
    static {
        if (!((F.exists())&&(F.isFile()))) {
            try {
            F.createNewFile();
            } catch (IOException ex) {
            Logger.getLogger(MapObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    MapObject() {
        super();
        maxId=0;
        try { 
            FileReader fr = new FileReader(F);
            BufferedReader br = new BufferedReader(fr);
            String line;
                int id, firstId, nextId;
                int  durationRepeat, unitRepeat, typeRepeat, noCycles, durationRemind, unitRemind;
                boolean repeat, remind;
                String name, category, des, note;
                LocalDateTime dt1;
                LocalDate endDate;
             
                while((line=br.readLine())!=null) {
                    
                        String[] a= line.split("%%");
                        for (int i=0; i<18; i++) System.out.println(a[i]);
                        id= Integer.parseInt(a[0]);
                        firstId= Integer.parseInt(a[2]);
                        nextId = Integer.parseInt(a[3]);
                        name = a[4];
                        dt1 = LocalDateTime.parse(a[5], CalenObj.FMDT);
                        repeat = Boolean.parseBoolean(a[6]);
                        durationRepeat = Integer.parseInt(a[7]);
                        unitRepeat = Integer.parseInt(a[8]);
                        typeRepeat= Integer.parseInt(a[9]);
                        noCycles= Integer.parseInt(a[10]);
                        endDate = LocalDate.parse(a[11], CalenObj.FMDATE);
                        category = a[12];
                        des = a[13];
                        note = a[14];
                        remind = Boolean.parseBoolean(a[15]);
                        durationRemind= Integer.parseInt(a[16]);
                        unitRemind = Integer.parseInt(a[17]);
                        
                        if((Integer.parseInt(a[1]))== CalenObj.EVENT) {
                            LocalDateTime dt2 = LocalDateTime.parse(a[18], CalenObj.FMDT);
                            String loca= a[19];
                            this.put(id, new MyEvent(dt2, loca, firstId, name, dt1, repeat, durationRepeat, unitRepeat, typeRepeat, noCycles, endDate, category, des, note, remind, durationRemind, unitRemind));
                            
                        }
                        else if (Integer.parseInt(a[1])== CalenObj.TASK) {
                            boolean done = Boolean.parseBoolean(a[18]);
                            this.put(id, new MyTask(done, firstId, name, dt1, repeat, durationRepeat, unitRepeat, typeRepeat, noCycles, endDate, category, des, note, remind, durationRemind, unitRemind));
                        }
                        this.get(id).nextId= nextId;

                }
                br.close();
                if (!isEmpty()) maxId = lastKey();
            
            }
            catch(Exception e) {
                e.printStackTrace();
            }
    }
    void addOne(int k, CalenObj o) {
        
        BufferedWriter w= null;
        try {
            if (containsKey(k)) {
                System.out.println("cant add a new value to key "+ k);
            }
            else {
                put(k, o);
                w= new BufferedWriter(new FileWriter(F, true));
                w.append(k + "%%" + o.toString() + "\n");
                w.close();                
            }
            maxId= lastKey();
        } catch (Exception e) {
            Logger.getLogger(MapObject.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    void removeOne(int i) {
        remove(i);
        FileWriter w = null;
        BufferedReader r= null;
        String line;
        StringBuffer bf = new StringBuffer();
        String start= new String(i+"%%");
        try {
            w = new FileWriter(F);
            r= new BufferedReader(new FileReader(F));
            while ((line=r.readLine())!=null) {
                if (!(line.startsWith(start))) {
                    bf.append(line + "\n");
                }
            }
            w.append(bf);
            w.flush();  
            
        } catch (IOException ex) {
            Logger.getLogger(MapObject.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                w.close();
                r.close();
            } catch (IOException ex) {
                Logger.getLogger(MapObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    void removeAllRepeating(int i) {
        int id = get(i).firstId;
        int idnext;
        while (id != 0) {
            idnext = get(id).nextId;
            removeOne(id);
            id= idnext;
        }
    }
    void createRepeat(int i) {
        CalenObj o = get(i);

        if (o.repeat) {
            if (o.typeRepeat == CalenObj.NUMBERCYCLES) {
                o.endDate = (CalenObj.plusDateTime(o.dt1, o.durationRepeat*(o.noCycles-1), o.unitRepeat)).toLocalDate();
            }
            else {
                o.noCycles = 1; 
                LocalDateTime d= CalenObj.plusDateTime(o.dt1, o.durationRepeat, o.unitRepeat);
                while (d.toLocalDate().compareTo(o.endDate) <=0) {
                    o.noCycles++;
                    d= CalenObj.plusDateTime(d, o.durationRepeat, o.unitRepeat);
                }
            }
            if (o.noCycles > 1) {
                CalenObj pre = o;
                CalenObj next;
                for (int k=2; k<= (o.noCycles); k++) {
                    next = pre.repeatObj(o.durationRepeat, o.unitRepeat);
                    addOne(++maxId, next);
                    pre.nextId = maxId;
                    pre= next;
                }
            }
        }
    }
    

    
    CalenObj getFirstOfSeries(int i) {
        if (containsKey(i)) {
            return get(get(i).firstId);
        }
        else return null;
        
    }
    
    CalenObj getNextOfSeries(int i) {
        if (containsKey(i)) {
            if (containsKey(get(i).nextId)) return get(get(i).nextId);
            else return null;
            
        }
        else return null;
    }

}
