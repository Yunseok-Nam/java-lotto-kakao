package lotto.model;

import java.util.Objects;

public class PurchaseAmount {
	private static final String MIN_PURCHASE_AMOUNT_ERROR_MESSAGE = "구입금액은 1,000원 이상이어야 합니다.";
	private static final String PURCHASE_UNIT_ERROR_MESSAGE = "입금액은 1,000원 단위여야 합니다.";
	public static final int PURCHASE_UNIT = 1000;

	private final int amount;
	private final int manualLottoCount;

	public PurchaseAmount(int amount, int manualLottoCount) {
		validate(amount);
		if (manualLottoCount * PURCHASE_UNIT > amount) {
			throw new IllegalArgumentException("금액이 부족합니다.");
		}
		this.amount = amount;
		this.manualLottoCount = manualLottoCount;
	}

	public double calculateProfitRate(int totalPrize) {
		return (double)totalPrize / amount;
	}

	private void validate(int amount) {
		if (amount < PURCHASE_UNIT) {
			throw new IllegalArgumentException(MIN_PURCHASE_AMOUNT_ERROR_MESSAGE);
		}

		if (amount % PURCHASE_UNIT != 0) {
			throw new IllegalArgumentException(PURCHASE_UNIT_ERROR_MESSAGE);
		}
	}

	public int getAutoLottoCount() {
		return (amount / PURCHASE_UNIT) - manualLottoCount;
	}

	public int getManualLottoCount() {
		return manualLottoCount;
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
