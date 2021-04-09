package properties;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public class Store {
    public static void main(String[] args) throws IOException {
        Properties actors = new Properties();
        actors.setProperty("孟鹤堂", "周九良");
        actors.setProperty("张鹤伦", "郎鹤严");
        actors.setProperty("郭麒麟", "阎鹤祥");
        Writer w = new FileWriter("basic_project\\src\\properties\\actors.txt");
        actors.store(w, "actors");
        w.close();
    }
}
