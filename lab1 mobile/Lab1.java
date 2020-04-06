import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lab1 {

    public static void main(String[] args) throws IOException {
        double in_call_dur = 0.0, out_call_dur = 0.0, price;
        int smscount = 0;
        String csvFile = args[0];
        BufferedReader br;
        String line;
        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
            String[] info = line.split(",");
            if (info[1].equals("911926375")) {
                out_call_dur = Double.parseDouble(info[3]);
                smscount = Integer.parseInt(info[4]);
            }
            if (info[2].equals("911926375")) in_call_dur = Double.parseDouble(info[3]);
        }
        price = price(in_call_dur, out_call_dur, smscount);
        System.out.println(price);
    }

    private static double price(double a, double b, int c) {
        double price = 0.0;
        if (a > 5.0) price += a - 5.0;
        price += b * 4;
        if (c > 5) price += c - 5;
        return price;
    }
}
