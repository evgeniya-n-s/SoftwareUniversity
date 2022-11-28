package _05_BillsPaymentSystem.entities;

import javax.persistence.*;

@Entity
@Table(name = "_05_bank_account")
public class BankAccount extends BillingDetail {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String swiftCode;

    public BankAccount() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
