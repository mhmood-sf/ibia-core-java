package ibia.core.entities;

import ibia.core.utils.Id;

// represents an instance of a committee
public class Committee implements Entity {
    public final String id = Id.generate("com");

    public Committee() throws Exception {
        // hmm
    }
}
