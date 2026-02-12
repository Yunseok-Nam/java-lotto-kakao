package lotto.model.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import lotto.model.LottoNumber;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
	private final int minNumber;
	private final int maxNumber;
	private final int lottoSize;
	private final List<LottoNumber> cachedLottoNumbers;

	public RandomLottoNumberGenerator(int minNumber, int maxNumber, int lottoSize) {
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		this.lottoSize = lottoSize;
		this.cachedLottoNumbers = generateCachedLottoNumbers();
	}

	@Override
	public List<LottoNumber> generate() {
		List<LottoNumber> numbers = new ArrayList<>(cachedLottoNumbers);
		Collections.shuffle(numbers);

		return numbers.stream()
			.limit(lottoSize)
			.sorted()
			.toList();
	}

	private List<LottoNumber> generateCachedLottoNumbers() {
		return IntStream.rangeClosed(minNumber, maxNumber)
			.mapToObj(LottoNumber::new)
			.toList();
	}
}
