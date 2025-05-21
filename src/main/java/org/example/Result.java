package org.example;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private final List<Integer> numeryPrzedmiotow;
    private final List<Integer> ilosci;
    private final int sumaWartosci;
    private final int sumaWag;

    public Result(List<Integer> numeryPrzedmiotow, List<Integer> ilosci, int sumaWartosci, int sumaWag) {
        this.numeryPrzedmiotow = new ArrayList<>(numeryPrzedmiotow);
        this.ilosci = new ArrayList<>(ilosci);
        this.sumaWartosci = sumaWartosci;
        this.sumaWag = sumaWag;
    }

    public List<Integer> getNumeryPrzedmiotow() {
        return new ArrayList<>(numeryPrzedmiotow);
    }

    public List<Integer> getIlosci() {
        return new ArrayList<>(ilosci);
    }

    public int getSumaWartosci() {
        return sumaWartosci;
    }

    public int getSumaWag() {
        return sumaWag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numeryPrzedmiotow.size(); i++) {
            sb.append("No: ").append(numeryPrzedmiotow.get(i))
                    .append(" Ilosc: ").append(ilosci.get(i))
                    .append("\n");
        }
        sb.append("\nWeight: ").append(sumaWag)
                .append("\nValue: ").append(sumaWartosci);
        return sb.toString();
    }
}

