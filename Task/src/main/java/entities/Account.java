package entities;

import javax.persistence.*;

@Entity
@Table(name = "accounts")

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String holder;
    private int accountAmount;


    public Account(int id, String holder, int amountOperation) {
        this.id = id;
        this.holder = holder;
        this.accountAmount = amountOperation;
    }

    public Account(String holder, int accountAmount) {
        this.holder = holder;
        this.accountAmount = accountAmount;
    }
    public Account(){

    }

    public String toString() {
        return id + " : " + holder + " : "
                + " Сумма на счете: " + accountAmount;
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

    public int getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(int accountAmount) {
        this.accountAmount = accountAmount;
    }
}
