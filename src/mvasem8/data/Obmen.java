package mvasem8.data;

public class Obmen {
    private int kod;
    private double kursValyut;

    public Obmen (int kod, double kursValyut) {
        this.kod = kod;
        this.kursValyut = kursValyut;
    }

    public int getKod () {
        return kod;
    }

    public void setKod (int kod) {
        this.kod = kod;
    }

    public double getKursValyut () {
        return kursValyut;
    }

    public void setKursValyut (double kursValyut) {
        this.kursValyut = kursValyut;
    }
}
