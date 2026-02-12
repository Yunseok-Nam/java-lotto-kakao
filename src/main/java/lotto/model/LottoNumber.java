package lotto.model;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final String INVALID_RANGE_ERROR_MESSAGE = "로또 번호는 1~45 범위의 숫자입니다.";
	public static final int MIN_LOTTO_NUMBER = 1;
	public static final int MAX_LOTTO_NUMBER = 45;

	private final int number;

	public LottoNumber(int number) {
		validate(number);
		this.number = number;
	}

	private void validate(int number) {
		if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException(INVALID_RANGE_ERROR_MESSAGE);
		}
	}

	@Override
	public int compareTo(LottoNumber other) {
		return Integer.compare(this.number, other.number);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber that = (LottoNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(number);
	}

	public int value() {
		return number;
	}
}
