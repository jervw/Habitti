package com.example.habitti;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class dateCounter {
    DateTime currentDate;

    public dateCounter() {
        this.currentDate = new DateTime();
    }

    public int compareDays(DateTime habbitDate) {
        return Days.daysBetween(habbitDate, currentDate).getDays();
    }
}
