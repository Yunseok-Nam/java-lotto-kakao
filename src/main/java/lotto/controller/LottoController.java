package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoNumber;
import lotto.model.PurchaseAmount;
import lotto.model.WinningLotto;
import lotto.util.LottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Optional;
import java.util.function.Supplier;

public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;
	private final LottoNumberGenerator lottoNumberGenerator;

	public LottoController(InputView inputView, OutputView outputView, LottoNumberGenerator lottoNumberGenerator) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.lottoNumberGenerator = lottoNumberGenerator;
	}

	public void run() {
		PurchaseAmount purchaseAmount = new PurchaseAmount(readUntilValid(inputView::readPurchaseAmount));
		LottoMachine lottoMachine = new LottoMachine(purchaseAmount, lottoNumberGenerator);
		outputView.printPurchasedLottos(lottoMachine.getLottos().values());
		WinningLotto winningLotto = readValidWinningLotto();
		outputView.printStatistics(lottoMachine.calculateResult(winningLotto));
	}

	private WinningLotto readValidWinningLotto() {
		Lotto winningNumbers = Lotto.from(readUntilValid(inputView::readWinningNumbers));
		LottoNumber bonusNumber = new LottoNumber(readUntilValid(inputView::readBonusNumber));
		return new WinningLotto(winningNumbers, bonusNumber);
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
