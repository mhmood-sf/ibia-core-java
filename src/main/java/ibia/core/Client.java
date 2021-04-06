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
     * @return the created Conference
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
     * @return the created Committee
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
     * @return the created Delegate
     */
    public static Delegate addNewDelegate(String name, String delegation, String committeeId) {
        Delegate del = new Delegate(name, delegation, committeeId);
        DbDriver.insertOne(del);
        return del;
    }

    /**
     * @return ArrayList of all persisted Conferences
     */
    public static ArrayList<Conference> getAllConferences() {
        return DbDriver.fetchAll(Conference.class);
    }

    /**
     * Fetch Committees belonging to a specific Conference
     * @param conferenceId ID of the Conference
     * @return ArrayList of Committees
     */
    public static ArrayList<Committee> getConferenceCommittees(String conferenceId) {
        return DbDriver.findAll(Committee.class, c -> c.getConferenceId().equals(conferenceId));
    }

    /**
     * Fetch Delegates belonging to a specific Committee
     * @param committeeId ID of the Committee
     * @return ArrayList of Delegates
     */
    public static ArrayList<Delegate> getCommitteeDelegates(String committeeId) {
        return DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(committeeId));
    }

    /**
     * Fetch Topics belonging to a specific Committee
     * @param committeeId ID of the Committee
     * @return ArrayList of Topics
     */
    public static ArrayList<Topic> getCommitteeTopics(String committeeId) {
        return DbDriver.findAll(Topic.class, t -> t.getCommitteeId().equals(committeeId));
    }

    /**
     * Fetch Resolutions belonging to a specific Topic
     * @param topicId ID of the Topic
     * @return ArrayList of Resolutions
     */
    public static ArrayList<Resolution> getTopicResolutions(int topicId) {
        return DbDriver.findAll(Resolution.class, r -> r.getTopicId() == topicId);
    }

    /**
     * Deletes a Conference instance from the database,
     * including it's child Committees and the
     * Delegates in those committees.
     * @param confId ID of the Conference
     */
    public static void deleteConference(String confId) {
        deleteConferenceChildren(confId);
        DbDriver.deleteById(Conference.class, confId);
    }

    /**
     * Deletes a Committee instance form the database,
     * including it's child Delegates.
     * @param comId ID of the Committee
     */
    public static void deleteCommittee(String comId) {
        deleteCommitteeChildren(comId);
        DbDriver.deleteById(Committee.class, comId);
    }

    /**
     * Deletes a Delegate instance from the database.
     * @param delId ID of the Delegate
     */
    public static void deleteDelegate(String delId) {
        DbDriver.deleteById(Delegate.class, delId);
    }

    /**
     * Deletes all child committees belonging to
     * a Conference instance, including the delegates
     * belonging to each of the child committees.
     * @param confId ID of the Conference
     */
    public static void deleteConferenceChildren(String confId) {
        ArrayList<Committee> coms =
            DbDriver.findAll(Committee.class, c -> c.getConferenceId().equals(confId));

        if (coms == null) return;
        for (Committee com : coms) {
            deleteCommitteeChildren(com.getId());
            DbDriver.deleteOne(com);
        }
    }

    /**
     * Deletes all child delegates belonging to
     * a Committee instance.
     * @param comId ID of the Committee
     */
    public static void deleteCommitteeChildren(String comId) {
        ArrayList<Delegate> dels =
            DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(comId));

        if (dels == null) return;
        for (Delegate del : dels) {
            DbDriver.deleteOne(del);
        }
    }
}
