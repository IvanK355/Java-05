package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Account;
import service.NotEnoughMoneyException;
import service.UnknownAccountException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonAccountDao implements Dao<Account> {

    public static final String filePath = "./Task/src/main/resources/accounts.json";
    public static final Type itemsMapType = new TypeToken<HashMap<Integer, Account>>() {
    }.getType();
    private final ArrayList<Account> accounts = new ArrayList<>();

    @Override

    public Account balance(int id) throws IOException, UnknownAccountException {

        HashMap<Integer, Account> balanceHashMap = readJson();
        try {
            String holder = balanceHashMap.get(id).getHolder();
            int amount = balanceHashMap.get(id).getAccountAmount();
            return new Account(id, holder, amount);
        } catch (Exception e){
            throw new UnknownAccountException("Исключение создано в JSON метод balance");
        }
    }

    @Override

    public Account deposit(int id, int amount) throws IOException, UnknownAccountException {
        balance(id);
        HashMap<Integer, Account> balanceHashMap = readJson();
        String holder = balanceHashMap.get(id).getHolder();
        int EndAmount = balanceHashMap.get(id).getAccountAmount() + amount;
        Account account = new Account(id, holder, EndAmount);
        balanceHashMap.put(id, account);
        writeJson(balanceHashMap);
        return account;
    }

    @Override
    public Account withdraw(int id, int amount) throws IOException, NotEnoughMoneyException, UnknownAccountException {
        balance(id);
        HashMap<Integer, Account> balanceHashMap = readJson();
        String holder = balanceHashMap.get(id).getHolder();
        int EndAmount = balanceHashMap.get(id).getAccountAmount() - amount;
        if (EndAmount < 0) {
            throw new NotEnoughMoneyException("Исключение создано в JSON метод withdraw");
        }
        Account account = new Account(id, holder, EndAmount);
        balanceHashMap.put(id, account);
        writeJson(balanceHashMap);
        return account;
    }

    @Override
    public void transfer(int id1, int id2, int amount) throws IOException, NotEnoughMoneyException, UnknownAccountException {
        balance(id1);
        balance(id2);
        withdraw(id1, amount);
        deposit(id2, amount);
    }

    @Override
    public void createNewTable() throws IOException {

        Gson gson = new Gson();
        FileWriter fw = new FileWriter(filePath);
        HashMap<Integer, Account> mapItems = new HashMap<>();
        for (int i = 0; i < 11; i++) {
            accounts.add(new Account(i, "Holder" + i, 500));
            mapItems.put(i, accounts.get(i));
        }
        gson.toJson(mapItems, fw);
        fw.close();
    }

    private void writeJson(HashMap<Integer, Account> mapItems) throws IOException {

        Gson gson = new Gson();
        FileWriter fw = new FileWriter(filePath);
        gson.toJson(mapItems, fw);
        fw.close();
    }

    private HashMap<Integer, Account> readJson() throws IOException {

        Gson gson = new Gson();
        FileReader fr = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fr);


        HashMap<Integer, Account> mapItemsDes = gson.fromJson(bufferedReader, itemsMapType);
        fr.close();

        return mapItemsDes;
    }
}
