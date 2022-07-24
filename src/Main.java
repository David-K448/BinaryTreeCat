import javax.swing.*;

import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.ActionListener;

public class Main extends JFrame{

    private JTextField userInput = new JTextField("");
    private JTextField consoleOutput = new JTextField("");
    private static BinaryTree inputTree;
    public static void main(String[] args) throws Exception {
        Main frame = new Main();
        frame.setVisible(true);
    }

    // create constructor
    public Main() {
        //set title for frame
        super("Binary Tree Catagorizor");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        JComponent[] inputComp = {
            new JLabel("Enter Binary Tree"), userInput
        };
        userInput.setColumns(20);
        JComponent[] outputComp = {
            new JLabel("Output"), consoleOutput
        };
        consoleOutput.setColumns(30);
        JButton[] buttonComp = {
            new JButton("Make Tree"),
            new JButton("Is Balanced?"),
            new JButton("Is Full?"),
            new JButton("Is Proper?"),
            new JButton("Height"),
            new JButton("Nodes"),
            new JButton("Inorder")
        };
        makeFlowPanel(inputComp);
        makeFlowPanel(buttonComp);
        makeFlowPanel(outputComp);
        addActionListeners(buttonComp);
        consoleOutput.setEditable(false);
        //setResizable(false);

    }

    private void makeFlowPanel(JComponent[] components) {
        JPanel panel = new JPanel(new FlowLayout());
        for(Component c: components) {
            panel.add(c);
        }
        add(panel);
        pack();
    }

    private void addActionListeners(JButton[] buttons) {
        for(JButton b: buttons) {
            b.addActionListener(treeListener);
        }
    }

    private final ActionListener treeListener = event -> {

    };

}
