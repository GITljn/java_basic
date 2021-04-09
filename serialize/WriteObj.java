package serialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteObj {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Actor actor1 = new Actor("孟鹤堂", "周九良");
        Actor actor2 = new Actor("张鹤伦", "郎鹤严");
        Actor actor3 = new Actor("烧饼", "曹鹤阳");
        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(actor1);
        actors.add(actor2);
        actors.add(actor3);
        FileOutputStream fos = new FileOutputStream("basic_project\\src\\serialize\\actors.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(actors);
        fos.close();
        oos.close();
    }
}
