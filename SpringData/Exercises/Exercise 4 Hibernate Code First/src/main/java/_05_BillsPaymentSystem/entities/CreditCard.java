package _05_BillsPaymentSystem.entities;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "_05_credit_cards")
public class CreditCard extends BillingDetail {

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "expiration_month", nullable = false)
    private Month expirationMonth;

    @Column(name = "expiration_year", nullable = false)
    private Year expirationYear;

    public CreditCard() {
    }

    public Month getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Month expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Year getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Year expirationYear) {
        this.expirationYear = expirationYear;
    }
}
