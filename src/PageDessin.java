

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PageDessin extends JPanel {
	RBT a;
    int containerWidth, containerHeight;
    public PageDessin(RBT a, int cw, int ch) {
        this.a = a;
        containerWidth = cw;
        containerHeight = ch;
    }

    public void DesignNode(Graphics g, Node n) {
        if (!n.getleft().issantinela()) {
            int nfg = n.getleft().getNleft();
            g.setColor(Color.BLACK);
            g.drawLine(n.getDessinX() + 17, n.getDessinY() + 17, n.getDessinX() - 30 * nfg + 17, n.getDessinY() + 50 + 17);
            n.getleft().setDessinX(n.getDessinX() - 30 * nfg);
            n.getleft().setDessinY(n.getDessinY() + 50);
        }
        if (!n.getright().issantinela()) {
            int nfd = n.getright().getNright();
            g.setColor(Color.BLACK);
            g.drawLine(n.getDessinX() + 17, n.getDessinY() + 17, n.getDessinX() + 30 * nfd + 17, n.getDessinY() + 50 + 17);
            n.getright().setDessinX(n.getDessinX() + 30 * nfd);
            n.getright().setDessinY(n.getDessinY() + 50);
        }
        g.setColor(n.getcolor());
        g.fillOval(n.getDessinX(), n.getDessinY(), 34, 34);
        g.setColor(Color.WHITE);
        g.drawString(n.getInfo().getValue() + "", 
                n.getInfo().getValue() < 10 ? n.getDessinX() + 14 : 
                        n.getInfo().getValue() < 100 ? n.getDessinX() + 10 : n.getDessinX() + 6, n.getDessinY() + 21);
        if (!n.getleft().issantinela()) {
            DesignNode(g, n.getleft());
        }
        if (!n.getright().issantinela()) {
            DesignNode(g, n.getright());
        }
    }

    public void paint(Graphics g) {
        Node n = a.getroot();
        n.setDessinX(390);
        n.setDessinY(10);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, containerWidth, containerHeight);
        if (!a.getroot().issantinela())
            DesignNode(g,n);
    }

    public boolean research(Graphics g, int val) {
        Node n = a.research(val);
        if (n != null) {
            g.setColor(Color.BLUE);
            g.drawOval(n.getDessinX() - 5, n.getDessinY() - 5, 44, 44);
            return true;
        } else {
            return false;
        }
    }
}