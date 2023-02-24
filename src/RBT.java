
import java.awt.Color;

public class RBT {

    static Node santinela;


    static {
    	RBT.santinela = new Node(null, Color.BLACK, null, null, null);
    }
    Node root, NodeAjoute;

    public Node getroot() {
        return root;
    }

    public RBT() {
        root = RBT.santinela;
    }

    public void ajout(Data o) {
        root = ajout(o, root, null);
        reOrganiser(NodeAjoute);
    }

    public Node research(int val) {
        return research(getroot(), new Data(val));
    }

    private Node research(Node n, Data o) {
        if (n.issantinela()) {
            return null;
        }
        if (o.getValue() < n.getInfo().getValue()) {
            return research(n.getleft(), o);
        } else if (o.getValue() == n.getInfo().getValue()) {
            return n;
        } else {
            return research(n.getright(), o);
        }
    }

    private Node ajout(Data o, Node r, Node p) {
        // p est le parent de r 
        if (r.issantinela()) {
            r = NodeAjoute = new Node(o, Color.RED, r, r, p);
        } else if (o.compareTo(r.info) < 0) {
            r.left = ajout(o, r.left, r);
        } else {
            r.right = ajout(o, r.right, r);
        }
        return r;
    }

    private void rotationleft(Node n) {
        Node y = n.right;
        n.right = y.left;
        if (!y.left.issantinela()) {
            y.left.parent = n;
        }
        y.parent = n.parent;
        if (n.parent == null) {
            root = y;
        } else if (n.parent.left == n) {
            n.parent.left = y;
        } else {
            n.parent.right = y;
        }
        y.left = n;
        n.parent = y;
    }

    private void rotationrighte(Node n) {
        Node y = n.left;
        n.left = y.right;
        if (!y.right.issantinela()) {
            y.right.parent = n;
        }
        y.parent = n.parent;
        if (n.parent == null) {
            root = y;
        } else if (n.parent.right == n) {
            n.parent.right = y;
        } else {
            n.parent.left = y;
        }
        y.right = n;
        n.parent = y;
    }

    private void reOrganiser(Node n) {
        while (n != root && n.parent.color == Color.RED) {
            if (n.parent == n.parent.parent.left) {
                Node y = n.parent.parent.right;
                if (y.color == Color.RED) {
                    n.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    n.parent.parent.color = Color.RED;
                    n = n.parent.parent;
                } else {
                    if (n == n.parent.right) {
                        n = n.parent;
                        rotationleft(n);
                    }
                    n.parent.color = Color.BLACK;
                    n.parent.parent.color = Color.RED;
                    rotationrighte(n.parent.parent);
                }
            } else {
                Node y = n.parent.parent.left;
                if (y.color == Color.RED) {
                    n.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    n.parent.parent.color = Color.RED;
                    n = n.parent.parent;
                } else {
                    if (NodeAjoute == n.parent.left) {
                        n = n.parent;
                        rotationrighte(n);
                    }
                    n.parent.color = Color.BLACK;
                    n.parent.parent.color = Color.RED;
                    rotationleft(n.parent.parent);
                }
            }
        }
        root.color = Color.BLACK; // la root est toujours noire 
    }

    //Suppression -------------------------------------------------------

    public void supprimer(Data o) {
        supprimer(root, o);
    }

    private Node supprimer(Node r, Data o) {
        if (r.issantinela()) {
            return r; // Pas trouvé
        }
        if (o.compareTo(r.info) == 0) {
            r = supprimer(r);
        } else if (o.compareTo(r.info) < 0) {
            supprimer(r.left, o);
        } else {
            supprimer(r.right, o);
        }
        return r;
    }
    private Node supprimer(Node z) {
        Node y, x;
        if (z.left.issantinela() || z.right.issantinela()) {
            y = z;
        } else {
            y = arbreSuccesseur(z);
        }
        if (!y.left.issantinela()) {
            x = y.left;
        } else {
            x = y.right;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        if (y != z) {
            z.info = y.info;
        }
   // si le Node supprimé est un Node rouge, il n’y a rien à
        // faire, l’arbre conserve ses propriétés
        // en revanche si le nœud supprimé est noir, 
        // il faut reorganiser l’arbre
        if (y.color == Color.black) {
            reOrganiserSuppression(x);
        }
        return y;
    }

    private Node arbreSuccesseur(Node x) {
   // le Node successseur de x dans l'arbre,
        // santinela si c'est le plus grand
        if (!x.right.issantinela()) {
            return arbreMinimum(x.right);
        }
        Node y = x.parent;
        while (!y.issantinela() && x == y.right) {
            x = y;
            y = x.parent;
        }
        return y;
    }

    private Node arbreMinimum(Node x) {
        while (!x.left.issantinela()) {
            x = x.left;
        }
        return x;
    }

    private void reOrganiserSuppression(Node n) {
        // re organisation de l'arbre, en remontant vers la root
        while (n != root && n.color == Color.black) {
            if (n == n.parent.left) {
                Node y = n.parent.right;
                if (y.color == Color.red) {
                    y.color = Color.black;
                    n.parent.color = Color.red;
                    rotationleft(n.parent);
                    y = n.parent.right;
                }
                if (y.left.color == Color.black && y.right.color == Color.black) {
                    y.color = Color.red;
                    n = n.parent;
                } else {
                    if (y.right.color == Color.black) {
                        y.left.color = Color.black;
                        y.color = Color.red;
                        rotationrighte(y);
                        y = n.parent.right;
                    }
                    y.color = n.parent.color;
                    n.parent.color = Color.black;
                    y.right.color = Color.black;
                    rotationleft(n.parent);
                    break;
                }
            } else {
                Node y = n.parent.left;
                if (y.color == Color.red) {
                    y.color = Color.black;
                    n.parent.color = Color.red;
                    rotationrighte(n.parent);
                    y = n.parent.left;
                }
                if (y.right.color == Color.black && y.left.color == Color.black) {
                    y.color = Color.red;
                    n = n.parent;
                } else {
                    if (y.left.color == Color.black) {
                        y.right.color = Color.black;
                        y.color = Color.red;
                        rotationleft(y);
                        y = n.parent.left;
                    }
                    y.color = n.parent.color;
                    n.parent.color = Color.black;
                    y.left.color = Color.black;
                    rotationrighte(n.parent);
                    break;
                }
            }
        }
        n.color = Color.black;
    }
}