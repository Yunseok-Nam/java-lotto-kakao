package lotto.model;

import lotto.model.generator.LottoNumberGenerator;

import java.util.List;
import java.util.stream.Stream;

public class LottoMachine {
	private final PurchaseAmount purchaseAmount;
	private final Lottos lottos;

	public LottoMachine(
		PurchaseAmount purchaseAmount,
		LottoNumberGenerator generator,
		List<List<Integer>> manualNumbers
	) {
		this.purchaseAmount = purchaseAmount;
		List<Lotto> manualLottos = issueManualLottos(manualNumbers);
		List<Lotto> autoLottos = issueAutoLottos(generator);
		this.lottos = new Lottos(Stream.concat(manualLottos.stream(), autoLottos.stream()).toList());
	}

	private List<Lotto> issueAutoLottos(LottoNumberGenerator generator) {
		return Stream.generate(() -> new Lotto(generator.generate()))
			.limit(purchaseAmount.getAutoLottoCount())
			.toList();
	}

	private List<Lotto> issueManualLottos(List<List<Integer>> manualNumbers) {
		return manualNumbers.stream()
			.map(Lotto::from)
			.toList();
	}

	public Lottos getLottos() {
		return lottos;
	}

	public LottoStatistics calculateResult(WinningLotto winningLotto) {
		return lottos.calculateStatistics(winningLotto, purchaseAmount);
	}
}
