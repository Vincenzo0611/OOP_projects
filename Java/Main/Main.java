package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        int option = JOptionPane.showOptionDialog(
                null,
                "Choose the input values:",
                "Input Values",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"4", "6"}, // The options to choose from
                "4" // The default selection
        );
        int uk;
        if (option == 0) {
            // Option "4x4" selected
            uk = 4;
        } else if (option == 1) {
            // Option "6x6" selected
            uk = 6;
        } else {
            // Dialog closed or canceled
            return;
        }
        IntegerInputWindow inputWindow = new IntegerInputWindow();
        inputWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                int x = inputWindow.getNumber1();
                int y = inputWindow.getNumber2();


                Swiat sw = new Swiat(x, y, uk);

                Przyciski frame = new Przyciski(sw);
                frame.setVisible(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        // Nie używamy tego zdarzenia w tym przypadku
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        char ch = e.getKeyChar();

                        if(ch == 'w')
                        {
                            sw.Setkierunek(sw.GORA);
                            sw.wykonajTure();
                            sw.rysujSwiat(frame);
                        }
                        else if (ch == 's')
                        {
                            sw.Setkierunek(sw.DOL);
                            sw.wykonajTure();
                            sw.rysujSwiat(frame);
                        }
                        else if (ch == 'a')
                        {
                            sw.Setkierunek(sw.LEWO);
                            sw.wykonajTure();
                            sw.rysujSwiat(frame);
                        }
                        else if (ch == 'd')
                        {
                            sw.Setkierunek(sw.PRAWO);
                            sw.wykonajTure();
                            sw.rysujSwiat(frame);
                        }
                        else if (ch == 'q' && sw.Getuklad_planszy() == 6)
                        {
                            sw.Setkierunek(sw.LEWOG);
                            sw.wykonajTure();
                            sw.rysujSwiat(frame);
                        }
                        else if (ch == 'e' && sw.Getuklad_planszy() == 6)
                        {
                            sw.Setkierunek(sw.PRAWOG);
                            sw.wykonajTure();
                            sw.rysujSwiat(frame);
                        }
                        else if (ch == ' ')
                        {
                            sw.Setkierunek(sw.BRAK);
                            sw.umiejetnosc();
                            sw.wykonajTure();
                            sw.rysujSwiat(frame);
                        }
                        else if(ch == 'f')
                        {
                            System.exit(0);
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        // Nie używamy tego zdarzenia w tym przypadku
                    }
                });
            }
        });
    }
}