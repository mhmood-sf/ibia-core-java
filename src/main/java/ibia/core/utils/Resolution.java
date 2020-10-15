package ibia.core.utils;

/**
 * Represents a Committee resolution.
 * <br><br>
 * This class is also a Hibernate entity.
 */
public class Resolution {
    private int id;
    private String mainSubmitter;
    private int topicId;
    private boolean passed;

    public Resolution() {}

    public Resolution(String mainSubmitter, int topicId) {
        this.mainSubmitter = mainSubmitter;
        this.topicId = topicId;
        this.passed = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainSubmitter() {
        return mainSubmitter;
    }

    public void setMainSubmitter(String mainSubmitter) {
        this.mainSubmitter = mainSubmitter;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public boolean getPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
