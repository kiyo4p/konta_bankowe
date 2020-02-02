package org.example.konta;

import org.example.konta.model.Account;
import org.example.konta.model.Accounts;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Manager {
    public Accounts manage(Accounts accounts) {
        checkCurrency(accounts.getAccounts());
        checkBalance(accounts.getAccounts());
        checkClosingDate(accounts.getAccounts());
        checkIBAN(accounts.getAccounts());

        Comparator<Account> accountComparator = sortByName();
        List<Account> accountsToSort = accounts.getAccounts();
        Collections.sort(accountsToSort, accountComparator);
        accounts.setAccounts(accountsToSort);
        return accounts;
    }

    private void checkCurrency(List<Account> listOfAccounts) {
        listOfAccounts.removeIf(account -> !account.getCurrency().equals("PLN"));
    }

    private void checkBalance(List<Account> listOfAccounts) {
        listOfAccounts.removeIf(account -> Double.parseDouble(account.getBalance()) < 0);
    }

    private void checkClosingDate(List<Account> listOfAccounts) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());
        listOfAccounts.removeIf(account -> currentDate.compareTo(account.getClosingDate()) > 0);
    }

    private void checkIBAN(List<Account> listOfAccounts) {
        listOfAccounts.removeIf(account -> !Pattern.matches("^PL[0-9]+", account.getIban()));
    }

    private Comparator<Account> sortByName() {
        return Comparator.comparing(Account::getName);
    }
}
