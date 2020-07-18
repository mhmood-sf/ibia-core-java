package ibia.core;

import java.util.ArrayList;

import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;

public class Client {
    /**
     * Given the conference name, number of committees, and
     * number of delegates per committee, creates and persists
     * the conference instance, as well as the committees and
     * delegates. Returns the conference instance created.
     */
    public static Conference beginNewConference(String name, int size, int committeeSize) {
        Conference conf = new Conference(name, size);
        ArrayList<Committee> committees = new ArrayList<>();
        ArrayList<Delegate> delegates = new ArrayList<>();

        for (int i = 0; i <= size; i++) {
            Committee com = new Committee(i + "", committeeSize, conf.getId());
            committees.add(com);

            for (int j = 0; j <= committeeSize; j++) {
                Delegate del = new Delegate(j + "", "Drumpf", com.getId());
                delegates.add(del);
            }
        }

        DbDriver.insertOne(conf);
        DbDriver.insertAll(committees);
        DbDriver.insertAll(delegates);

        return conf;
    }

    /**
     * Given the conference ID, returns the committees belonging
     * to that conference.
     */
    public static ArrayList<Committee> getConferenceCommittees(String confId) {
        return DbDriver.findAll(Committee.class, c -> c.getConferenceId().equals(confId));
    }

    /**
     * Given the committee ID, returns the delegates belonging
     * to that committee.
     */
    public static ArrayList<Delegate> getCommitteeDelegates(String comId) {
        return DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(comId));
    }
}
