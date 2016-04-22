import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
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
    List<Banknotes> banknotes;



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
        output = ">" + command+"\n";

        String inputsArray[] = command.split(" ");
        switch (inputsArray[0]) {
            case "put" : put(inputsArray); break;
            case "get" : get(Integer.parseInt(inputsArray[1])); break;
            case "dump" : dump(); break;
            case "state" : state(); break;
            case "quit" : quit(); break;
            default:error();

        }
    }

    public void error()
    {
        output += "Неправильный ввод";
    }

    public void put(String[] arr)
    {
        try {
            output += countMoneyInTotal(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            output += "Вы не указали, сколько купюр хотите внести.";
        } catch (NumberFormatException nfe)
        {
            output += "Вторым и третьим аргументами должны быть числа";
        }

    }

    public void get(int value)
    {
        int valueNeeded = value;
        StringBuilder sb = new StringBuilder();
        int total = 0;

        initializeBanknotes();

        boolean run = true;
        while (valueNeeded > 0 && moneyInTotal > 0 && run) {
            for (Banknotes a : banknotes) {
                if (a.getValue() == 1 && a.getCount() == 0) run = false;
                int aCount = 0;
                int count = a.getCount();
                int banknoteValue = a.getValue();
                if (banknoteValue <= valueNeeded) {
                    if (a.getCount() > 0) {
                        String s = a.getValue()+"=";
                        sb.append(s);
                        do {
                            if (banknoteValue > valueNeeded) break;
                            aCount++;
                            valueNeeded -= banknoteValue;
                            total += a.getValue();
                            a.setCount(count - aCount);
                            moneyInTotal -= a.getValue();
                        } while (valueNeeded > 0 && a.getCount() > 0);
                        s = aCount+", ";
                        sb.append(s);
                    }
                }
            }
        }
        if (total > 0)
        {
            String s = "всего " + total;
            sb.append(s);
            if (valueNeeded > 0)
            {
                s = "\nбез " + valueNeeded;
                sb.append(s);
            }
        }
        else sb.append("Невозможно выдать запаршиваемую сумму");


        output += sb.toString();
    }

    public void dump()
    {
        initializeBanknotes();

        StringBuilder sb = new StringBuilder("");
        for (Banknotes a : banknotes)
        {
            String s = "Купюр номиналом " + a.getValue() + " всего в банкомате " + a.getCount();
            sb.append(s);
            if (banknotes.indexOf(a) != (banknotes.size()-1))
            sb.append("\n");
        }
        output += sb.toString();
    }

    public void state()
    {
        output += moneyInTotal+"";
    }

    public void quit()
    {
        showUserData();
        isWorking = false;
    }

    public String countMoneyInTotal(int banknote, int amount)
    {
        initializeBanknotes();
        String result = "";
        for (Banknotes banknote1 : banknotes) {
            Banknotes b;
            if (banknote == banknote1.getValue()) {
                b = banknote1;
                b.setCount(b.getCount() + amount);
                moneyInTotal += banknote * amount;
                result = "всего " + moneyInTotal;
                break;
            } else result =  "Нет такой банкноты " + banknote;
        }

        return result;
    }

    private void initializeBanknotes(){
        if (banknotes == null) {
            banknotes = Arrays.asList(Banknotes.values());
            BanknotesComparator comp = new BanknotesComparator();
            Collections.sort(banknotes, comp);
        }
    }
}
