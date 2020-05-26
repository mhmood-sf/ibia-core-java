package ibia.core.entities;

import ibia.core.utils.Id;

// represents an instance of a delegate
public class Delegate implements Entity {
    public final String id = Id.generate("del");

    public Delegate() throws Exception {
        // hmm
    }
}
