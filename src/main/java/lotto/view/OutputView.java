package lotto.view;

import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.LottoResult;
import lotto.model.LottoStatistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
	private static final String PURCHASED_LOTTOS_WITH_MANUAL_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
	private static final String STATISTICS_TITLE = "당첨 통계";
	private static final String STATISTICS_SEPARATOR = "---------";
	private static final String RESULT_LINE_FORMAT = "%d개 일치 (%d원)- %d개";
	private static final String SECOND_RESULT_LINE_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
	private static final String PROFIT_RATE_MESSAGE_FORMAT = "총 수익률은 %.2f입니다.";
	private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

	public void printPurchasedLottos(int manualLottoCount, int autoLottoCount, List<Lotto> lottos) {
		System.out.printf((PURCHASED_LOTTOS_WITH_MANUAL_MESSAGE) + "%n", manualLottoCount, autoLottoCount);
		lottos.stream()
			.map(this::formatLotto)
			.forEach(System.out::println);
	}

	public void printStatistics(LottoStatistics lottoStatistics) {
		System.out.println();
		System.out.println(STATISTICS_TITLE);
		System.out.println(STATISTICS_SEPARATOR);
		LottoResult.winningResultsByPrizeAscending().stream()
			.map(result -> formatResultLine(result, lottoStatistics.countOf(result)))
			.forEach(System.out::println);
		System.out.printf((PROFIT_RATE_MESSAGE_FORMAT) + "%n", lottoStatistics.profitRate());
	}

	private String formatResultLine(LottoResult lottoResult, long count) {
		if (lottoResult.includesBonusMatch()) {
			return SECOND_RESULT_LINE_FORMAT.formatted(lottoResult.getMatchCount(), lottoResult.getPrize(), count);
		}
		return RESULT_LINE_FORMAT.formatted(lottoResult.getMatchCount(), lottoResult.getPrize(), count);
	}

	private String formatLotto(Lotto lotto) {
		String numbers = lotto.numbers().stream()
			.map(LottoNumber::value)
			.map(String::valueOf)
			.collect(Collectors.joining(", "));
		return "[" + numbers + "]";
	}

	public void printError(String message) {
		System.out.println(ERROR_MESSAGE_PREFIX + message);
	}
}
