
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BulgarianIdNumber_generateInvalidTest {
    private BulgarianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new BulgarianIdNumber();
        faker = new BaseProviders() {
            @Override
            public BaseProviders address() {
                return null;
            }

            @Override
            public BaseProviders ancient() {
                return null;
            }

            @Override
            public BaseProviders animal() {
                return null;
            }

            @Override
            public BaseProviders app() {
                return null;
            }

            @Override
            public BaseProviders appliance() {
                return null;
            }

            @Override
            public BaseProviders artist() {
                return null;
            }

            @Override
            public BaseProviders australia() {
                return null;
            }

            @Override
            public BaseProviders aviation() {
                return null;
            }

            @Override
            public BaseProviders aws() {
                return null;
            }

            @Override
            public BaseProviders azure() {
                return null;
            }

            @Override
            public BaseProviders barcode() {
                return null;
            }

            @Override
            public BaseProviders bloodtype() {
                return null;
            }

            @Override
            public BaseProviders book() {
                return null;
            }

            @Override
            public BaseProviders bool() {
                return null;
            }

            @Override
            public BaseProviders brand() {
                return null;
            }

            @Override
            public BaseProviders business() {
                return null;
            }

            @Override
            public BaseProviders camera() {
                return null;
            }

            @Override
            public BaseProviders cannabis() {
                return null;
            }

            @Override
            public BaseProviders cat() {
                return null;
            }

            @Override
            public BaseProviders chiquito() {
                return null;
            }

            @Override
            public BaseProviders cnpj() {
                return null;
            }

            @Override
            public BaseProviders code() {
                return null;
            }

            @Override
            public BaseProviders coin() {
                return null;
            }

            @Override
            public BaseProviders color() {
                return null;
            }

            @Override
            public BaseProviders commerce() {
                return null;
            }

            @Override
            public BaseProviders community() {
                return null;
            }

            @Override
            public BaseProviders company() {
                return null;
            }

            @Override
            public BaseProviders compass() {
                return null;
            }

            @Override
            public BaseProviders computer() {
                return null;
            }

            @Override
            public BaseProviders construction() {
                return null;
            }

            @Override
            public BaseProviders cosmere() {
                return null;
            }

            @Override
            public BaseProviders country() {
                return null;
            }

            @Override
            public BaseProviders cpf() {
                return null;
            }

            @Override
            public BaseProviders cryptoCoin() {
                return null;
            }

            @Override
            public BaseProviders cultureSeries() {
                return null;
            }

            @Override
            public BaseProviders currency() {
                return null;
            }

            @Override
            public BaseProviders date() {
                return null;
            }

            @Override
            public BaseProviders dcComics() {
                return null;
            }

            @Override
            public BaseProviders demographic() {
                return null;
            }

            @Override
            public BaseProviders device() {
                return null;
            }

            @Override
            public BaseProviders dog() {
                return null;
            }

            @Override
            public BaseProviders domain() {
                return null;
            }

            @Override
            public BaseProviders drivingLicense() {
                return null;
            }

            @Override
            public BaseProviders drone() {
                return null;
            }

            @Override
            public BaseProviders dungeonsAndDragons() {
                return null;
            }

            @Override
            public BaseProviders educator() {
                return null;
            }

            @Override
            public BaseProviders electricalComponents() {
                return null;
            }

            @Override
            public BaseProviders emoji() {
                return null;
            }

            @Override
            public BaseProviders duration() {
                return null;
            }

            @Override
            public BaseProviders famousLastWords() {
                return null;
            }

            @Override
            public BaseProviders file() {
                return null;
            }

            @Override
            public BaseProviders finance() {
                return null;
            }

            @Override
            public BaseProviders financialTerms() {
                return null;
            }

            @Override
            public BaseProviders funnyName() {
                return null;
            }

            @Override
            public BaseProviders garmentSize() {
                return null;
            }

            @Override
            public BaseProviders gender() {
                return null;
            }

            @Override
            public BaseProviders greekPhilosopher() {
                return null;
            }

            @Override
            public BaseProviders hacker() {
                return null;
            }

            @Override
            public BaseProviders hashing() {
                return null;
            }

            @Override
            public BaseProviders hipster() {
                return null;
            }

            @Override
            public BaseProviders hobby() {
                return null;
            }

            @Override
            public BaseProviders hololive() {
                return null;
            }

            @Override
            public BaseProviders horse() {
                return null;
            }

            @Override
            public BaseProviders house() {
                return null;
            }

            @Override
            public BaseProviders idNumber() {
                return null;
            }

            @Override
            public BaseProviders image() {
                return null;
            }

            @Override
            public BaseProviders industrySegments() {
                return null;
            }

            @Override
            public BaseProviders internet() {
                return null;
            }

            @Override
            public BaseProviders job() {
                return null;
            }

            @Override
            public BaseProviders kpop() {
                return null;
            }

            @Override
            public BaseProviders languageCode() {
                return null;
            }

            @Override
            public BaseProviders largeLanguageModel() {
                return null;
            }

            @Override
            public BaseProviders locality() {
                return null;
            }

            @Override
            public BaseProviders location() {
                return null;
            }

            @Override
            public BaseProviders lorem() {
                return null;
            }

            @Override
            public BaseProviders marketing() {
                return null;
            }

            @Override
            public BaseProviders matz() {
                return null;
            }

            @Override
            public BaseProviders mbti() {
                return null;
            }

            @Override
            public BaseProviders measurement() {
                return null;
            }

            @Override
            public BaseProviders medical() {
                return null;
            }

            @Override
            public BaseProviders military() {
                return null;
            }

            @Override
            public BaseProviders money() {
                return null;
            }

            @Override
            public BaseProviders mood() {
                return null;
            }

            @Override
            public BaseProviders mountain() {
                return null;
            }

            @Override
            public BaseProviders mountaineering() {
                return null;
            }

            @Override
            public BaseProviders music() {
                return null;
            }

            @Override
            public BaseProviders name() {
                return null;
            }

            @Override
            public BaseProviders nation() {
                return null;
            }

            @Override
            public BaseProviders natoPhoneticAlphabet() {
                return null;
            }

            @Override
            public BaseProviders nigeria() {
                return null;
            }

            @Override
            public BaseProviders number() {
                return null;
            }

            @Override
            public BaseProviders olympicSport() {
                return null;
            }

            @Override
            public BaseProviders passport() {
                return null;
            }

            @Override
            public BaseProviders phoneNumber() {
                return null;
            }

            @Override
            public BaseProviders photography() {
                return null;
            }

            @Override
            public BaseProviders planet() {
                return null;
            }

            @Override
            public BaseProviders programmingLanguage() {
                return null;
            }

            @Override
            public BaseProviders relationships() {
                return null;
            }

            @Override
            public BaseProviders restaurant() {
                return null;
            }

            @Override
            public BaseProviders robin() {
                return null;
            }

            @Override
            public BaseProviders rockBand() {
                return null;
            }

            @Override
            public BaseProviders science() {
                return null;
            }

            @Override
            public BaseProviders slackEmoji() {
                return null;
            }

            @Override
            public BaseProviders shakespeare() {
                return null;
            }

            @Override
            public BaseProviders sip() {
                return null;
            }

            @Override
            public BaseProviders size() {
                return null;
            }

            @Override
            public BaseProviders space() {
                return null;
            }

            @Override
            public BaseProviders stock() {
                return null;
            }

            @Override
            public BaseProviders subscription() {
                return null;
            }

            @Override
            public BaseProviders superhero() {
                return null;
            }

            @Override
            public BaseProviders team() {
                return null;
            }

            @Override
            public BaseProviders text() {
                return null;
            }

            @Override
            public BaseProviders time() {
                return null;
            }

            @Override
            public BaseProviders timeAndDate() {
                return null;
            }

            @Override
            public BaseProviders tire() {
                return null;
            }

            @Override
            public BaseProviders transport() {
                return null;
            }

            @Override
            public BaseProviders twitter() {
                return null;
            }

            @Override
            public BaseProviders unique() {
                return null;
            }

            @Override
            public BaseProviders university() {
                return null;
            }

            @Override
            public BaseProviders vehicle() {
                return null;
            }

            @Override
            public BaseProviders verb() {
                return null;
            }

            @Override
            public BaseProviders weather() {
                return null;
            }

            @Override
            public BaseProviders word() {
                return null;
            }

            @Override
            public BaseProviders yoda() {
                return null;
            }

            @Override
            public BaseProviders zodiac() {
                return null;
            }

            @Override
            public BaseProviders pronouns() {
                return null;
            }
        };
    }

    @Test
    public void testGenerateInvalid() {
        String invalidIdNumber = generator.generateInvalid(faker);
        assertNotNull(invalidIdNumber);
        assertNotEquals(generator.checksum(invalidIdNumber.substring(0, invalidIdNumber.length() - 1)), Character.getNumericValue(invalidIdNumber.charAt(invalidIdNumber.length() - 1)));
    }
}
