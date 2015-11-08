package rollercoaster;

import java.awt.Color;

import CS2114.Button;
import CS2114.CircleShape;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;
import list.AList;

/**
 * This object is the front end. Here we build our window, 
 * its buttons, and render the RollerCoasterQueue and 
 * CoasterTrain on the window in a meaningful way.
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.11.02
 */
public class CoasterWindow {

    /**
     * Window
     */
    private Window window;

    /**
     * List
     */
    private RollerCoasterQueue queue;
    private CoasterTrain train;
    private AList<CircleShape> partyCircles;

    /**
     * Button
     */
    private Button seatParty;
    private Button sendTrain;

    /**
     * Shape of coaster
     */
    private Shape coaster;
    private Shape coasterSeatsFilled;

    /**
     * Separator
     */
    private Shape separator;

    /**
     * Final value
     */
    public static final int TRAIN_HEIGHT = 200;
    public static final int TRAIN_WIDTH = 40;
    public static final int QUEUE_STARTX = 200;
    public static final int QUEUE_STARTY = 150;
    public static final int DISPLAY_FACTOR = 10;

    /**
     * Text
     */
    private TextShape coasterStatus;
    private TextShape queueStatus;
    private TextShape errorM;
    private TextShape coasterCount;
    private TextShape queueFront;

    /**
     * Constructor
     * 
     * @param queue A queue contains WaitingParty
     */
    public CoasterWindow(RollerCoasterQueue queue) {
        this.queue = queue;
        this.train = new CoasterTrain();
        this.partyCircles = new AList<CircleShape>();

        // Window
        window = new Window("Roller Coaster Ride");

        // Separator
        separator = new Shape(0, 0, 2, (int) (window.getGraphPanelHeight() * 0.8), Color.BLACK);
        separator.setX((int) (window.getGraphPanelWidth() * 0.25));
        separator.setY((int) (window.getGraphPanelHeight() * 0.1 - 10));
        window.addShape(separator);

        // Button  
        initSeatPartyButtonStatus();
        initSendTrainButtonStatus();

        // Display
        displayCoasterCount();
        displayQueueFront();
    }

    /**
     * Method for sendTrain Button
     * 
     * @param b Button
     */
    public void clickedSendTrain(Button b) {
        train.clear();
        updateCoaster();

        if (queue.isEmpty()) {
            endRide();
        }
        else {
            updateSendTrainButtonStatus();
            updateSeatPartyButtonStatus();
            b.enable();
        }
    }
    
    /**
     * Method for seatParty Button
     * 
     * @param b Button
     */
    public void clickedSeatParty(Button b) {
        WaitingParty party = queue.dequeueParty(train.getOpenSeats());
        updateSeatPartyButtonStatus();

        if (party == null) {
            b.disable();
        }
        else {
            train.seatParty(party);
            updateQueue();
            updateCoaster();
        }
    }

    /**
     * Update status of the send train Button
     */
    private void updateSendTrainButtonStatus() {
        if (queue.isEmpty()) {
            sendTrain.disable();
        }
        else {
            sendTrain.enable();
        } // end if
    }

    /**
     * Update status of the seat party Button
     */
    private void updateSeatPartyButtonStatus() {
        if (queue.isEmpty()) {
            seatParty.disable();
        }
        else {
            seatParty.enable();
        } // end if
    }

    /**
     * Return a TextShape
     * 
     * @param str String
     * @param x X coordinate
     * @param y Y coordinate
     * @return TextShape
     */
    private TextShape addTextShape(String str, int x, int y) {
        TextShape text = new TextShape(x, y, str);
        text.setBackgroundColor(Color.WHITE);

        return text;
    }

    /**
     * Update coaster
     */
    private void updateCoaster() {
        if (coasterSeatsFilled != null) {
            window.removeShape(coasterSeatsFilled);
        } 
        
        coasterSeatsFilled = new Shape(coaster.getX(),
                coaster.getY() + train.getOpenSeats()
                        * (TRAIN_HEIGHT / CoasterTrain.SEATS),
                TRAIN_WIDTH, (CoasterTrain.SEATS - train.getOpenSeats())
                        * (TRAIN_HEIGHT / CoasterTrain.SEATS),
                Color.ORANGE);
        coasterSeatsFilled.setForegroundColor(Color.BLACK);
        window.addShape(coasterSeatsFilled);
        window.moveToFront(coasterSeatsFilled);
        
        updateTextShapes();
        window.repaint();
    }

    /**
     * Update queue
     */
    private void updateQueue() {
        updateCircles();    
        updateTextShapes();
    }

    /**
     * End ride
     */
    private void endRide() {
        errorM = addTextShape("Ride closed", 0, 0);
        errorM.setX((window.getGraphPanelWidth() - separator.getY() - errorM.getWidth()) / 2);
        errorM.setY((window.getGraphPanelHeight() - errorM.getHeight()) / 2);
        errorM.setBackgroundColor(Color.ORANGE);
        window.addShape(errorM);

        updateSeatPartyButtonStatus();
        updateSendTrainButtonStatus();
    }

    /**
     * Update Circles
     */
    private void updateCircles() {
        for (int i = 0; i < partyCircles.getLength(); i++) {
            window.removeShape(partyCircles.getEntry(i));
        }
        
        partyCircles.clear();
        
        // Add circle to the list
        partyCircles = new AList<CircleShape>();
        WaitingParty[] parties = new WaitingParty[queue.toArray().length];

        for (int i = 0; i < parties.length; i++) {
            parties[i] = (WaitingParty) queue.toArray()[i];
        }

        for (int i = 0; i < parties.length; i++) {
            CircleShape tempCircle = new CircleShape(0, 0,
                    parties[i].getLength() * DISPLAY_FACTOR,
                    parties[i].willSplit() ? Color.GREEN : Color.RED);
            partyCircles.add(tempCircle);
        } 
        
        drawCircles();
    }

    /**
     * Draw circles in the list
     */
    private void drawCircles() {
        int x = QUEUE_STARTX;
        int y = QUEUE_STARTY;

        for (int i = 0; i < partyCircles.getLength(); i++) {
            CircleShape circle = partyCircles.getEntry(i);
            circle.setX(x);
            circle.setY(y - circle.getHeight() / 2);
            window.addShape(circle);

            x += circle.getWidth() + 5;
        }

        window.repaint();
    }

    /**
     * Update TextShapes
     */
    private void updateTextShapes() {
        // If queue is not empty, get the number of people
        if (!queue.isEmpty()) {
            updateFirstParty(queue.getFront().getLength());
        } 
        
        coasterCount.setText("Available Seats:" + train.getOpenSeats());
        window.repaint();
    }

    /**
     * Update first party in the queue
     * @param n Number of person in the first Waiting party
     */
    private void updateFirstParty(int n) {
        queueFront.setText("Perons in the first Waiting party:" + n);
        window.repaint();
    }

    /**
     * Initialize seatParty Button
     */
    private void initSeatPartyButtonStatus() {
        seatParty = new Button("Seat Party");
        window.addButton(seatParty, WindowSide.SOUTH);
        seatParty.onClick(this, "clickedSeatParty");
        seatParty.enable();
    }

    /**
     * Initialize sendTrain Button
     */
    private void initSendTrainButtonStatus() {
        sendTrain = new Button("Send Train");
        window.addButton(sendTrain, WindowSide.SOUTH);
        sendTrain.onClick(this, "clickedSendTrain");
        sendTrain.enable();
    }

    /**
     * Display coaster count
     */
    private void displayCoasterCount() {
        // Coaster
        coaster = new Shape(0, 0, 40, TRAIN_HEIGHT, Color.WHITE);
        coaster.setForegroundColor(Color.BLACK);
        coaster.setX((separator.getX() - coaster.getWidth()) / 2);
        coaster.setY((window.getGraphPanelHeight() - TRAIN_HEIGHT) / 2);
        window.addShape(coaster);
        
        coasterStatus = addTextShape("Coaster Status", 0, 0);
        coasterStatus.setX((separator.getX() - coasterStatus.getWidth()) / 2);
        coasterStatus.setY(separator.getY());
        window.addShape(coasterStatus);

        coasterCount = addTextShape("Available Seats:" + 20, 0, 0);
        coasterCount.setX((separator.getX() - coasterCount.getWidth()) / 2);
        coasterCount.setY((int) (window.getGraphPanelHeight() * 0.9 - coasterCount.getHeight()));
        window.addShape(coasterCount);

        window.repaint();
    }

    /**
     * Display queue front
     */
    private void displayQueueFront() {
        updateCircles();

        // Text
        queueFront = addTextShape("Perons in the first Waiting party:" + queue.getFront().getLength(), 
                separator.getX() + 20,
                (int) (window.getGraphPanelHeight() * 0.9 - coasterCount.getHeight()));
        window.addShape(queueFront);

        queueStatus = addTextShape("Queues Status:(Green:Willing to split)"
                + "(Red:Not willing to split)", separator.getX() + 20, separator.getY());
        window.addShape(queueStatus);

        window.repaint();
    }

}
