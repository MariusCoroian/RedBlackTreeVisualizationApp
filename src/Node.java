

import java.awt.Color;

public class Node {

    Color color;
    Data info;
    Node left, right, parent;
    private int dessinX, dessinY;

    Node(Data o) {
        color = Color.BLACK;
        info = o;
        left = right = parent = null;
        dessinX = dessinY = -1;
    }

    Node(Data o, Color c, Node g, Node d, Node p) {
        color = c;
        info = o;
        left = g;
        right = d;
        parent = p;
    }

    public int getNleft() {
        return 1 + getNbrFils(this.getright());
    }

    public int getNright() {
        return 1 + getNbrFils(this.getleft());
    }

    private int getNbrFils(Node n) {
        if (n.issantinela()) {
            return 0;
        }
        return 1 + getNbrFils(n.getleft()) + getNbrFils(n.getright());
    }

    public Color getcolor() {
        return color;
    }

    public Data getInfo() {
        return info;
    }

    public Node getleft() {
        return left;
    }

    public Node getright() {
        return right;
    }

    public Node getParent() {
        return parent;
    }

    public boolean issantinela() {
        return this == RBT.santinela;
    }

    public int getheight() {
        if (issantinela()) {
            return 0;
        } else {
            return 1 + Math.max(left.getheight(), right.getheight());
        }
    }

    public int getDessinX() {
        return dessinX;
    }

    public int getDessinY() {
        return dessinY;
    }

    public void setDessinX(int dessinX) {
        this.dessinX = dessinX;
    }

    public void setDessinY(int dessinY) {
        this.dessinY = dessinY;
    }

}