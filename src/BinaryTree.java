import java.util.EmptyStackException;
import java.util.Stack;

public class BinaryTree {
    Node parent, child;

    public BinaryTree(String text) throws InvalidTreeSyntax {
        Stack<Node>nStack = new Stack<>();

        String[] inputArray = text.substring(1, text.length()-1).split("(?<=\\()|(?=\\()|(?<=\\))|(?=\\))");
        parent = new Node(inputArray[0]);
        for(int i = 1; i < inputArray.length -1; i++) {
            if(inputArray[i].equals("(")) {
                nStack.push(parent);
                if(child != null) {
                    parent = child;
                }
            }else if(inputArray[i].equals(")")) {
                try {
                    child = parent;
                    parent = nStack.pop();
                } catch (EmptyStackException empStk) {
                    throw new InvalidTreeSyntax("Incorrect Formatting!");
                }
            }else {
                child = new Node(inputArray[i]);
                if (parent != null) {
                    parent.addChild(child);
                }
            }
        }
        if (this.recIsNodes(parent) * 3 != text.length()) {
            throw new InvalidTreeSyntax("Incorrect Syntax!");
        }
    }

    public boolean isBalanced() {
        return recIsBal(this.parent);
    }

    private boolean recIsBal(Node root) {
        if(root == null) {
            return true;
        }
        return(Math.abs(recHeight(root.left) - recHeight(root.right)) <= 1) && (recIsBal(root.left) && recIsBal(root.right));
    }

    public boolean isFull() {
        return recIsFull(this.parent, recHeight(this.parent), 0);
    }

    public boolean recIsFull(Node root, int height, int index) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            //return (height == index + 1);
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        return (recIsFull(root.left, height, index+1)) && recIsFull(root.right, height, index);
    }

    public boolean isProper() {
        return recIsProper(this.parent);
    }
            
    private boolean recIsProper(Node root) {
        if (root == null) {
            return true;
        }
        return ((root.left != null || root.right == null) && (root.left == null || root.right != null)) && (recIsProper(root.left) && (recIsProper(root.right)));
    }
    
    public int height() {
        return(recHeight(this.parent));
    }

    private int recHeight(Node root) {
        return (root == null) ? 0 : 1 + Math.max(recHeight(root.left), recHeight(root.right));
    }

    public int nodes() {
        return recIsNodes(this.parent);
    }

    private int recIsNodes(Node root) {
        return(root == null) ? 0 : 1 + recIsNodes(root.left) + recIsNodes(root.right);
    }

    public String inOrder() {
        return retInOrder(this.parent);
    }

    private String retInOrder(Node root) {
        return (root == null) ? "" : "(" + retInOrder(root.left) + root.info + retInOrder(root.right) + ")";
    }
    
    @Override
    public String toString() {
        return parent.toString();
    }

    public static class Node {
        private String info;
        private Node left; 
        private Node right;

        public Node(String info) {
            this.info = info;
        }

        private void addChild(Node child) throws InvalidTreeSyntax {
            if (this.left == null) {
                this.setLeft(child);
            } else if (this.right == null) {
                this.setRight(child);
            } else {
                throw new InvalidTreeSyntax("Nodes must have 2 children!");
            }
        }
        private void setLeft(Node nLeft) {
            left = nLeft;
        }
        private void setRight(Node nRight) {
            right = nRight;
        }

        @Override
        public String toString() {
            return toString(this);
        }
        private static String toString(Node root) {
            return (root == null) ? "" : "(" + root.info + toString(root.left) + toString(root.right) + ")";
        }
    }

}
