package org.example.konta.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Accounts {
    @XmlElement(name = "account")
    private List<Account> allAccounts;

    public List<Account> getAccounts() {
        return allAccounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.allAccounts = accounts;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "accounts=" + allAccounts +
                '}';
    }
}
