package lotto.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import lotto.model.LottoNumber;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
	private final List<LottoNumber> cachedLottoNumbers;

	public RandomLottoNumberGenerator() {
		this.cachedLottoNumbers = generateCachedLottoNumbers();
	}

	@Override
	public List<LottoNumber> generate() {
		List<LottoNumber> numbers = new ArrayList<>(cachedLottoNumbers);
		Collections.shuffle(numbers);

		return numbers.stream()
			.limit(LottoRules.LOTTO_SIZE)
			.sorted()
			.toList();
	}

	private List<LottoNumber> generateCachedLottoNumbers() {
		return IntStream.rangeClosed(LottoRules.MIN_LOTTO_NUMBER, LottoRules.MAX_LOTTO_NUMBER)
			.mapToObj(LottoNumber::new)
			.toList();
	}
}
