package mvasem8.data;

public class Sotrudnik {
    private int kod;
    private String name;
    private String doljnost;

    public Sotrudnik (int kod, String name, String doljnost) {
        this.kod = kod;
        this.name = name;
        this.doljnost = doljnost;
    }

    public int getKod () {
        return kod;
    }

    public void setKod (int kod) {
        this.kod = kod;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getDoljnost () {
        return doljnost;
    }

    public void setDoljnost (String doljnost) {
        this.doljnost = doljnost;
    }
}
