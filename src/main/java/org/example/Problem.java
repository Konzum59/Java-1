package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Problem {
    private final int liczbaRodzajow;
    private final int ziarno;
    private final int dolnyZakres;
    private final int gornyZakres;
    private final List<Przedmiot> przedmioty;


    public Problem(int n, int seed, int lowerBound, int upperBound) {
        if (n <= 0) {
            throw new IllegalArgumentException("Liczba przedmiotów musi być dodatnia");
        }
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("Dolny zakres musi być mniejszy od górnego");
        }

        this.liczbaRodzajow = n;
        this.ziarno = seed;
        this.dolnyZakres = lowerBound;
        this.gornyZakres = upperBound;
        this.przedmioty = new ArrayList<>();

        generujPrzedmioty();
    }

    private void generujPrzedmioty() {
        Random rand = new Random(ziarno);
        int zakres = gornyZakres - dolnyZakres + 1;

        for (int i = 0; i < liczbaRodzajow; i++) {
            int wartosc = dolnyZakres + rand.nextInt(zakres);
            int waga = dolnyZakres + rand.nextInt(zakres);
            przedmioty.add(new Przedmiot(i, wartosc, waga));
        }
    }


    public Result solve(int capacity) {

        if (capacity <= 0) {
            return new Result(new ArrayList<>(), new ArrayList<>(), 0, 0);
        }

        List<Przedmiot> sortedItems = new ArrayList<>(przedmioty);

        Collections.sort(sortedItems, new Comparator<Przedmiot>() {
            @Override
            public int compare(Przedmiot p1, Przedmiot p2) {
                double ratio1 = p1.getStosunekWartosciDoWagi();
                double ratio2 = p2.getStosunekWartosciDoWagi();
                return Double.compare(ratio2, ratio1);
            }
        });

        List<Integer> selectedItems = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        int totalWeight = 0;
        int totalValue = 0;

        for (Przedmiot item : sortedItems) {
            if (totalWeight >= capacity) {
                break;
            }

            int maxPossible = (capacity - totalWeight) / item.getWaga();
            if (maxPossible > 0) {
                selectedItems.add(item.getNumer());
                quantities.add(maxPossible);
                totalWeight += maxPossible * item.getWaga();
                totalValue += maxPossible * item.getWartosc();
            }
        }

        return new Result(selectedItems, quantities, totalValue, totalWeight);
    }


    public List<Przedmiot> getPrzedmioty() {
        return new ArrayList<>(przedmioty);
    }





    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Przedmiot p : przedmioty) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}