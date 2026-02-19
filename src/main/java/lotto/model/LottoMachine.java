package lotto.model;

import lotto.model.generator.LottoNumberGenerator;

import java.util.List;
import java.util.stream.Stream;

public class LottoMachine {
	private final LottoPurchaseInformation lottoPurchaseInformation;
	private final Lottos lottos;

	public LottoMachine(
		LottoPurchaseInformation lottoPurchaseInformation,
		LottoNumberGenerator generator,
		List<Lotto> manualLottos
	) {
		this.lottoPurchaseInformation = lottoPurchaseInformation;
		List<Lotto> autoLottos = issueAutoLottos(generator);
		this.lottos = new Lottos(Stream.concat(manualLottos.stream(), autoLottos.stream()).toList());
	}

	private List<Lotto> issueAutoLottos(LottoNumberGenerator generator) {
		return Stream.generate(() -> new Lotto(generator.generate()))
			.limit(lottoPurchaseInformation.autoLottoCount())
			.toList();
	}

	public Lottos getLottos() {
		return lottos;
	}

	public LottoStatistics calculateResult(WinningLotto winningLotto) {
		return lottos.calculateStatistics(winningLotto, lottoPurchaseInformation);
	}
}
