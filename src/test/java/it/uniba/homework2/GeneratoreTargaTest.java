package it.uniba.homework2;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.Statistics;
import net.jqwik.api.statistics.StatisticsReport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GeneratoreTargaTest {

    @Property
    void invalidPrefix(@ForAll("InvalidPrefix") String prefisso) {
        assertThatThrownBy(() -> {
            GeneratoreTarga.generaTargaConPrefisso(prefisso, 3);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Provide
    private Arbitrary<String> InvalidPrefix() {
        return Arbitraries.oneOf(
                Arbitraries.strings().ofMinLength(1).ofMaxLength(2),
                Arbitraries.strings().ofMinLength(4));
    }

    @Property
    void invalidLengthNum(@ForAll("InvalidLengthNum") int lunghezzaNumero) {
        assertThatThrownBy(() -> {
            GeneratoreTarga.generaTargaConPrefisso("ABC", lunghezzaNumero);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Provide
    private Arbitrary<Integer> InvalidLengthNum() {
        return Arbitraries.oneOf(
                Arbitraries.integers().lessOrEqual(0));
    }

    @Property
    @StatisticsReport(format = Histogram.class)
    void validPlatesPBT(@ForAll("validPrefix") String prefisso,
                        @ForAll @IntRange(min = 1, max = 5) int lunghezzaNumero) {

            String generatedPlate = GeneratoreTarga.generaTargaConPrefisso(prefisso, lunghezzaNumero);

            int plateLength = prefisso.length() + lunghezzaNumero;

            assertThat(generatedPlate.length()).isEqualTo(plateLength);

            int lunghezzaTarga = generatedPlate.length();

            Statistics.collect(lunghezzaTarga);
    }

    @Provide
    private Arbitrary<String> validPrefix() {
        return Arbitraries.strings().withCharRange('A', 'Z').ofLength(3);
    }
}

