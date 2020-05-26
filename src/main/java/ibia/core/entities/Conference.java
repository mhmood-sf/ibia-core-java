package ibia.core.entities;

import ibia.core.utils.Id;

// represents an instance of a conference
public class Conference implements Entity {
    public final String id = Id.generate("com");

    public Conference() throws Exception {
        // hmm
    }
}
