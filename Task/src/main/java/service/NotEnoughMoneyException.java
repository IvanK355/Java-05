package service;

public class NotEnoughMoneyException extends Throwable {

    public NotEnoughMoneyException(String msg) {

        super(msg);
    }
}
