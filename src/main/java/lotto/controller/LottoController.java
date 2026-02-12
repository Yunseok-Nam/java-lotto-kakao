package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoNumber;
import lotto.model.PurchaseAmount;
import lotto.model.WinningLotto;
import lotto.model.generator.LottoNumberGenerator;
import lotto.model.generator.RandomLottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;

	public LottoController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		PurchaseAmount purchaseAmount = readUntilValid(
			() -> new PurchaseAmount(inputView.readPurchaseAmount(), inputView.readManualLottoCount())
		);

		LottoNumberGenerator lottoNumberGenerator = new RandomLottoNumberGenerator(
			LottoNumber.MIN_LOTTO_NUMBER,
			LottoNumber.MAX_LOTTO_NUMBER,
			Lotto.LOTTO_SIZE
		);

		LottoMachine lottoMachine = readUntilValid(
			() -> new LottoMachine(
				purchaseAmount,
				lottoNumberGenerator,
				readManualNumbers(purchaseAmount.getManualLottoCount())
			)
		);
		outputView.printPurchasedLottos(
			purchaseAmount.getManualLottoCount(),
			purchaseAmount.getAutoLottoCount(),
			lottoMachine.getLottos().values()
		);
		WinningLotto winningLotto = readValidWinningLotto();
		outputView.printStatistics(lottoMachine.calculateResult(winningLotto));
	}

	private WinningLotto readValidWinningLotto() {
		Lotto winningLotto = readUntilValid(() -> Lotto.from(inputView.readWinningNumbers()));
		LottoNumber bonusNumber = readUntilValid(() -> new LottoNumber(inputView.readBonusNumber()));
		return new WinningLotto(winningLotto, bonusNumber);
	}

	private List<List<Integer>> readManualNumbers(int manualLottoCount) {
		if (manualLottoCount == 0) {
			return List.of();
		}
		return inputView.readManualLottoNumbers(manualLottoCount);
	}

	private <T> T readUntilValid(Supplier<T> reader) {
		Optional<T> value = tryRead(reader);
		while (value.isEmpty()) {
			value = tryRead(reader);
		}
		return value.orElseThrow();
	}

	private <T> Optional<T> tryRead(Supplier<T> reader) {
		try {
			return Optional.of(reader.get());
		} catch (IllegalArgumentException exception) {
			outputView.printError(exception.getMessage());
			return Optional.empty();
		}
	}
}
