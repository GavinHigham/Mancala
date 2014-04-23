
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Dakota
 */
public class MenuView extends JPanel implements ChangeListener {

    public MenuView(final ApplicationState model) {
        setLayout(new BorderLayout());

        JButton styleNum = new JButton("Number Style");
        JButton styleJewel = new JButton("Jewel Style");
        
        JLabel title = new JLabel("                                     Welcome to our Mancala game. Please choose a style.");

        styleNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int styleIndex = 1;
                model.setState(styleIndex);
            }
        });

        styleJewel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int styleIndex = 2;
                model.setState(styleIndex);
            }
        });
        
        add(styleNum, BorderLayout.EAST);
        add(styleJewel, BorderLayout.WEST);
        add(title, BorderLayout.CENTER);

    }

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		// Stub. Main menu always looks the same.
	}
}
