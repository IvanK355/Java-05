package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Account;
import service.UnknownAccountException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonAccountDao implements Dao<Account> {

    private static final String filePath = "./Task/src/main/resources/accounts.json";
    private static final Type itemsMapType = new TypeToken<HashMap<Integer, Account>>() {
    }.getType();
    private ArrayList<Account> accounts = new ArrayList<>();

    @Override
    public void create() throws IOException {

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

    @Override
    public Account read(int id) throws IOException, NullPointerException, UnknownAccountException {

            HashMap<Integer, Account> balanceHashMap = readJson();
            String holder = balanceHashMap.get(id).getHolder();
            int amount = balanceHashMap.get(id).getAccountAmount();
            return new Account(id, holder, amount);

    }

    @Override
    public Account update(int id, int amount) throws IOException {
        HashMap<Integer, Account> balanceHashMap = readJson();
        String holder = balanceHashMap.get(id).getHolder();
        Account account = new Account(id, holder, amount);
        balanceHashMap.put(id, account);
        writeJson(balanceHashMap);
        return account;
    }

    @Override
    public Account delete(int id, int amount) {
        return null;
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
