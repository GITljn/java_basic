package properties;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class Load {
    public static void main(String[] args) throws IOException {
        Properties actors = new Properties();
        Reader reader = new FileReader("basic_project\\src\\properties\\actors.txt");
        actors.load(reader);
        for (String actor : actors.stringPropertyNames()) {
            System.out.println("name: " + actor + "   partner: " + actors.getProperty(actor));
        }
    }
}
