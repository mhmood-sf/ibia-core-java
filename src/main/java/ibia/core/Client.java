package ibia.core;

import java.util.ArrayList;

import ibia.core.entities.Committee;
import ibia.core.entities.Conference;

public class Client {
    public static void beginNewConference(String name, int size, int committeeSize) {
        Conference conf = new Conference(name, size);
        ArrayList<Committee> committees = new ArrayList<>();

        for (int i = 0; i <= size; i++) {
            committees.add(new Committee(i + "", committeeSize, conf.getId()));
        }
    }
}
