package rollercoaster;

import java.awt.Color;

import CS2114.Button;
import CS2114.CircleShape;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;
import list.AList;

public class CoasterWindow {

    private Window window;
    private RollerCoasterQueue queue;
    private CoasterTrain train;
    private AList<CircleShape> partyCircles;
    private Button seatParty;
    private Button sendTrain;
    private Shape coaster;
    private Shape coasterSeatsFilled;
    private Shape separator;
    public static final int TRAIN_HEIGHT = 50;
    public static final int QUEUE_STARTX = 100;
    public static final int QUEUE_STARTY = 150;
    public static final int DISPLAY_FACTOR = 10;
    private TextShape coasterStatus;
    private TextShape queueStatus;
    private TextShape errorM;
    private TextShape coasterCount;
    private TextShape queueFront;
    
    public CoasterWindow(RollerCoasterQueue queue) {
        this.queue = queue;
        
        // Window
        window = new Window("Roller Coaster Ride");
        
        // Add circle to the list
        partyCircles = new AList<CircleShape>();
        WaitingParty[] parties = new WaitingParty[queue.toArray().length];
        
        for (int i = 0; i < parties.length; i++) {
            parties[i] = (WaitingParty) queue.toArray()[i];
        }
        
        for (int i = 0; i < parties.length; i++) {
            CircleShape tempCircle = new CircleShape(parties[i].getLength() * DISPLAY_FACTOR,
                    parties[i].getLength() * DISPLAY_FACTOR);
            
            if (parties[i].willSplit) {
                tempCircle.setBackgroundColor(Color.GREEN);
            }
            else {
                tempCircle.setBackgroundColor(Color.RED);
            }
        }
        
        // Button  
        seatParty = new Button("Seat Party");
        sendTrain = new Button("Send Train");
        window.addButton(seatParty, WindowSide.SOUTH);
        window.addButton(sendTrain, WindowSide.SOUTH);
        
        seatParty.onClick(this, "clickedSeatParty");
        sendTrain.onClick(this, "clickedSendTrain");
       
        // TextShape
        coasterStatus = addTextShape("Coaster Status", 50, 50);
        window.addShape(coasterStatus);
        
    }
    
    private TextShape addTextShape(String str, int x, int y) {
        return new TextShape(x, y, str);
    }
    
    public void clickedSendTrain(Button b) {
        train.clear();
        updateCoaster();
        
        if (queue.isEmpty()) {
            endRide();
        }
        else {
            b.enable();
        }
    }

    public void clickedSeatParty(Button b) {
        WaitingParty party = queue.dequeueParty(train.getOpenSeats());
        
        if (party == null) {
            b.disable();
        }
        else {
            train.seatParty(party);
            updateQueue();
            updateCoaster();
        }
        
        if (queue.isEmpty()) {
            b.disable();
        }
    }

    private void updateCoaster() {
        // TODO Auto-generated method stub
        
    }
    
    private void endRide() {
        // TODO Auto-generated method stub
        
    }

    private void updateQueue() {
        
    }
    
}
