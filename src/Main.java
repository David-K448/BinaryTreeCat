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
        setLayout(new GridLayout(4, 1));
        JComponent[] inputComp = {
            new JLabel("Enter Binary Tree"), userInput
        };
        userInput.setColumns(20);
        JComponent[] outputComp = {
            new JLabel("Output"), consoleOutput
        };
        consoleOutput.setColumns(30);
        JButton[] buttonComp1 = {
            new JButton("Make Tree")
        };

        JButton[] buttonComp2 = {
            new JButton("Is Balanced?"),
            new JButton("Is Full?"),
            new JButton("Is Proper?"),
            new JButton("Height"),
            new JButton("Nodes"),
            new JButton("Inorder")
        };
        makeFlowPanel(inputComp);
        makeFlowPanel(buttonComp1);
        makeFlowPanel(buttonComp2);
        makeFlowPanel(outputComp);
        addActionListeners(buttonComp1, buttonComp2);
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

    private void addActionListeners(JButton[] makeButton, JButton[] buttons) {
        for(JButton b1: makeButton){
            b1.addActionListener(treeListener);
        }
        for(JButton b: buttons) {
            b.addActionListener(treeListener);
        }
    }

    private final ActionListener treeListener = event -> {
        try {
            switch ((event.getActionCommand())) {
                case "Make Tree":
                    inputTree = new BinaryTree(userInput.getText());
                    consoleOutput.setText("Tree Created: "+inputTree.toString());
                    break;
                case "Is Balanced?":
                    consoleOutput.setText(String.valueOf(inputTree.isBalanced()));
                    break;
                case "Is Full?":
                    consoleOutput.setText(String.valueOf(inputTree.isFull()));
                    break;
                case "Is Proper?":
                    consoleOutput.setText(String.valueOf(inputTree.isProper()));
                    break;
                case "Height":
                    consoleOutput.setText(String.valueOf(inputTree.height()));
                    break;
                case "Nodes":
                    consoleOutput.setText(String.valueOf(inputTree.nodes()));
                    break;
                case "Inorder":
                    consoleOutput.setText(String.valueOf(inputTree.inOrder()));
                    break;
                default:
                    break;
            }
        } catch (InvalidTreeSyntax e) {
            //TODO: handle exception
            JOptionPane.showMessageDialog(getParent(), e.getMessage());
        } catch (IndexOutOfBoundsException indexEr) {
            JOptionPane.showMessageDialog(getParent(), "Missing Input!");
        }
    };

}
//(A(G(j)(1))(z(5)))
//(4(2(3)(1))(6(5)))