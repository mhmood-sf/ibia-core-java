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
    public static Conference beginNewConference(String name) {
        Conference conf = new Conference(name);
        DbDriver.insertOne(conf);
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
