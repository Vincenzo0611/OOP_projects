package Main;

import javax.swing.*;
import java.awt.*;

public class HexButton extends JButton {

    private int a;
    private Color kolor;
    public HexButton(int a, Color c) {
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(100, 100));
        this.a = a;
        this.kolor = c;
    }

    public void setFillColor(Color color) {
        this.kolor = color;
        repaint(); // Odświeżenie przycisku
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon hex = new Polygon();
        hex.addPoint(a/2, 0);
        hex.addPoint(a + a/2, 0);
        hex.addPoint(a*2 - 1, a);
        hex.addPoint(a + a/2, a*2 - 1);
        hex.addPoint(a/2, a*2 - 1);
        hex.addPoint(0, a);
        g.drawPolygon(hex);
        g.setColor(this.kolor);
        g.fillPolygon(hex);
    }
}
