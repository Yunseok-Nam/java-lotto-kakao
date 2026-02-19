package lotto.model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
	private final Map<LottoResult, Integer> counts;
	private final LottoPurchaseInformation lottoPurchaseInformation;

	private LottoStatistics(Map<LottoResult, Integer> counts, LottoPurchaseInformation lottoPurchaseInformation) {
		this.counts = counts;
		this.lottoPurchaseInformation = lottoPurchaseInformation;
	}

	public static LottoStatistics from(List<LottoResult> results, LottoPurchaseInformation lottoPurchaseInformation) {
		Map<LottoResult, Integer> counts = initializeCounts();
		results.forEach(result -> counts.put(result, counts.get(result) + 1));
		return new LottoStatistics(counts, lottoPurchaseInformation);
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

	public BigDecimal profitRate() {
		BigDecimal totalPrize = BigDecimal.valueOf(calculateTotalPrize());
		return lottoPurchaseInformation.profitRate(totalPrize);
	}

	public long calculateTotalPrize() {
		return counts.entrySet().stream()
			.mapToLong(entry -> (long)entry.getKey().getPrize() * entry.getValue())
			.sum();
	}
}
