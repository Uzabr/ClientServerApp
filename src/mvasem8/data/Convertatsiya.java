package mvasem8.data;

import java.util.Date;

public class Convertatsiya {
    private int kod;
    private double sumPerevoda;
    private int kodContarct;
    private int kodValyut;
    private int kodObmen;
    private Date dataConvertatsi;


    public Convertatsiya (int kod, double sumPerevoda, int kodContarct, int kodValyut, int kodObmen, Date dataConvertatsi) {
        this.kod = kod;
        this.sumPerevoda = sumPerevoda;
        this.kodContarct = kodContarct;
        this.kodValyut = kodValyut;
        this.kodObmen = kodObmen;
        this.dataConvertatsi = dataConvertatsi;
    }

    public Date getDataConvertatsi () {
        return dataConvertatsi;
    }

    public void setDataConvertatsi (Date dataConvertatsi) {
        this.dataConvertatsi = dataConvertatsi;
    }

    public int getKod () {
        return kod;
    }

    public void setKod (int kod) {
        this.kod = kod;
    }

    public double getSumPerevoda () {
        return sumPerevoda;
    }

    public void setSumPerevoda (double sumPerevoda) {
        this.sumPerevoda = sumPerevoda;
    }

    public int getKodContarct () {
        return kodContarct;
    }

    public void setKodContarct (int kodContarct) {
        this.kodContarct = kodContarct;
    }

    public int getKodValyut () {
        return kodValyut;
    }

    public void setKodValyut (int kodValyut) {
        this.kodValyut = kodValyut;
    }

    public int getKodObmen () {
        return kodObmen;
    }

    public void setKodObmen (int kodObmen) {
        this.kodObmen = kodObmen;
    }
}
