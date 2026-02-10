import lotto.controller.LottoController;
import lotto.util.RandomLottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		InputView inputView = new InputView();
		OutputView outputView = new OutputView();
		LottoController controller = new LottoController(inputView, outputView, new RandomLottoNumberGenerator());
		controller.run();
	}
}
