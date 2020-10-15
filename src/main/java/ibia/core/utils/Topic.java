package ibia.core.utils;

/**
 * Represents a Committee topic.
 * <br><br>
 * This class is also a Hibernate entity.
 */
class Topic {
    private int id;
    private String committeeId;
    private String topic;

    public Topic() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String id) {
        this.committeeId = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
