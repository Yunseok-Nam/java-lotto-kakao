package lotto.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

public class PurchaseAmount {
	private static final String MIN_PURCHASE_AMOUNT_ERROR_MESSAGE = "구입금액은 1,000원 이상이어야 합니다.";
	private static final String PURCHASE_UNIT_ERROR_MESSAGE = "입금액은 1,000원 단위여야 합니다.";
	public static final int PURCHASE_UNIT = 1000;

	private final int amount;

	public PurchaseAmount(int amount) {
		validate(amount);
		this.amount = amount;
	}

	public BigDecimal calculateProfitRate(BigDecimal totalPrize) {
		return totalPrize.divide(BigDecimal.valueOf(amount), MathContext.DECIMAL64);
	}

	private void validate(int amount) {
		if (amount < PURCHASE_UNIT) {
			throw new IllegalArgumentException(MIN_PURCHASE_AMOUNT_ERROR_MESSAGE);
		}

		if (amount % PURCHASE_UNIT != 0) {
			throw new IllegalArgumentException(PURCHASE_UNIT_ERROR_MESSAGE);
		}
	}

	public int getAutoLottoCount(ManualLottoCount manualLottoCount) {
		return manualLottoCount.calculateAutoLottoCount(amount / PURCHASE_UNIT);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		PurchaseAmount that = (PurchaseAmount)o;
		return amount == that.amount;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(amount);
	}
}
