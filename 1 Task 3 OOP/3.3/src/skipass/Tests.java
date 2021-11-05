package skipass;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    void TestNowTicket(){
        TicketManager tm = new TicketManager();

        TicketManager.Ticket t = tm.createTicket(
                LocalDateTime.of(
                        LocalDate.now(),
                        LocalTime.of(0,0,0)),
                LocalDateTime.of(
                        LocalDate.now().plusDays(1),
                        LocalTime.of(0,0,0))
        );

        assertTrue(tm.try_to_enter(t.getId()));
    }

    @Test
    void TestOlderTicket(){
        TicketManager tm = new TicketManager();

        TicketManager.Ticket t = tm.createTicket(
                LocalDateTime.of(
                        LocalDate.now().minusDays(5),
                        LocalTime.of(0,0,0)),
                LocalDateTime.of(
                        LocalDate.now().minusDays(3),
                        LocalTime.of(0,0,0))
        );

        assertFalse(tm.try_to_enter(t.getId()));
    }

    @Test
    void TestFutureTicket(){
        TicketManager tm = new TicketManager();

        TicketManager.Ticket t = tm.createTicket(
                LocalDateTime.of(
                        LocalDate.now().plusDays(5),
                        LocalTime.of(0,0,0)),
                LocalDateTime.of(
                        LocalDate.now().plusDays(7),
                        LocalTime.of(0,0,0))
        );

        assertFalse(tm.try_to_enter(t.getId()));
    }

    @Test
    void TestCountTicket(){
        TicketManager tm = new TicketManager();

        int count = 15;

        TicketManager.Ticket t = tm.createTicket(
                LocalDateTime.of(
                        LocalDate.now(),
                        LocalTime.of(0,0,0)),
                LocalDateTime.of(
                        LocalDate.now().plusDays(1),
                        LocalTime.of(0,0,0)),
                count
        );
        for(int i = 0; i < count; ++i){
            assertTrue(tm.try_to_enter(t.getId()));
        }
        assertFalse(tm.try_to_enter(t.getId()));
    }

    @Test
    void TestAccess(){
        TicketManager tm = new TicketManager();

        TicketManager.Ticket t = tm.createTicket(
                LocalDateTime.of(
                        LocalDate.now(),
                        LocalTime.of(0,0,0)),
                LocalDateTime.of(
                        LocalDate.now().plusDays(1),
                        LocalTime.of(0,0,0)),
                3
        );

        assertTrue(tm.try_to_enter(t.getId()));
        tm.block(t.getId());
        assertFalse(tm.try_to_enter(t.getId()));
    }
    @Test
    void TestAccessSeveralTickets(){
        TicketManager tm = new TicketManager();

        TicketManager.Ticket t1 = tm.createTicket(
                LocalDateTime.of(
                        LocalDate.now(),
                        LocalTime.of(0,0,0)),
                LocalDateTime.of(
                        LocalDate.now().plusDays(1),
                        LocalTime.of(0,0,0)),
                3
        );
        TicketManager.Ticket t2 = tm.createTicket(
                LocalDateTime.of(
                        LocalDate.now(),
                        LocalTime.of(0,0,0)),
                LocalDateTime.of(
                        LocalDate.now().plusDays(1),
                        LocalTime.of(0,0,0))
        );
        assertTrue(tm.try_to_enter(t1.getId()));
        assertTrue(tm.try_to_enter(t2.getId()));
        tm.block(t1.getId());
        assertFalse(tm.try_to_enter(t1.getId()));
        assertTrue(tm.try_to_enter(t2.getId()));
    }
}
