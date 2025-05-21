package org.example;

public class Przedmiot {
    private final int numer;
    private final int wartosc;
    private final int waga;

    public Przedmiot(int numer, int wartosc, int waga) {
        this.numer = numer;
        this.wartosc = wartosc;
        this.waga = waga;
    }

    public int getNumer() {
        return numer;
    }

    public int getWartosc() {
        return wartosc;
    }

    public int getWaga() {
        return waga;
    }

    public double getStosunekWartosciDoWagi() {
        return (double) wartosc / waga;
    }

    @Override
    public String toString() {
        return "No: " + numer + " value: " + wartosc + " weight: " + waga;
    }
}
