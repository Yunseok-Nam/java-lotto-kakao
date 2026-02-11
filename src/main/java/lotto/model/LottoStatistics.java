package lotto.model;

import lotto.util.LottoRules;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
	private final Map<LottoResult, Integer> counts;
	private final PurchaseAmount purchaseAmount;

	private LottoStatistics(Map<LottoResult, Integer> counts, PurchaseAmount purchaseAmount) {
		this.counts = counts;
		this.purchaseAmount = purchaseAmount;
	}

	public static LottoStatistics from(List<LottoResult> results, PurchaseAmount purchaseAmount) {
		Map<LottoResult, Integer> counts = initializeCounts();
		results.forEach(result -> counts.put(result, counts.get(result) + 1));
		return new LottoStatistics(counts, purchaseAmount);
	}

	private static Map<LottoResult, Integer> initializeCounts() {
		Map<LottoResult, Integer> counts = new EnumMap<>(LottoResult.class);
		for (LottoResult result : LottoResult.values()) {
			counts.put(result, 0);
		}
		return counts;
	}

	public int countOf(LottoResult lottoResult) {
		return counts.get(lottoResult);
	}

	public int calculateTotalPrize() {
		return counts.entrySet().stream()
			.mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
			.sum();
	}

	public double profitRate() {
		int purchaseAmountValue = purchaseAmount.getLottoCount() * LottoRules.PURCHASE_UNIT;
		return (double)calculateTotalPrize() / purchaseAmountValue;
	}
}
