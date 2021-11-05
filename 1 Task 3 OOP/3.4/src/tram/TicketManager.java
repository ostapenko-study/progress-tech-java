package tram;

import java.time.LocalDate;
import java.time.Period;

public class TicketManager {

    public enum TicketType{
        Pay,
        Student,
        School
    }

    static abstract class ATicket{
        private long id;

        ATicket(long id){
            this.id = id;
        }

        public long getId() {
            return id;
        }

        protected abstract boolean try_to_enter(TicketManager tm);
        public abstract TicketType get_type();
    }

    static private class Ticket extends ATicket{
        LocalDate start;
        int days;
        int count;
        TicketType type;

        Ticket(long id, LocalDate start, int days, int count, TicketType type){
            super(id);
            this.start = start;
            this.days = days;
            this.count = count;
            this.type = type;
        }

        @Override
        protected boolean try_to_enter(TicketManager tm) {
            if(count > 0 && checkPeriod()){
                count--;
                return true;
            }
            return false;
        }

        private boolean checkPeriod() {
            LocalDate now = LocalDate.now();
            return start.isBefore(now) && now.isBefore(start.plusDays(days));
        }

        @Override
        public TicketType get_type() {
            return type;
        }
    }

    static private class PayTicket extends ATicket{

        int money;

        PayTicket(long id, int money) {
            super(id);
            this.money = money;
        }

        @Override
        protected boolean try_to_enter(TicketManager tm) {
            if(money >= tm.getCoast()){
                money -= tm.getCoast();
                return true;
            }
            return false;
        }

        @Override
        public TicketType get_type() {
            return TicketType.Pay;
        }
    }

    int coast = 4;

    public void setCoast(int coast) {
        this.coast = coast;
    }
    private int getCoast() {
        return coast;
    }

    private class generatorId{
        static long id = 10000;

        static long getId(){
            return ++id;
        }
    }

    ATicket createPayTicket(int money){
        return new PayTicket(generatorId.getId(), money);
    }

    ATicket createTicket(LocalDate start, int days, int count, TicketType type){
        return new Ticket(generatorId.getId(), start, days, count, type);
    }

    boolean try_to_enter(ATicket t){
        return t.try_to_enter(this);
    }
}
