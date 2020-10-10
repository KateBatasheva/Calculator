import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

public class CalculatorWindow extends JFrame {

    private int HIGHT = 300;
    private int WIGHT = 300;
    private int LONG_W = 250;
    private int LONG_H = 300;
    static String memory;


    public CalculatorWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(HIGHT, WIGHT, LONG_W, LONG_H);
        setTitle("Calculator");
        JPanel numb = new JPanel(new GridLayout(6, 3));
        JPanel res = new JPanel(new GridLayout(2, 1));
        add(numb, BorderLayout.SOUTH);
        add(res, BorderLayout.EAST);
        JButton[] buttons = new JButton[18];
        String[] sym = {"(", ")","+", "-", "*", "/", "^", "memory", "="};
        JTextField string2 = new JTextField();
        add(string2);
        NotionWindow open = new NotionWindow();
        ActionListener buttonPress = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (string2.getText().contains("=")){
                    string2.setText("");
                }

                for (int i = 0; i < buttons.length-1; i++) {
                    if (e.getSource() == buttons[i]) {
                        string2.setText(string2.getText().concat(buttons[i].getText()));
                    }
                }
            }
        };
        for (int i = 0; i < buttons.length - 1; i++) {
            if (i < 10) {
                buttons[i] = new JButton("" + i + "");
            } else {
                buttons[i] = new JButton("" + sym[i - 10] + "");
            }
            buttons[i].addActionListener(buttonPress);
            numb.add(buttons[i]);
        }
        JButton clear = new JButton("<-");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                string2.setText("");
            }
        });
        res.add(clear);
        buttons[buttons.length - 1] = new JButton("=");
        buttons[buttons.length - 1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorLogic cal = new CalculatorLogic();
                try {
                    String res =  String.valueOf(cal.eval(string2.getText()));
                    string2.setText(string2.getText().concat(" = "+res));

                } catch (NoSuchElementException a) {
                    string2.setText("invalid expression.");
                    open.setVisible(true);
                    }
                }
        });
        memory = String.valueOf(res);
        buttons[buttons.length - 2] = new JButton("");
        numb.add(buttons[buttons.length - 2] );
        res.add(buttons[buttons.length - 1]);
        setVisible(true);
        open.setVisible(true);

    }
}

