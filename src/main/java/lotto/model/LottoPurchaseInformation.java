package lotto.model;

import java.math.BigDecimal;

public final class LottoPurchaseInformation {
	private static final String INSUFFICIENT_ERROR = "금액이 부족합니다.";
	private static final int MIN_AUTO_LOTTO_COUNT = 0;

	private final PurchaseAmount purchaseAmount;
	private final ManualLottoCount manualLottoCount;

	public LottoPurchaseInformation(PurchaseAmount purchaseAmount, ManualLottoCount manualLottoCount) {
		if (purchaseAmount.getAutoLottoCount(manualLottoCount) < MIN_AUTO_LOTTO_COUNT) {
			throw new IllegalArgumentException(INSUFFICIENT_ERROR);
		}
		this.purchaseAmount = purchaseAmount;
		this.manualLottoCount = manualLottoCount;
	}

	public int manualLottoCount() {
		return manualLottoCount.value();
	}

	public int autoLottoCount() {
		return purchaseAmount.getAutoLottoCount(manualLottoCount);
	}

	public BigDecimal profitRate(BigDecimal totalPrize) {
		return purchaseAmount.calculateProfitRate(totalPrize);
	}
}
