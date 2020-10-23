package ibia.core;

import java.util.ArrayList;

import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;
import ibia.core.utils.Resolution;
import ibia.core.utils.Topic;

/**
 * Provides a simple interface for interacting
 * with the core backend.
 */
public class Client {
    /**
     * Creates and persists a new Conference instance.
     * 
     * @param name name of conference.
     */
    public static Conference addNewConference(String name) {
        Conference conf = new Conference(name);
        DbDriver.insertOne(conf);
        return conf;
    }

    /**
     * Creates and persists a new Committee instance.
     * 
     * @param name name of committee
     * @param conferenceId the parent Conference
     */
    public static Committee addNewCommittee(String name, String conferenceId) {
        Committee com = new Committee(name, conferenceId);
        DbDriver.insertOne(com);
        return com;
    }

    /**
     * Creates and persists a new Delegate instance.
     * 
     * @param name name of delegate
     * @param delegation the country ("delegation") assigned to the delegate
     * @param committeeId the parent Committee
     */
    public static Delegate addNewDelegate(String name, String delegation, String committeeId) {
        Delegate del = new Delegate(name, delegation, committeeId);
        DbDriver.insertOne(del);
        return del;
    }

    /**
     * Obtain a list of all persisted conferences.
     */
    public static ArrayList<Conference> getAllConferences() {
        return DbDriver.fetchAll(Conference.class);
    }

    /**
     * Obtain a list of persisted committees belonging
     * to a particular conference.
     */
    public static ArrayList<Committee> getConferenceCommittees(String conferenceId) {
        return DbDriver.findAll(Committee.class, c -> c.getConferenceId().equals(conferenceId));
    }

    /**
     * Obtain a list of persisted delegates belonging
     * to a particular committee.
     */
    public static ArrayList<Delegate> getCommitteeDelegates(String committeeId) {
        return DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(committeeId));
    }

    /**
     * Obtain a list of persisted topics belonging
     * to a particular committee.
     */
    public static ArrayList<Topic> getCommitteeTopics(String committeeId) {
        return DbDriver.findAll(Topic.class, t -> t.getCommitteeId().equals(committeeId));
    }

    /**
     * Obtain a list of persisted resolutions
     * belonging to a particular topic.
     */
    public static ArrayList<Resolution> getTopicResolutions(int topicId) {
        return DbDriver.findAll(Resolution.class, r -> r.getTopicId() == topicId);
    }

    /**
     * Deletes a Conference instance from the database,
     * including it's child Committees and the
     * Delegates in those committees.
     */
    public static void deleteConference(String confId) {
        deleteConferenceChildren(confId);
        DbDriver.deleteById(Conference.class, confId);
    }

    /**
     * Deletes a Committee instance form the database,
     * including it's child Delegates.
     */
    public static void deleteCommittee(String comId) {
        deleteCommitteeChildren(comId);
        DbDriver.deleteById(Committee.class, comId);
    }

    /**
     * Deletes a Delegate instance from the database.
     */
    public static void deleteDelegate(String delId) {
        DbDriver.deleteById(Delegate.class, delId);
    }

    /**
     * Deletes all child committees belonging to
     * a Conference instance, including the delegates
     * belonging to each of the child committees.
     */
    public static void deleteConferenceChildren(String confId) {
        ArrayList<Committee> coms =
            DbDriver.findAll(Committee.class, c -> c.getConferenceId().equals(confId));
        
        for (Committee com : coms) {
            deleteCommitteeChildren(com.getId());
            DbDriver.deleteOne(com);
        }
    }

    /**
     * Deletes all child delegates belonging to
     * a Committee instance.
     */
    public static void deleteCommitteeChildren(String comId) {
        ArrayList<Delegate> dels =
            DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(comId));
        
        for (Delegate del : dels) {
            DbDriver.deleteOne(del);
        }
    }
}
