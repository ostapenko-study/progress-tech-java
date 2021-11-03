package skipass;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TicketManager {

    public static abstract class Ticket {
        final long id;

        private Ticket(long id) {
            this.id = id;
        }

        long getId(){return id;}

        protected abstract boolean try_to_enter();

    }

    private static class PeriodTicket extends Ticket{
        LocalDateTime start;
        LocalDateTime end;

        private PeriodTicket(long id, LocalDateTime start, LocalDateTime end) {
            super(id);
            this.start = start;
            this.end = end;
        }

        @Override
        protected boolean try_to_enter() {
            LocalDateTime now = LocalDateTime.now();
            return now.isAfter(start) && now.isBefore(end);
        }
    }

    private static class CountTicket extends PeriodTicket{
        int count;

        private CountTicket(long id, LocalDateTime start, LocalDateTime end, int count){
            super(id, start, end);
            this.count = count;
        }

        @Override
        protected boolean try_to_enter() {
            if(count > 0 && super.try_to_enter()){
                count--;
                return true;
            }

            return false;
        }
    }

    static long counter = 10000;
    private static class MetaInfoTicket{
        public Ticket ticket;
        public boolean access;
        MetaInfoTicket(Ticket t){
            this.ticket = t;
            this.access = true;
        }
    }
    Map<Long, MetaInfoTicket> storage = new HashMap<Long, MetaInfoTicket>();

    Ticket createTicket(LocalDateTime start, LocalDateTime end, int count){
        counter++;
        long id = counter;
        Ticket t = new CountTicket(id, start, end, count);
        storage.put(id, new MetaInfoTicket(t));
        return t;
    }

    Ticket createTicket(LocalDateTime start, LocalDateTime end){
        counter++;
        long id = counter;
        Ticket t = new PeriodTicket(id, start, end);
        storage.put(id, new MetaInfoTicket(t));
        return t;
    }

    boolean try_to_enter(long id_ticket){
        if(storage.containsKey(id_ticket)) {
            if (storage.get(id_ticket).access) {
                return storage.get(id_ticket).ticket.try_to_enter();
            }
        }

        return false;
    }

    boolean block(long id_ticket){
        if(storage.containsKey(id_ticket)){
            storage.get(id_ticket).access = false;
            return true;
        }else{
            return false;
        }
    }
}
