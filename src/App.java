
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Calculette");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel screen = new JPanel();
        JTextArea textArea = new JTextArea(2, 20);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        screen.add(textArea);
        textArea.setSize(300, 200);
        screen.setSize(350, 200);

        JPanel row1 = new JPanel();
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton buttonSlash = new JButton("/");
        row1.add(button7);
        row1.add(button8);
        row1.add(button9);
        row1.add(buttonSlash);

        JPanel row2 = new JPanel();
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton buttonStar = new JButton("*");
        row2.add(button4);
        row2.add(button5);
        row2.add(button6);
        row2.add(buttonStar);

        JPanel row3 = new JPanel();
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton buttonMinus = new JButton("-");
        row3.add(button1);
        row3.add(button2);
        row3.add(button3);
        row3.add(buttonMinus);

        JPanel row4 = new JPanel();
        JButton button0 = new JButton("0");
        JButton buttonEqual = new JButton("=");
        JButton buttonPlus = new JButton("+");
        JButton buttonC = new JButton("C");
        row4.add(button0);
        row4.add(buttonEqual);
        row4.add(buttonPlus);
        row4.add(buttonC);

        frame.add(screen);
        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        frame.add(row1);
        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        frame.add(row2);
        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        frame.add(row3);
        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        frame.add(row4);
        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setVisible(true);

        button0.addActionListener(e -> textArea.append("0"));
        button1.addActionListener(e -> textArea.append("1"));
        button2.addActionListener(e -> textArea.append("2"));
        button3.addActionListener(e -> textArea.append("3"));
        button4.addActionListener(e -> textArea.append("4"));
        button5.addActionListener(e -> textArea.append("5"));
        button6.addActionListener(e -> textArea.append("6"));
        button7.addActionListener(e -> textArea.append("7"));
        button8.addActionListener(e -> textArea.append("8"));
        button9.addActionListener(e -> textArea.append("9"));
        buttonPlus.addActionListener(e -> textArea.append(" + "));
        buttonMinus.addActionListener(e -> {
            if (textArea.getText().isEmpty()) {
                textArea.append("-");
            } else if (textArea.getText().endsWith(" ")) {
                textArea.append("-");
            } else {
                textArea.append(" - ");
            }
        });
        buttonStar.addActionListener(e -> textArea.append(" * "));
        buttonSlash.addActionListener(e -> textArea.append(" / "));
        buttonEqual.addActionListener(e -> {
            String[] parts = textArea.getText().split(" ");
            if (parts.length == 3) {
                double num1 = Double.parseDouble(parts[0]);
                double num2 = Double.parseDouble(parts[2]);
                String operator = parts[1];
                double result = 0;
                result = switch (operator) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> {
                        if (num2 != 0) {
                            yield num1 / num2;
                        } else {
                            yield 0;
                        }
                    }
                    default -> throw new IllegalArgumentException("Unexpected operator: " + operator);
                };
                if (num2 == 0 && operator.equals("/")) {
                    textArea.setText("Error: Division by zero");
                } else {
                    if (result % 1 == 0) {
                        textArea.setText(String.valueOf((int) result));
                    } else {
                    textArea.setText(String.valueOf(result));
                    }
                }
            } else {
                textArea.setText("Error: Invalid input");
            }
        });
        buttonC.addActionListener(e -> textArea.setText(""));
    }
}
