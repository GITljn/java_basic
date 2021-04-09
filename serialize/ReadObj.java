package serialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadObj {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("basic_project\\src\\serialize\\actors.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Actor> actors = (ArrayList<Actor>) ois.readObject();
        for (Actor actor : actors) {
            System.out.println("name: " + actor.getName() + "   partner: " + actor.getPartner());
        }
    }
}
