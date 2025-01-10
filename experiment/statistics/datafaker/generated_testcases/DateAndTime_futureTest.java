
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;

public class DateAndTime_futureTest {
    private DateAndTime dateAndTime;
    private BaseFaker faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseFaker();
        dateAndTime = new DateAndTime(faker);
    }

    @Test
    void testFutureDate() {
        Timestamp referenceDate = new Timestamp(System.currentTimeMillis());
        Timestamp futureDate = dateAndTime.future(100, TimeUnit.SECONDS, referenceDate);
        assertThat(futureDate.getTime()).isGreaterThanOrEqualTo(referenceDate.getTime());
    }
}
