package lotto.util;

import java.util.List;
import java.util.stream.IntStream;

import lotto.model.LottoNumber;

public class FixedLottoNumberGenerator implements LottoNumberGenerator {

	private final List<LottoNumber> numbers;

	public FixedLottoNumberGenerator(int start, int end) {
		this.numbers = IntStream.rangeClosed(start, end)
			.mapToObj(LottoNumber::new)
			.toList();
	}

	@Override
	public List<LottoNumber> generate() {
		return numbers;
	}
}
