package Haupt_Aufgaben;

import java.util.ArrayList;

public class SortByDate {
    static ArrayList<String> toDo = new ArrayList<String>();
    
    static ArrayList<String> ablaufdatum = new ArrayList<String>();
    
    public static void main(String[] args) {
        add("Task 1", "03.01.2023");
        add("Task 2", "02.05.2023");
        add("Task 3", "03.01.2025");
        sortByDates();
    }
    public static void sortByDates() {
        ArrayList<Integer> daten = formatDates();
        boolean[] printed = new boolean[daten.size()];
        int toPrint = 0;
        int biggest = 0;
        boolean allPrinted = false;
        while(!allPrinted) {
        for (int i = 0; i < daten.size(); i++) {
            if (daten.get(i) > biggest && !printed[i]) {
                biggest = daten.get(i);
                toPrint = i;
    }
}
    System.out.println(toDo.get(toPrint));
    daten.remove(toPrint);
    printed[toPrint] = true;
    biggest = 0;
    allPrinted = true;
    for (boolean i : printed) {
        if (!i) {
            allPrinted = false;
            break;
        }
}
}
    }

    public static ArrayList<Integer> formatDates() {
    ArrayList<Integer> daten = new ArrayList<Integer>();
    for (int i = 0; i < ablaufdatum.size(); i++) {
        String[] cutDate = ablaufdatum.get(i).split("\\."); 
        String fixedDate = cutDate[2] + cutDate[1] + cutDate[0];
        daten.add(Integer.parseInt(fixedDate));
        //pos in fixedDates =0 pos in ablaufdatum
    }
    return daten;
}
public static void add(String toDo, String ablaufdatum) {
    SortByDate.toDo.add(toDo);
    SortByDate.ablaufdatum.add(ablaufdatum);
}
}