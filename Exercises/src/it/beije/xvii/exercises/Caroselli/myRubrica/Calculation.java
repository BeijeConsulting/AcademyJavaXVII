package it.beije.xvii.exercises.Caroselli.myRubrica;

import java.util.Objects;

public class Calculation {
    private double buyTotal;
    private double sellTotal;
    private double amount;
    private double quantity;
    private String nameOfAction;

    private String operation;


    public Calculation() {
    }

    public double getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(double buyTotal) {
        this.buyTotal = buyTotal;
    }

    public double getSellTotal() {
        return sellTotal;
    }

    public void setSellTotal(double sellTotal) {
        this.sellTotal = sellTotal;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getNameOfAction() {
        return nameOfAction;
    }

    public void setNameOfAction(String nameOfAction) {
        this.nameOfAction = nameOfAction;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Calculation{" +
                "buyTotal=" + buyTotal +
                ", sellTotal=" + sellTotal +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", nameOfAction='" + nameOfAction + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculation that = (Calculation) o;
        return Double.compare(that.buyTotal, buyTotal) == 0 && Double.compare(that.sellTotal, sellTotal) == 0 && Double.compare(that.amount, amount) == 0 && Double.compare(that.quantity, quantity) == 0 && Objects.equals(nameOfAction, that.nameOfAction) && Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyTotal, sellTotal, amount, quantity, nameOfAction, operation);
    }
}
