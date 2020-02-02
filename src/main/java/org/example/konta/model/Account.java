package org.example.konta.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {
    @XmlAttribute
    private String iban;
    private String name;
    private String currency;
    private String balance;
    private String closingDate;

    public Account() {

    }

    public Account(String iban, String name, String currency, String balance, String closingDate) {
        this.iban = iban;
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.closingDate = closingDate;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "iban='" + iban + '\'' +
                ", name='" + name + '\'' +
                ", currency=" + currency +
                ", balance='" + balance + '\'' +
                ", closingDate='" + closingDate + '\'' +
                '}';
    }
}
