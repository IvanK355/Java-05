package entities;

public class Account {

    private int id;
    private String holder;
    private int amountOperation;


    public Account(int id, String holder, int amountOperation) {
        this.id = id;
        this.holder = holder;
        this.amountOperation = amountOperation;
    }


    public String toString() {
        return id + " : " + holder + " : "
                + " Сумма на счете: " + amountOperation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public int getAmountOperation() {
        return amountOperation;
    }

    public void setAmountOperation(int amountOperation) {
        this.amountOperation = amountOperation;
    }
}
