
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
 * This class displays the gameboard during the game depending on how
 * this particular version of the board is configured. 
 * 
 * @author P.U.G.S.
 */

public class GameView extends JPanel implements ChangeListener {

    private JPanel playingPane;
    private final ApplicationState model;
    int[] board;
    JLabel playerLabel;
    JLabel undoDesc;
    JLabel win;
    private String player1TurnString = "Player One's Turn";
    private String player2TurnString = "Player Two's Turn";
    private boolean finished;
    PitIcon player1Mancala;
    PitIcon player2Mancala;
    ArrayList<PitIcon> pitIcons;
    GameViewConfig config;

    /**
     * Creates a panel that displays the board as well as any other additions
     * that are displayed on the panel
     *
     * @param model The application state that GameView uses
     * @param config The configuration for the display of the pits and stones on
     * the board
     */
    public GameView(final ApplicationState model, GameViewConfig config) {
        this.config = config;
        this.model = model;
        board = model.getPits();
        JButton undoButton = new JButton("Undo");

        undoDesc = new JLabel("Undos Left:  Player One: " + model.getUndos(1) + "  Player Two: " + model.getUndos(2));
        undoDesc.setBounds(10, 10, 75, 50);
        undoDesc.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.add(undoDesc);

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

        finished = model.getGameOver();

        win = new JLabel("");
        this.add(win);

        playingPane = new JPanel();
        playingPane.setLayout(new BorderLayout());
        playingPane.setBounds(50, 70, 800, 300);
        playingPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        this.add(playingPane);

        JPanel smallPits = new JPanel();
        pitIcons = new ArrayList<PitIcon>();

        smallPits.setLayout(new GridLayout(2, 6));
        int[] pits = model.getPits();

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

        JLabel player1Pit = new JLabel();
        JLabel player2Pit = new JLabel();
        player1Mancala = new PitIcon(75, 200, model.getMancala1());
        player2Mancala = new PitIcon(75, 200, model.getMancala2());

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
        // Auto-generated method stub
    }

    /**
     * Creates a string that displays the player turn
     *
     * @return the string of the current players turn
     */
    private String getPlayerTurnString() {
        if (model.getPlayer1Turn()) {
            return player1TurnString;
        } else {
            return player2TurnString;
        }
    }

   /*
    * Method to redraw elements on the board to keep it
    * up to date througout the game.
    */
    public void redraw() {
        board = model.getPits();

        playerLabel.setText(getPlayerTurnString());

        for (int i = 0; i < 12; i++) {
            pitIcons.get(i).setStones(board[i]);
        }
        player1Mancala.setStones(model.getMancala1());
        player2Mancala.setStones(model.getMancala2());
        undoDesc.setText("Undos Left:  Player One: " + model.getUndos(1) + "  Player Two: " + model.getUndos(2));

        finished = model.getGameOver();
        if (finished) {
            if (model.getMancala1() > model.getMancala2()) {
                win.setText("Player One Wins!");
            } else {
                win.setText("Player Two Wins!");
            }
            win.setBorder(BorderFactory.createLineBorder(Color.RED));
            win.repaint();
        } else {
            win.setText("");
            win.setBorder(BorderFactory.createEmptyBorder());
        }
        playingPane.repaint();
    }

    /*
     * Every time the state of the game is changed,
     * like when a move is made, elements are redrawn.
     */
    public void stateChanged(ChangeEvent e) {
        redraw();
    }
}
