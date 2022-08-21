import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableTest implements Callable<Account> {
    @Override
    public Account call() throws Exception{
        Thread.sleep(new Random().nextInt(2000));
        Account newAccount = new Account(generatingRandomString(10),generatingRandomFloat());

        return newAccount ;
    }
    public String generatingRandomString(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    public float generatingRandomFloat() {
        DecimalFormat df2 = new DecimalFormat("0.0");
        double min = 0.00;
        double max = 200.10;
        double rand = new Random().nextDouble();
        String result = df2.format(min + (rand * (max - min))) + "0";
        return Float.parseFloat((result));
    }
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void main(String args[]) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<Account>> list = new ArrayList<Future<Account>>();

        Callable<Account> callable = new CallableTest();
        for (int i = 0; i < getRandomNumber(200,500); i++) {
            Future<Account> future = executor.submit(callable);
            list.add(future);
        }
        for (Future<Account> fut : list) {
            try {
                System.out.println(new Date() + "::" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}
