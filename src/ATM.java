import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//
//
//
//
public class ATM
{

    int moneyInTotal;
    int banknotes;


    BufferedReader stdin;

    String output;

    public ATM() {
        moneyInTotal = 0;
        stdin = new BufferedReader(new InputStreamReader(System.in));
        output = "Hello";
    }

    public void run() throws IOException {

        while (true)
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


        String command = stdin.readLine().toLowerCase();

        String inputsArray[] = command.split(" ");
        switch (inputsArray[0]) {

            case "put" : put(command, inputsArray); break;
            case "get" : get(); break;
            case "dump" : dump(); break;
            case "state" : state(command); break;
            //case "quit" : quit(); break;
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
        output = "";
        System.out.println(">"+command);
        countMoneyInTotal(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
    }

    public void get()
    {


    }

    public void dump()
    {

    }

    public void state(String command)
    {
        output = "";
        System.out.println(">" + command);
        output = moneyInTotal+"";
    }

    public void quit(String command)
    {

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
