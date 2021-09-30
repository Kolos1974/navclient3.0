package data.entity;

import java.math.BigDecimal;

public class SzamlaTetel {

    private String iktSzam;
    private int tetelsorsz;
    private Boolean tetelKitoltve = true;
	private String megnev;
    private BigDecimal mennyiseg;
    private String me;
    private BigDecimal egysegAr;
    private BigDecimal afaalap;
    private BigDecimal afaSzazalek;
    private BigDecimal afaertek;
    private BigDecimal brutto;
    private String kozvSzolg;
    private String afaKulcs;

    //deviza
    private BigDecimal devizaAfaalap;
    private BigDecimal devizaAfaertek;
    private BigDecimal devizaBrutto;

    public String getKozvSzolg() {
        return kozvSzolg;
    }

    public void setKozvSzolg(String kozvSzolg) {
        this.kozvSzolg = kozvSzolg;
    }

    public void setIktSzam(String iktSzam) {
        this.iktSzam = iktSzam;
    }

    public String getIktSzam() {
        return iktSzam;
    }

    public int getTetelsorsz() {
        return tetelsorsz;
    }

    public void setTetelsorsz(int tetelsorsz) {
        this.tetelsorsz = tetelsorsz;
    }

    public Boolean getTetelKitoltve() {
		return tetelKitoltve;
	}

	public void setTetelKitoltve(Boolean tetelKitoltve) {
		this.tetelKitoltve = tetelKitoltve;
	}

    public String getMegnev() {
        return megnev;
    }

    public void setMegnev(String megnev) {
        this.megnev = megnev;
    }

    public BigDecimal getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(BigDecimal mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public BigDecimal getEgysegAr() {
        return egysegAr;
    }

    public void setEgysegAr(BigDecimal egysegAr) {
        this.egysegAr = egysegAr;
    }

    public BigDecimal getAfaalap() {
        return afaalap;
    }

    public void setAfaalap(BigDecimal afaalap) {
        this.afaalap = afaalap;
    }

    public BigDecimal getAfaSzazalek() {
        return afaSzazalek;
    }

    public void setAfaSzazalek(BigDecimal afaSzazalek) {
        this.afaSzazalek = afaSzazalek;
    }

    public BigDecimal getAfaertek() {
        return afaertek;
    }

    public void setAfaertek(BigDecimal afaertek) {
        this.afaertek = afaertek;
    }

    public BigDecimal getBrutto() {
        return brutto;
    }

    public void setBrutto(BigDecimal brutto) {
        this.brutto = brutto;
    }

    public BigDecimal getDevizaAfaalap() {
        return devizaAfaalap;
    }

    public void setDevizaAfaalap(BigDecimal devizaAfaalap) {
        this.devizaAfaalap = devizaAfaalap;
    }

    public BigDecimal getDevizaAfaertek() {
        return devizaAfaertek;
    }

    public void setDevizaAfaertek(BigDecimal devizaAfaertek) {
        this.devizaAfaertek = devizaAfaertek;
    }

    public BigDecimal getDevizaBrutto() {
        return devizaBrutto;
    }

    public void setDevizaBrutto(BigDecimal devizaBrutto) {
        this.devizaBrutto = devizaBrutto;
    }

    public String getAfaKulcs() {
        return afaKulcs;
    }

    public void setAfaKulcs(String afaKulcs) {
        this.afaKulcs = afaKulcs;
    }
}
