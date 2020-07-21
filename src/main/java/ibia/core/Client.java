package ibia.core;

import java.util.ArrayList;

import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;

public class Client {
    /**
     * Creates, persists and returns a new Conference.
     */
    public static Conference beginNewConference(String name) {
        Conference conf = new Conference(name);
        DbDriver.insertOne(conf);
        return conf;
    }

    /**
     * Creates, persists and returns a new Committee.
     */
    public static Committee beginNewCommittee(String name, String conferenceId) {
        Committee com = new Committee(name, conferenceId);
        DbDriver.insertOne(com);
        return com;
    }

    /**
     * Creates, persists and returns a new Delegate.
     */
    public static Delegate addNewDelegate(String name, String delegation, String committeeId) {
        Delegate del = new Delegate(name, delegation, committeeId);
        DbDriver.insertOne(del);
        return del;
    }

    /**
     * Returns all created conferences.
     */
    public static ArrayList<Conference> getAllConferences() {
        return DbDriver.fetchAll(Conference.class);
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
