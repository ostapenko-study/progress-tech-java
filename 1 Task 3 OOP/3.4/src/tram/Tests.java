package tram;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @ParameterizedTest
    @CsvSource({"5,5", "10,10", "100,100"})
    void TestCountTicket(int count, int enter_accesses) {
        TicketManager tm = new TicketManager();
        TicketManager.ATicket st = tm.createTicket(LocalDate.now().minusDays(2), 5, count, TicketManager.TicketType.Student);
        TicketManager.ATicket sc = tm.createTicket(LocalDate.now().minusDays(2), 5, count, TicketManager.TicketType.School);
        TicketManager.ATicket p = tm.createTicket(LocalDate.now().minusDays(2), 5, count, TicketManager.TicketType.Pay);
        for (int i = 0; i < enter_accesses; ++i) {
            assertTrue(tm.try_to_enter(st));
            assertTrue(tm.try_to_enter(sc));
            assertTrue(tm.try_to_enter(p));
        }
        for (int i = 0; i < 100; ++i) {
            assertFalse(tm.try_to_enter(st));
            assertFalse(tm.try_to_enter(sc));
            assertFalse(tm.try_to_enter(p));
        }
    }

    @ParameterizedTest
    @CsvSource({"4,4,1", "7,4,1", "100,150,0", "999,25,39"})
    void TestMoneyTicket(int money, int coast, int excepted_accesses) {
        TicketManager tm = new TicketManager();
        tm.setCoast(coast);
        TicketManager.ATicket t = tm.createPayTicket(money);
        for (int i = 0; i < excepted_accesses; ++i) {
            tm.try_to_enter(t);
        }
        for (int i = 0; i < 100; ++i) {
            assertFalse(tm.try_to_enter(t));
        }
    }

    @ParameterizedTest
    @CsvSource({"2018-12-25,100", "2000-01-01,5", "1995-02-05,1000", "2009-09-09,9"})
    void TestPeriodTicketFalse(LocalDate start, int day) {
        TicketManager tm = new TicketManager();
        int count = 100;
        TicketManager.ATicket st = tm.createTicket(start, day, count, TicketManager.TicketType.Student);
        TicketManager.ATicket sc = tm.createTicket(start, day, count, TicketManager.TicketType.School);
        TicketManager.ATicket p = tm.createTicket(start, day, count, TicketManager.TicketType.Pay);

        for (int i = 0; i < 2 * count; ++i) {
            assertFalse(tm.try_to_enter(st));
            assertFalse(tm.try_to_enter(sc));
            assertFalse(tm.try_to_enter(p));
        }
    }
}
