import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotionWindow extends JFrame {
    private int HIGHT = 250;
    private int WIGHT = 350;
    private int LONG_W = 300;
    private int LONG_H = 200;

    public NotionWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(HIGHT, WIGHT, LONG_W, LONG_H);
        setTitle("Calculator: Notion");
        JButton OK = new JButton("OK");
        JPanel ok = new JPanel(new GridLayout(4,1));
        add(ok);
        JLabel jLabel = new JLabel("The calculator has the following restrictions:");
        JLabel jLabel2 = new JLabel("- don't work with negative digits;");
        JLabel jLabel3 = new JLabel("- don't work with division digits.");
        ok.add(jLabel, BorderLayout.CENTER);
        ok.add(jLabel2, BorderLayout.CENTER);
        ok.add(jLabel3, BorderLayout.CENTER);
        ok.add(OK,BorderLayout.SOUTH);
        OK.addActionListener(e -> {
            setVisible(false);
        });
    }
}