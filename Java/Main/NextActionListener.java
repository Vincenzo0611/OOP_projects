package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextActionListener implements ActionListener {
    private Przyciski p;
    private Swiat swiat;

    public NextActionListener(Swiat swiat, Przyciski przyciski) {
        this.p = przyciski;
        this.swiat = swiat;
    }

    public void actionPerformed(ActionEvent e) {
        swiat.Setkierunek(swiat.BRAK);
        swiat.wykonajTure();
        swiat.rysujSwiat(p);
    }
}