package data.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Szamla {

    public enum SzamlaType {
        B, V, K, G, P, Z, O, DV
    }

    public enum States {
        PROCESSING,
        DONE,
        ABORTED,
        RECEIVED,
        SAVED
    }

    private SzamlaType type;
    private String iktSzam;
    private String stEredeti;
    private String modEredeti;
    private int eredetiTetelCount;
    private String szekod;
    //NOTE: most konstans
    private String category = "NORMAL";
    private Timestamp szdat;
    private double exchangeRate;
	private String fizmodkod;
    private Timestamp esdat;
    //NOTE: most konstans
    private String appearance = "PAPER";
    private Timestamp teljdat;
    private Timestamp bekdat;
    private Timestamp szidoszTol;
    private Timestamp szidoszIg;
    private String devizaNem;

    //OTHER TABLE
    private List<SzamlaTetel> tetels = new ArrayList<>();
    private VeSza veSza;
    private String szamlaSzam;

    //SUMMARY
    private Map<String, VatSummary> vatSummeries;
    private OverallSummary overallSummary;

    public Map<String, VatSummary> getVatSummeries() {
        return vatSummeries;
    }

    public Timestamp getSzidoszTol() {
        return szidoszTol;
    }

    public void setSzidoszTol(Timestamp szidoszTol) {
        this.szidoszTol = szidoszTol;
    }

    public Timestamp getSzidoszIg() {
        return szidoszIg;
    }

    public void setSzidoszIg(Timestamp szidoszIg) {
        this.szidoszIg = szidoszIg;
    }

    public String getStEredeti() {
        return stEredeti;
    }

    public void setStEredeti(String stEredeti) {
        this.stEredeti = stEredeti;
    }

    public String getModEredeti() {
        return modEredeti;
    }

    public void setModEredeti(String modEredeti) {
        this.modEredeti = modEredeti;
    }

    public int getEredetiTetelCount() {
        return eredetiTetelCount;
    }

    public void setEredetiTetelCount(int eredetiTetelCount) {
        this.eredetiTetelCount = eredetiTetelCount;
    }

    public Timestamp getBekdat() {
        return bekdat;
    }

    public void setBekdat(Timestamp bekdat) {
        this.bekdat = bekdat;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getSzdat() {
        return szdat;
    }

    public void setSzdat(Timestamp szdat) {
        this.szdat = szdat;
    }

    public String getFizmodkod() {
        return fizmodkod;
    }

    public void setFizmodkod(String fizmodkod) {
        this.fizmodkod = fizmodkod;
    }

    public Timestamp getEsdat() {
        return esdat;
    }

    public Timestamp getTeljdat() {
        return teljdat;
    }

    public void setTeljdat(Timestamp teljdat) {
        this.teljdat = teljdat;
    }

    public String getSzamlaSzam() {
        return szamlaSzam;
    }

    public void setSzamlaSzam(String szamlaSzam) {
        this.szamlaSzam = szamlaSzam;
    }

    public void setEsdat(Timestamp esdat) {
        this.esdat = esdat;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public VeSza getVeSza() {
        return veSza;
    }

    public void setVeSza(VeSza veSza) {
        this.veSza = veSza;
    }

    public SzamlaType getType() {
        return type;
    }

    public void setType(SzamlaType type) {
        this.type = type;
    }

    public String getIktSzam() {
        return iktSzam;
    }

    public void setIktSzam(String iktSzam) {
        this.iktSzam = iktSzam;
    }

    public String getSzekod() {
        return szekod;
    }

    public void setSzekod(String szekod) {
        this.szekod = szekod;
    }

    public void addTetel(SzamlaTetel szamlaTetel) {
        tetels.add(szamlaTetel);
    }

    public List<SzamlaTetel> getTetels() {
        return tetels;
    }

    public OverallSummary getOverallSummary() {
        return overallSummary;
    }

    public void setDevizaNem(String devizaNem) {
        this.devizaNem = devizaNem;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void calculateSummeries() {
        vatSummeries = new HashMap<>();
        overallSummary = new OverallSummary();
        for (SzamlaTetel tetel : tetels) {
            //VatSummary
            String afaKulcs = tetel.getAfaKulcs();
            VatSummary vatSummary = vatSummeries.get(afaKulcs);
            if (vatSummary == null) {
                vatSummary = new VatSummary(afaKulcs);
            }
            BigDecimal afaalap = tetel.getAfaalap();
            BigDecimal afaertek = tetel.getAfaertek();
            BigDecimal brutto = tetel.getBrutto();
            BigDecimal devizaAfaalap = tetel.getDevizaAfaalap();
            BigDecimal devizaAfaertek = tetel.getDevizaAfaertek();
            BigDecimal devizaBrutto = tetel.getDevizaBrutto();
            vatSummary.addToAfaAlap(afaalap.setScale(0, BigDecimal.ROUND_HALF_UP));
            vatSummary.addToAfaErtek(afaertek);
            vatSummary.addToBrutto(brutto);
            if (this.isDeviza()) {
                vatSummary.addToDevizaAfaAlap(devizaAfaalap.setScale(0, BigDecimal.ROUND_HALF_UP));
                vatSummary.addToDevizaAfaErtek(devizaAfaertek);
                vatSummary.addToDevizaBrutto(devizaBrutto);
            }
            vatSummeries.putIfAbsent(afaKulcs, vatSummary);

            //Overall
            overallSummary.addToAfaAlap(afaalap.setScale(0, BigDecimal.ROUND_HALF_UP));
            overallSummary.addToAfaErtek(afaertek);
            overallSummary.addToBrutto(brutto);
            if (this.isDeviza()) {
                overallSummary.addToDevizaAfaAlap(devizaAfaalap.setScale(0, BigDecimal.ROUND_HALF_UP));
                overallSummary.addToDevizaAfaErtek(devizaAfaertek);
                overallSummary.addToDevizaBrutto(devizaBrutto);
            }
        }
    }

    public boolean isStorno() {
        return stEredeti != null && !stEredeti.isEmpty();
    }

    public boolean isModified() {
        return modEredeti != null && !modEredeti.isEmpty();
    }

    public boolean isDeviza() {
        return this.type == SzamlaType.DV;
    }

    public String getDevizaNem() {
        if (this.devizaNem == null) return "HUF";
        return this.devizaNem;
    }

}
