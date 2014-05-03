
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/*
 * This class gives us version of the board game,
 * in which the numbers are displayed in the pits,
 * representing the number of stones.
 *
 * @author P.U.G.S.
 */

public class GameView extends JPanel implements ChangeListener {

    private JPanel playingPane;
    private final ApplicationState model;
    int[] board;
    JLabel playerLabel;
    private String player1TurnString = "Player one's turn";
    private String player2TurnString = "Player two's turn";
    PitIcon player1Mancala;
    PitIcon player2Mancala;
    ArrayList<PitIcon> pitIcons;
    GameViewConfig config;

    public GameView(final ApplicationState model, GameViewConfig config) {
        this.config = config;
        this.model = model;
        board = model.getPits();
        JButton undoButton = new JButton("Undo");
        undoButton.setBounds(800, 10, 75, 50); //Size and position of button
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.undo();
                redraw();
            }
        });
        this.add(undoButton);

        playerLabel = new JLabel(getPlayerTurnString());
        playerLabel.setBounds(100, 5, 200, 100);
        this.add(playerLabel);

        playingPane = new JPanel();
        playingPane.setLayout(new BorderLayout());
        playingPane.setBounds(50, 70, 800, 300);
        playingPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        this.add(playingPane);

        JPanel smallPits = new JPanel();
        pitIcons = new ArrayList<PitIcon>();

        //Could only get grid layout to work. Gridbag layout would be better.
        smallPits.setLayout(new GridLayout(2, 6));
        //Get current pits state.
        int[] pits = model.getPits();

        //creates a pit and places stones in each of them
        for (int i = 0; i < 12; i++) {
            JLabel pitLab = new JLabel();
            PitIcon tempPitIcon = new PitIcon(90, 90, pits[i]);
            tempPitIcon.setColors(config.getPitColor(), config.getStoneColor());
            tempPitIcon.setRectangular(config.getRectangularPits());
            pitIcons.add(tempPitIcon); //Maintain reference for changing pit stones.
            pitLab.setIcon(tempPitIcon);
            pitLab.setPreferredSize(new Dimension(100, 100));
            smallPits.add(pitLab);
            final int index = i;
            pitLab.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    model.playMoveRowMajorOrder(index);
                }
            });
        }

        //Creates the large pits (Mancalas) and places their stones in them.
        //Wrapping the Icons in JLabels.
        JLabel player1Pit = new JLabel();
        JLabel player2Pit = new JLabel();
        player1Mancala = new PitIcon(75, 200, model.getMancala1());
        player2Mancala = new PitIcon(75, 200, model.getMancala2());
        //Configure mancalas
        player1Mancala.setColors(config.getPitColor(), config.getStoneColor());
        player1Mancala.setRectangular(config.getRectangularPits());
        player2Mancala.setColors(config.getPitColor(), config.getStoneColor());
        player2Mancala.setRectangular(config.getRectangularPits());
        player2Pit.setIcon(player1Mancala);
        player1Pit.setIcon(player2Mancala);

        playingPane.add(player2Pit, BorderLayout.EAST);
        playingPane.add(player1Pit, BorderLayout.WEST);
        playingPane.add(smallPits, BorderLayout.CENTER);
        playingPane.validate();

        redraw();
    }

    public void paintComponent() {
        // TODO Auto-generated method stub
    }

    private String getPlayerTurnString() {
        if (model.getPlayer1Turn()) {
            return player1TurnString;
        } else {
            return player2TurnString;
        }
    }

    public void redraw() {
        board = model.getPits();

        //Updating playerLabel
        playerLabel.setText(getPlayerTurnString());
        //Updating pits.
        for (int i = 0; i < 12; i++) {
            pitIcons.get(i).setStones(board[i]);
        }
        player1Mancala.setStones(model.getMancala1());
        player2Mancala.setStones(model.getMancala2());
        playingPane.repaint();
    }

    public void stateChanged(ChangeEvent e) {
        redraw();
    }
}
