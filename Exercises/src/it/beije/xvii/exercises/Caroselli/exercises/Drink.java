package it.beije.xvii.exercises.Caroselli.exercises;

import java.util.Objects;

public class Drink {

    //content rappresenta i ml, evapPerday e threshold invece sono %
    private double content;
    private double evapPerDay;

    private double threshold;

    public Drink() {
    }

    public Drink(double content, double evapPerDay, double threshold) {
        this.content = content;
        this.evapPerDay = evapPerDay;
        this.threshold = threshold;
    }

    public double getContent() {
        return content;
    }

    public void setContent(double content) {
        this.content = content;
    }

    public double getEvapPerDay() {
        return evapPerDay;
    }

    public void setEvapPerDay(double evapPerDay) {
        this.evapPerDay = evapPerDay;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Double.compare(drink.content, content) == 0 && Double.compare(drink.evapPerDay, evapPerDay) == 0 && Double.compare(drink.threshold, threshold) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, evapPerDay, threshold);
    }

    @Override
    public String toString() {
        return "Drink{" +
                "content=" + content +
                ", evapPerDay=" + evapPerDay +
                ", threshold=" + threshold +
                '}';
    }
}
