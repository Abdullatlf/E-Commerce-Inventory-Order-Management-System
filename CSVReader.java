
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CSVReader{

    //create load method for every CSV file and gather them in one loadFile method


    public static void loadCustomersFromCSV(/*need to pass system and file name*/){
    String path = "dataset/customers.csv";
    String line = "";
    try{
    BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();
        while ((line = br.readLine()) != null) { 
            String[] values = line.split(",");
            String customerId = values[0];
            String name = values[1];
            String email = values[2];

            //System.out.println(line);

            Customer customer = new Customer();
            //needs to add customer to list

        }
    }catch(FileNotFoundException e){
        e.printStackTrace();
    }catch(IOException e){
        e.printStackTrace();

    }
}

}