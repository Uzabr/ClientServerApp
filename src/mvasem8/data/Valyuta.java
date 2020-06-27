package mvasem8.data;

public class Valyuta {
    private int kod;
    private String nameVlyuta;
    private int kodObmena;

    public Valyuta (int kod, String nameVlyuta, int kodObmena) {
        this.kod = kod;
        this.nameVlyuta = nameVlyuta;
        this.kodObmena = kodObmena;
    }

    public int getKod () {
        return kod;
    }

    public void setKod (int kod) {
        this.kod = kod;
    }

    public String getNameVlyuta () {
        return nameVlyuta;
    }

    public void setNameVlyuta (String nameVlyuta) {
        this.nameVlyuta = nameVlyuta;
    }

    public int getKodObmena () {
        return kodObmena;
    }

    public void setKodObmena (int kodObmena) {
        this.kodObmena = kodObmena;
    }
}
