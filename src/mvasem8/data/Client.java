package mvasem8.data;

public class Client {
    private int kod;
    private String name;
    private String passport;

    public Client (int kod, String name, String passport) {
        this.kod = kod;
        this.name = name;
        this.passport = passport;
    }

    public Client () {

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

    public String getPassport () {
        return passport;
    }

    public void setPassport (String passport) {
        this.passport = passport;
    }

    @Override
    public String toString () {
        return  getKod() + ",  " + getName() + ", " + getPassport();
    }
}
