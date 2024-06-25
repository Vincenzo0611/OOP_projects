package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadActionListener implements ActionListener {
    private Przyciski p;
    private Swiat swiat;

    public LoadActionListener(Swiat swiat, Przyciski przyciski) {
        this.p = przyciski;
        this.swiat = swiat;
    }

    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Czy chcesz wczytac gre?", "Load", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            swiat.load();
            p.newButtons(swiat);
            swiat.rysujSwiat(p);
        }
    }
}