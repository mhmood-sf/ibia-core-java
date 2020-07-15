package ibia.core;

import java.util.ArrayList;

import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;

public class Client {
    public static void beginNewConference(String name, int size, int committeeSize) {
        Conference conf = new Conference(name, size);
        ArrayList<Committee> committees = new ArrayList<>();
        ArrayList<Delegate> delegates = new ArrayList<>();

        for (int i = 0; i <= size; i++) {
            Committee com = new Committee(i + "", committeeSize, conf.getId());
            committees.add(com);

            for (int j = 0; j <= committeeSize; j++) {
                Delegate del = new Delegate(j + "", com.getId());
                delegates.add(del);
            }
        }

        DbDriver.insertOne(conf);
        DbDriver.insertAll(committees);
        DbDriver.insertAll(delegates);
    }
}
