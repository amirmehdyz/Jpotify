package view.Center;

import javax.swing.*;
import java.awt.*;

/**
 * ToolBar panel at the top of the CentralPanel and MainPage
 */
public class ToolBar extends JPanel {

    private RoundTextField searchBox;
    private JTextField userNameTextField;

    public ToolBar(String userName) {
        super();
//        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setLayout(new BorderLayout(15,15));
        this.setBackground(new Color(33, 33, 33));

//        add(Box.createRigidArea(new Dimension(15, 15)));

        searchBox = new RoundTextField(15);
        searchBox.setDefaultText("🔍 Search");
//        searchBox.setPreferredSize(new Dimension(50,20));
//        this.add(searchBox);
        this.add(searchBox,BorderLayout.WEST);

//        add(Box.createRigidArea(new Dimension(100, 0)));

        this.userNameTextField = new JTextField(userName);
        userNameTextField.setFont(new Font("TimesNewRoman", Font.LAYOUT_RIGHT_TO_LEFT, 9));
        userNameTextField.setForeground(new Color(245,245,245));
        userNameTextField.setBackground(new Color(33, 33, 33,0));
        userNameTextField.setEditable(false);
//        userNameTextField.setPreferredSize(new Dimension(30,10));
//        this.add(userNameTextField);
        this.add(userNameTextField, BorderLayout.EAST);
        revalidate();
    }
}