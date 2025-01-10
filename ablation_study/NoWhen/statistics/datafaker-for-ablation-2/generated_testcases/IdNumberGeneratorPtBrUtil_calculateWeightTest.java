
package net.datafaker.idnumbers.pt.br;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.net.URL;
import java.nio.file.Path;

public class IdNumberGeneratorPtBrUtil_calculateWeightTest {

    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders() {
            @Override
            public String toString() {
                return "MockFaker";
            }

            @Override
            public void addUrl(Locale locale, URL url) {
                // Dummy implementation to satisfy the abstract method
            }

            @Override
            public void addPath(Locale locale, Path path) {
                // Dummy implementation to satisfy the abstract method
            }
        };
    }

    @Test
    public void testCalculateWeightCNPJ() {
        String cnpj = IdNumberGeneratorPtBrUtil.cnpj(faker, false, true, false);
        int weight = 9;
        int start = 4;
        int end = 12;
        int expectedSum = calculateExpectedSum(cnpj, weight, start, end);
        assertEquals(expectedSum, IdNumberGeneratorPtBrUtil.calculateWeight(cnpj, weight, start, end));
    }

    @Test
    public void testCalculateWeightCPF() {
        String cpf = IdNumberGeneratorPtBrUtil.cpf(faker, false, true);
        int weight = 10;
        int start = 0;
        int end = 9;
        int expectedSum = calculateExpectedSum(cpf, weight, start, end);
        assertEquals(expectedSum, IdNumberGeneratorPtBrUtil.calculateWeight(cpf, weight, start, end));
    }

    private int calculateExpectedSum(String num, int weight, int start, int end) {
        int sum = 0;
        int weightAux = weight;
        for (int index = start; index < end; index++) {
            sum += (num.charAt(index) - '0') * weightAux--;
        }
        return sum;
    }
}
