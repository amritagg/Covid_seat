package com.amrit.practice.covidseattracker;

public class Data {
    int center_id;
    String address;
    boolean paid;
    String vaccine_name;
    boolean age18;
    int dose1;
    int dose2;

    public Data(int center_id, String address, boolean paid, String vaccine_name, boolean age18, int dose1, int dose2) {
        this.center_id = center_id;
        this.address = address;
        this.paid = paid;
        this.vaccine_name = vaccine_name;
        this.age18 = age18;
        this.dose1 = dose1;
        this.dose2 = dose2;
    }

    public String getAddress() {
        return address;
    }

    public boolean isPaid() {
        return paid;
    }

    public String getVaccine_name() {
        return vaccine_name;
    }

    public boolean isAge18() {
        return age18;
    }

    public int getDose1() {
        return dose1;
    }

    public int getDose2() {
        return dose2;
    }
}
