import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//
//
//
//
public class ATM
{

    int moneyInTotal;
    //int banknotes;
    boolean isWorking;


    BufferedReader stdin;

    String output;

    public ATM() {
        isWorking = true;
        moneyInTotal = 0;
        stdin = new BufferedReader(new InputStreamReader(System.in));
        output = "Hello";
    }

    public void run() throws IOException {

        while (isWorking)
        {
            showUserData();
            readUserData();
        }
    }

    public void showUserData()
    {
        System.out.println(output);
    }

    public void readUserData() throws IOException {

        output = "";
        String command = stdin.readLine().toLowerCase();

        String inputsArray[] = command.split(" ");
        switch (inputsArray[0]) {
            case "put" : put(command, inputsArray); break;
            case "get" : get(command, Integer.parseInt(inputsArray[1])); break;
            case "dump" : dump(command); break;
            case "state" : state(command); break;
            case "quit" : quit(command); break;
            default:error(command);

        }
    }

    public void error(String command)
    {
        System.out.println(">"+command);
        output = "Неправильный ввод";
    }

    public void put(String command, String[] arr)
    {
        //output = "";
        System.out.println(">"+command);
        try {
            countMoneyInTotal(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
        } catch (ArrayIndexOutOfBoundsException e)
        {
            output = "Вы не указали, сколько купюр хотите внести.";
        }
    }

    public void get(String command, int value)
    {
        int valueNeeded = value;
        StringBuilder sb = new StringBuilder("");
        System.out.println(">" + command);
        int total = 0;
        //int rest = 0;

        List<Banknotes> banknotes = Arrays.asList(Banknotes.values());
        BanknotesComparator comp = new BanknotesComparator();
        Collections.sort(banknotes, comp);

        boolean run = true;
        while (valueNeeded > 0 && moneyInTotal > 0 && run) {
            for (Banknotes a : banknotes) {
                if (a.getValue() == 1 && a.getCount() == 0) run = false;
                int aCount = 0;
                int count = a.getCount();
                int banknoteValue = a.getValue();
                if (banknoteValue <= valueNeeded) {
                    if (a.getCount() > 0) {

                        sb.append(a.getValue()+"=");
                        do {
                            if (banknoteValue > valueNeeded) break;
                            aCount++;
                            valueNeeded -= banknoteValue;
                            total += a.getValue();
                            a.setCount(count - aCount);
                            moneyInTotal -= a.getValue();
                        } while (valueNeeded > 0 && a.getCount() > 0);
                        sb.append(aCount+", ");
                    }
                }
            }
        }
        sb.append("всего " + total);
        if (valueNeeded > 0) sb.append("\nбез " + valueNeeded);

        output = sb.toString();

    }

    public void dump(String command)
    {
        System.out.println(">" + command);

        List<Banknotes> list = Arrays.asList(Banknotes.values());

        BanknotesComparator comp = new BanknotesComparator();
        Collections.sort(list, comp);

        StringBuilder sb = new StringBuilder("");
        for (Banknotes a : list)
        {
            sb.append("Купюр номиналом " + a.getValue() + " всего в банкомате " + a.getCount());
            if (list.indexOf(a) != (list.size()-1))
            sb.append("\n");
        }
        output = sb.toString();
    }

    public void state(String command)
    {
        //output = "";
        System.out.println(">" + command);
        output = moneyInTotal+"";
    }

    public void quit(String command)
    {
        isWorking = false;
        System.out.println(">" + command);
    }

    public void countMoneyInTotal(int banknote, int amount)
    {
        Banknotes[] banknotes = Banknotes.values();
        for (Banknotes banknote1 : banknotes) {
            Banknotes b;
            if (banknote == banknote1.getValue()) {
                b = banknote1;
                b.setCount(b.getCount() + amount);
                moneyInTotal += banknote * amount;
                output = "всего " + moneyInTotal;
                break;
            } else output = "Нет такой банкноты";
        }
    }
}
