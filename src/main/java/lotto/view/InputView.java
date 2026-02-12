package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputView {
	private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
	private static final String MANUAL_LOTTO_COUNT_PROMPT = "수동으로 구매할 로또 수를 입력해 주세요.";
	private static final String MANUAL_LOTTO_NUMBERS_PROMPT = "수동으로 구매할 번호를 입력해 주세요.";
	private static final String WINNING_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String BONUS_NUMBER_PROMPT = "보너스 볼을 입력해 주세요.";
	private static final String COMMA_DELIMITER = ",";
	private static final String NOT_NUMBER_ERROR_MESSAGE = "숫자만 입력해 주세요.";

	private final Scanner scanner = new Scanner(System.in);

	public int readPurchaseAmount() {
		System.out.println(PURCHASE_AMOUNT_PROMPT);
		return parseNumber(scanner.nextLine());
	}

	public int readManualLottoCount() {
		System.out.println();
		System.out.println(MANUAL_LOTTO_COUNT_PROMPT);
		return parseNumber(scanner.nextLine());
	}

	public List<List<Integer>> readManualLottoNumbers(int manualLottoCount) {
		System.out.println();
		System.out.println(MANUAL_LOTTO_NUMBERS_PROMPT);

		List<List<Integer>> manualLottoNumbers = new ArrayList<>();
		for (int i = 0; i < manualLottoCount; i++) {
			manualLottoNumbers.add(parseCommaSeparatedNumbers(scanner.nextLine()));
		}
		return manualLottoNumbers;
	}

	public List<Integer> readWinningNumbers() {
		System.out.println();
		System.out.println(WINNING_NUMBERS_PROMPT);
		return parseCommaSeparatedNumbers(scanner.nextLine());
	}

	public int readBonusNumber() {
		System.out.println(BONUS_NUMBER_PROMPT);
		return parseNumber(scanner.nextLine());
	}

	private int parseNumber(String value) {
		try {
			return Integer.parseInt(value.trim());
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
		}
	}

	private List<Integer> parseCommaSeparatedNumbers(String value) {
		StringTokenizer tokenizer = new StringTokenizer(value, COMMA_DELIMITER);
		List<Integer> numbers = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			numbers.add(parseNumber(tokenizer.nextToken()));
		}
		return numbers;
	}
}
