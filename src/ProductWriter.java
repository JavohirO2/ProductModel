import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        String ID = "";
        String name = "";
        String description = "";
        double cost;
        boolean repeat = false;
        String rec;
        ArrayList<String> recs = new ArrayList<>();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTextData.txt");

        do {
            ID = SafeInput.getNonZeroLenString(in, "Please enter the product's ID");

            name = SafeInput.getNonZeroLenString(in, "Please enter the name of your product");

            description = SafeInput.getNonZeroLenString(in, "Please enter the product description");

            cost = SafeInput.getDouble(in, "Enter the cost of " + name + ".");


            rec = ID + ", " + name + ", " + description + ", " + cost + ".";

            recs.add(rec);

            repeat = SafeInput.getYNConfirm(in, "Are you done?");

        }
        while (!repeat);

        System.out.println(recs);



        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String r : recs)
            {
                writer.write(r, 0, r.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}

