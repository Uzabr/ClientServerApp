package mvasem8.data;

import java.util.Date;

public class Contract {
    private int kod;
    private Date dataZakl;
    private int kodClient;
    private int kodSotrudnik;

    public Contract (int kod, Date dataZakl, int kodClient, int kodSotrudnik) {
        this.kod = kod;
        this.dataZakl = dataZakl;
        this.kodClient = kodClient;
        this.kodSotrudnik = kodSotrudnik;
    }

    public int getKod () {
        return kod;
    }

    public void setKod (int kod) {
        this.kod = kod;
    }

    public Date getDataZakl () {
        return dataZakl;
    }

    public void setDataZakl (Date dataZakl) {
        this.dataZakl = dataZakl;
    }

    public int getKodClient () {
        return kodClient;
    }

    public void setKodClient (int kodClient) {
        this.kodClient = kodClient;
    }

    public int getKodSotrudnik () {
        return kodSotrudnik;
    }

    public void setKodSotrudnik (int kodSotrudnik) {
        this.kodSotrudnik = kodSotrudnik;
    }
}
