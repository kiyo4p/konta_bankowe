package org.example.konta;

import org.example.konta.model.Account;
import org.example.konta.model.Accounts;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ManagerTest {
    private List<Account> accountsList;
    private Manager manager = new Manager();
    private Accounts accounts = new Accounts();

    @Before
    public void setup() {
        accountsList = new ArrayList<>(
                List.of(new Account("PL61109010140000071219812875", "first", "PLN", "15.00", "2034-01-01"))
        );
    }

    @Test
    public void currencyRejectionTest() {
        Account wrongCurrency = new Account("PL61109010140000071219812875", "test", "PL", "15.00", "2034-01-01");
        accountsList.add(wrongCurrency);
        accounts.setAccounts(accountsList);

        assertEquals(1, manager.manage(accounts).getAccounts().size());
    }

    @Test
    public void negativeBalanceTest() {
        Account negativeBalance = new Account("PL61109010140000071219812875", "test", "PLN", "-5.00", "2034-01-01");
        Account zeroBalance = new Account("PL61109010140000071219812875", "test", "PLN", "0", "2034-01-01");
        accountsList.add(negativeBalance);
        accountsList.add(zeroBalance);
        accounts.setAccounts(accountsList);

        assertEquals(2, manager.manage(accounts).getAccounts().size());
    }

    @Test
    public void closingDateTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());

        Account earlierDate = new Account("PL61109010140000071219812875", "test", "PLN", "15.00", "2020-01-01");
        Account theSameDate = new Account("PL61109010140000071219812875", "test", "PLN", "15.00", currentDate);
        accountsList.add(earlierDate);
        accountsList.add(theSameDate);
        accounts.setAccounts(accountsList);

        assertEquals(2, manager.manage(accounts).getAccounts().size());
    }

    @Test
    public void incorrectIbanTest() {
        Account wrongIban = new Account("PLN61109010140000071219812875", "test", "PLN", "15.00", "2034-01-01");
        accountsList.add(wrongIban);
        accounts.setAccounts(accountsList);

        assertEquals(1, manager.manage(accounts).getAccounts().size());
    }

    @Test
    public void sortingTest() {
        Account testAccount = new Account("PL61109010140000071219812875", "test", "PLN", "15.00", "2034-01-01");
        Account aaaAccount = new Account("PL61109010140000071219812875", "aaa", "PLN", "15.00", "2034-01-01");
        accountsList.add(testAccount);
        accountsList.add(aaaAccount);
        accounts.setAccounts(accountsList);

        List<Account> actual = manager.manage(accounts).getAccounts();

        assertEquals("aaa", actual.get(0).getName());
        assertEquals("test", actual.get(2).getName());
    }
}
