package ibia.core;

import java.util.ArrayList;

import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;
import ibia.core.utils.Resolution;
import ibia.core.utils.Topic;

// TODO: fix JavaDoc errors/warnings

/**
 * Provides a simple interface for interacting
 * with the core backend.
 */
public class Client {
    /**
     * Creates and persists a new Conference instance.
     * 
     * @param name
     * @return
     */
    public static Conference addNewConference(String name) {
        Conference conf = new Conference(name);
        DbDriver.insertOne(conf);
        return conf;
    }

    /**
     * Creates and persists a new Committee instance.
     * 
     * @param name
     * @param conferenceId the parent Conference
     * @return
     */
    public static Committee addNewCommittee(String name, String conferenceId) {
        Committee com = new Committee(name, conferenceId);
        DbDriver.insertOne(com);
        return com;
    }

    /**
     * Creates and persists a new Delegate instance.
     * 
     * @param name
     * @param delegation
     * @param committeeId the parent Committee
     * @return
     */
    public static Delegate addNewDelegate(String name, String delegation, String committeeId) {
        Delegate del = new Delegate(name, delegation, committeeId);
        DbDriver.insertOne(del);
        return del;
    }

    /**
     * Obtain a list of all persisted conferences.
     * 
     * @return
     */
    public static ArrayList<Conference> getAllConferences() {
        return DbDriver.fetchAll(Conference.class);
    }

    /**
     * Obtain a list of persisted committees belonging
     * to a particular conference.
     * 
     * @param conferenceId
     * @return
     */
    public static ArrayList<Committee> getConferenceCommittees(String conferenceId) {
        return DbDriver.findAll(Committee.class, c -> c.getConferenceId().equals(conferenceId));
    }

    /**
     * Obtain a list of persisted delegates belonging
     * to a particular committee.
     * 
     * @param committeeId
     * @return
     */
    public static ArrayList<Delegate> getCommitteeDelegates(String committeeId) {
        return DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(committeeId));
    }

    /**
     * Obtain a list of persisted topics belonging
     * to a particular committee.
     * @param committeeId
     * @return
     */
    public static ArrayList<Topic> getCommitteeTopics(String committeeId) {
        return DbDriver.findAll(Topic.class, t -> t.getCommitteeId().equals(committeeId));
    }

    /**
     * Obtain a list of persisted resolutions
     * belonging to a particular topic.
     * @param topicId
     * @return
     */
    public static ArrayList<Resolution> getTopicResolutions(int topicId) {
        return DbDriver.findAll(Resolution.class, r -> r.getTopicId() == topicId);
    }
}
