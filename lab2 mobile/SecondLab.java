import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class SecondLab {
    public static void main(String[] args) throws Exception {
        double price;
        int traffic = 0;
        String path = "traffic.csv";
        BufferedReader br;
        String line;
        br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            String[] csvcolumn = line.split(",");
            if (csvcolumn.length > 1) {
                if (csvcolumn[3].equals("192.168.250.62") || csvcolumn[4].equals("192.168.250.62")) {
                    traffic += Integer.parseInt(csvcolumn[12]);
                    StringBuilder timestring = new StringBuilder(csvcolumn[1]);
                    timestring.delete(0, timestring.indexOf(" ") + 1);
                    int time = LocalTime.parse(timestring).toSecondOfDay() - 41400;
                    try (FileWriter writer = new FileWriter("forgraph.txt", true)) {
                        writer.write(time + "," + traffic + "\n");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        System.out.println(traffic);
        price = Price(traffic);
        System.out.println("Price: " + price + " R.");
        Process process = Runtime.getRuntime().exec("python graph.py");
        process.waitFor();
        process.destroy();
    }

    private static double Price(int trafficCount) {
        double price;
        if (trafficCount > 100) {
            price = ((double) trafficCount - 100) + 50;
        } else price = (double) trafficCount * 0.5;
        price *= 100;
        price = Math.round(price) / 100.0;
        return price;
    }
}