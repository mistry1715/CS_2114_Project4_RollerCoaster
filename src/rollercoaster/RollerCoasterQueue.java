package rollercoaster;

import list.AList;

public class RollerCoasterQueue {

    public static final int MIN_PERSON_HEIGHT = 96;
    private ArrayQueue<WaitingParty> queue;
    private AList<WaitingParty> shortiesParties;
    
    public RollerCoasterQueue() {
        queue = new ArrayQueue<WaitingParty>();
        shortiesParties = new AList<WaitingParty>();
    }
    
    public void enqueueParty(WaitingParty party) {
        
    }
}
