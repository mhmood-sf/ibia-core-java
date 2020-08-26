package ibia.core;

import java.util.ArrayList;

import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;

// TODO: fix JavaDoc errors/warnings
// TODO: consistent naming for the get<Conf/Comm><Comm/Dels>() methods

/**
 * Provides a simple interface for interacting
 * with the core backend.
 */
public class Client {

    /**
     * Creates and persists a new Conference instance.
     * 
     * @param name - The name of the conference
     * @return The created Conference instance
     */
    public static Conference beginNewConference(String name) {
        Conference conf = new Conference(name);
        DbDriver.insertOne(conf);
        return conf;
    }

    /**
     * Creates and persists a new Committee instance.
     * 
     * @param name - The name of the committee.
     * @param conferenceId - The ID of the conference this committee belongs to.
     * @return The created Committee instance.
     */    public static Committee beginNewCommittee(String name, String conferenceId) {
        Committee com = new Committee(name, conferenceId);
        DbDriver.insertOne(com);
        return com;
    }

    /**
     * Creates and persists a new Delegate instance.
     * 
     * @param name - The name of the delegate.
     * @param delegation - Either a valid alpha2 code or some other custom delegation.
     * @param committeeId - The ID of the committee this delegate belongs to.
     * @return The created Conference instance.
     */
    public static Delegate addNewDelegate(String name, String delegation, String committeeId) {
        Delegate del = new Delegate(name, delegation, committeeId);
        DbDriver.insertOne(del);
        return del;
    }

    /**
     * Obtain a list of all persisted conferences.
     * 
     * @return - List of persisted Conference instances.
     */
    public static ArrayList<Conference> getAllConferences() {
        return DbDriver.fetchAll(Conference.class);
    }
    
    /**
     * Obtain a list of persisted committees belonging
     * to a particular conference.
     * 
     * @param conferenceId - ID of the conference the committees belong to.
     * @return List of persisted committee instances.
     */
    public static ArrayList<Committee> getConferenceCommittees(String conferenceId) {
        return DbDriver.findAll(Committee.class, c -> c.getConferenceId().equals(conferenceId));
    }

    /**
     * Obtain a list of persisted delegates belonging
     * to a particular committee.
     * 
     * @param committeeId - ID of the committee the delegates belong to.
     * @return List of persisted delegate instances.
     */
    public static ArrayList<Delegate> getCommitteeDelegates(String committeeId) {
        return DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(committeeId));
    }
}
