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

            partyCircles.add(tempCircle);
        }

        // Button  
        initSeatPartyButtonStatus();
        initSendTrainButtonStatus();

        // Separator
        separator = new Shape(2, (int) (window.getGraphPanelHeight() * 0.8));
        separator.setX(window.getGraphPanelWidth() / 5);
        separator.setY(window.getGraphPanelHeight() / 10);
        window.addShape(separator);

        // TextShape
        displayCoasterCount();
        displayQueueFront();

        // Coaster
        coaster = new Shape(10, TRAIN_HEIGHT);
        window.addShape(coaster);
        coaster.setX((separator.getX()/2) - 5);
        coaster.setY((window.getGraphPanelHeight() - TRAIN_HEIGHT)/2);

        updateCoaster();

        // Queue
        updateQueue();
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

    private TextShape addTextShape(String str, int x, int y) {
        return new TextShape(x, y, str);
    }

    private void updateCoaster() {
        coasterSeatsFilled = new Shape(10, 
                (int) (2.5 * (CoasterTrain.SEATS - train.getOpenSeats())));
        coasterSeatsFilled.setBackgroundColor(Color.YELLOW);
        coasterSeatsFilled.setX(coaster.getX());
        coasterSeatsFilled.setY((int) (coaster.getY() + train.getOpenSeats() * 2.5));
        window.moveToFront(coasterSeatsFilled);

        window.repaint();

        updateTextShapes();
    }

    private void updateQueue() {
        updateCircles();    
        updateTextShapes();
    }

    private void endRide() {
        errorM = addTextShape("Ride closed",
                (window.getGraphPanelWidth() - separator.getY() - errorM.getWidth()) / 2,
                (window.getGraphPanelHeight() - errorM.getHeight()) / 2);
        errorM.setBackgroundColor(Color.YELLOW);
        window.addShape(errorM);

        seatParty.disable();
        sendTrain.disable();
    }

    private void drawCircles() {
        int x = QUEUE_STARTX;
        int y = QUEUE_STARTY;

        for (int i = 0; i < partyCircles.getLength(); i++) {
            CircleShape circle = partyCircles.getEntry(i);
            circle.setX(x);
            circle.setY(y - circle.getHeight() / 2);

            x += circle.getWidth() + 5;
        }

        window.repaint();
    }

    private void updateCircles() {
        drawCircles();
    }

    private void updateTextShapes() {
        updateFirstParty(queue.getFront().getLength());

        coasterCount.setText("Available Seats:" + train.getOpenSeats());

        window.repaint();
    }

    private void updateFirstParty(int n) {
        queueFront = addTextShape("Perons in the first Waiting party:" + n, 
                separator.getX() + 20,
                (int) (window.getGraphPanelHeight() * 0.9 - coasterCount.getHeight()));
        window.addShape(queueFront);

        window.repaint();
    }

    private void initSeatPartyButtonStatus() {
        seatParty = new Button("Seat Party");
        window.addButton(seatParty, WindowSide.SOUTH);
        seatParty.onClick(this, "clickedSeatParty");
        seatParty.enable();
    }

    private void initSendTrainButtonStatus() {
        sendTrain = new Button("Send Train");
        window.addButton(sendTrain, WindowSide.SOUTH);
        sendTrain.onClick(this, "clickedSendTrain");
        sendTrain.enable();
    }

    private void displayCoasterCount() {
        coasterCount = addTextShape("Available Seats:" + train.getOpenSeats(), 
                (separator.getX() - coasterStatus.getWidth()) / 2, 
                (int) (window.getGraphPanelHeight() * 0.9 - coasterCount.getHeight()));
        window.addShape(coasterCount);

        coasterStatus = addTextShape("Coaster Status", 50, 50);
        coasterStatus.setX((separator.getX() - coasterStatus.getWidth()) / 2);
        coasterStatus.setY(separator.getY());
        window.addShape(coasterStatus);

        window.repaint();
    }

    private void displayQueueFront() {
        queueFront = addTextShape("Perons in the first Waiting party:" + queue.getFront().getLength(), 
                separator.getX() + 20,
                (int) (window.getGraphPanelHeight() * 0.9 - coasterCount.getHeight()));
        window.addShape(queueFront);

        queueStatus = addTextShape("Queues Status:(Green:Willing to split)"
                + "(Red:Not willing to split)", 0, 0);
        window.addShape(queueStatus);

        window.repaint();
    }

}
