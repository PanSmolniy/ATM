//
//
//
//иналом 1, 3, 5, 10, 25, 50, 100, 500, 1000 и 5000 рублей
public enum Banknotes
{
    ONE(1), THREE(3), FIVE(5), TEN(10), TWENTY_FIVE(25), FIFTY(50),
    HUNDRED(100), FIVE_HUNDRED(500), THOUSAND(1000), FIVE_THOUSAND(5000);

    private int count;

    private int value;
    Banknotes(int value)
    {
        this.value = value;
        count = 0;
    }

    public int getValue()
    {
        return value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
