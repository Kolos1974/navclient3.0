package data.entity;

import java.math.BigDecimal;

public class OverallSummary {

    private BigDecimal afaalapOverallSum;
    private BigDecimal afaertekOverallSum;
    private BigDecimal bruttoOverallSum;
    // Deviza
    private BigDecimal devizaAfaalapOverallSum;
    private BigDecimal devizaAfaertekOverallSum;
    private BigDecimal devizaBruttoOverallSum;

    public OverallSummary() {
        this.afaalapOverallSum = BigDecimal.ZERO;
        this.afaertekOverallSum = BigDecimal.ZERO;
        this.bruttoOverallSum = BigDecimal.ZERO;
        this.devizaAfaalapOverallSum = BigDecimal.ZERO;
        this.devizaAfaertekOverallSum = BigDecimal.ZERO;
        this.devizaBruttoOverallSum = BigDecimal.ZERO;
    }

    public BigDecimal getAfaalapOverallSum() {
        return afaalapOverallSum;
    }

    public BigDecimal getAfaertekOverallSum() {
        return afaertekOverallSum;
    }

    public BigDecimal getBruttoOverallSum() {
        return bruttoOverallSum;
    }

    public BigDecimal getDevizaAfaalapOverallSum() {
        return devizaAfaalapOverallSum;
    }

    public BigDecimal getDevizaAfaertekOverallSum() {
        return devizaAfaertekOverallSum;
    }

    public BigDecimal getDevizaBruttoOverallSum() {
        return devizaBruttoOverallSum;
    }

    public void addToAfaAlap(BigDecimal b) {
        this.afaalapOverallSum = this.afaalapOverallSum.add(b);
    }

    public void addToAfaErtek(BigDecimal b) {
        this.afaertekOverallSum = this.afaertekOverallSum.add(b);
    }

    public void addToBrutto(BigDecimal b) {
        this.bruttoOverallSum = this.bruttoOverallSum.add(b);
    }

    public void addToDevizaAfaAlap(BigDecimal b) {
        this.devizaAfaalapOverallSum = this.devizaAfaalapOverallSum.add(b);
    }

    public void addToDevizaAfaErtek(BigDecimal b) {
        this.devizaAfaertekOverallSum = this.devizaAfaertekOverallSum.add(b);
    }

    public void addToDevizaBrutto(BigDecimal b) {
        this.devizaBruttoOverallSum = this.devizaBruttoOverallSum.add(b);
    }
}
