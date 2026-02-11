package lotto.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

	@Override
	public List<Integer> generate() {
		List<Integer> candidates = createCandidates();
		Collections.shuffle(candidates);
		return candidates.stream()
			.limit(LottoRules.LOTTO_SIZE)
			.sorted()
			.toList();
	}

	private List<Integer> createCandidates() {
		return IntStream.rangeClosed(LottoRules.MIN_LOTTO_NUMBER, LottoRules.MAX_LOTTO_NUMBER)
			.boxed()
			.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}
}
