
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This view displays the menu for the application
 *
 * @author P.U.G.S
 */
public class MenuView extends JPanel implements ChangeListener {

    /**
     * Displays the main menu of the Mancala game
     *
     * @param model the Application state used for this view
     */
    public MenuView(final ApplicationState model) {
        setLayout(new BorderLayout());

        JButton styleBlue = new JButton("Default Blue");
        JButton styleRain = new JButton("Rainbow Mode");

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());

        String[] numOptions = {"3", "4"};
        final JComboBox stonesPer = new JComboBox(numOptions);
        JLabel stonesPerDesc = new JLabel("Number of Stones Per Pit: ");

        optionsPanel.add(stonesPerDesc);
        optionsPanel.add(stonesPer);


        JLabel title = new JLabel("    Welcome to our Mancala game. Please choose a style.");

        styleBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int styleIndex = 1;
                int stones = stonesPer.getSelectedIndex() + 3;
                model.newGame(stones);
                model.setState(styleIndex);

            }
        });

        styleRain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int styleIndex = 2;
                int stones = stonesPer.getSelectedIndex() + 3;
                model.newGame(stones);
                model.setState(styleIndex);
            }
        });

        add(styleBlue, BorderLayout.EAST);
        add(styleRain, BorderLayout.WEST);
        add(title, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Stub. Main menu always looks the same.
    }
}
