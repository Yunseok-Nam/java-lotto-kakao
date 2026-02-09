package lotto.model;

import lotto.LottoRules;

import java.util.Objects;

public class PurchaseAmount {
    private final int amount;

    public PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount % LottoRules.PURCHASE_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 1,000원 단위여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseAmount that = (PurchaseAmount) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
