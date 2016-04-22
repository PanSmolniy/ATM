import java.util.Comparator;

public class BanknotesComparator implements Comparator<Banknotes> {
    @Override
    public int compare(Banknotes o1, Banknotes o2) {
        return -(o1.getValue() - o2.getValue());
    }
}
