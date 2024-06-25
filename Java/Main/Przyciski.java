package Main;

import Zwierzeta.Czlowiek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Przyciski extends JFrame {

    private JPanel buttonPanel;
    private JPanel legendPanel;

    private JPanel komunikaty;
    public Przyciski(Swiat swiat) {
        setTitle("Swiat Vincenzo Piras 193218");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // Panel with buttons
        addButtons(swiat);
        add(buttonPanel);

        legendPanel = new JPanel(new GridLayout(2, 1));

        JPanel legenda = new JPanel(new GridLayout(6, 6));

        JPanel squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#18ebd5"));
        JLabel label = new JLabel("- Czlowiek");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#1c1a1a"));
        label = new JLabel("- Owca");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#6a6565"));
        label = new JLabel("- Wilk");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#fd890e"));
        label = new JLabel("- Lis");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#216d33"));
        label = new JLabel("- Zolw");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#a6730c"));
        label = new JLabel("- Antylopa");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#00E277"));
        label = new JLabel("- Trawa");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#eeea18"));
        label = new JLabel("- Mlecz");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#b20020"));
        label = new JLabel("- Guarana");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#04167a"));
        label = new JLabel("- Wilcze Jagody");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.decode("#dbd4d4"));
        label = new JLabel("- Barszcz Sosnowskiego");
        legenda.add(squarePanel);
        legenda.add(label);

        squarePanel = new JPanel();
        squarePanel.setBackground(Color.WHITE);

        legenda.add(squarePanel);

        JButton button = new JButton("Nastepna Tura");

        button.setPreferredSize(new Dimension(80, 60)); // Ustawienie niestandardowych wymiarów
        button.setBackground(Color.decode("#FFFFFF"));
        button.setFocusable(false);

        button.addActionListener(new NextActionListener(swiat, this));

        legenda.add(button);

        legenda.add(squarePanel);

        label = new JLabel("w - ruch do gory");
        legenda.add(label);
        label = new JLabel("s - ruch do dolu");
        legenda.add(label);
        label = new JLabel("a - ruch w lewo");
        legenda.add(label);
        label = new JLabel("d - ruch w prawo");
        legenda.add(label);
        label = new JLabel("f - konczy gre");
        legenda.add(label);
        label = new JLabel("spacja - umiejetnosc");
        legenda.add(label);
        label = new JLabel("a - lewo-dol dla hex");
        legenda.add(label);
        label = new JLabel("d - prawo-dol dla hex");
        legenda.add(label);
        label = new JLabel("q - lewo-gora dla hex");
        legenda.add(label);
        label = new JLabel("e - prawo-gora dla hex");
        legenda.add(label);


        button = new JButton("Save");

        button.setPreferredSize(new Dimension(80, 60)); // Ustawienie niestandardowych wymiarów
        button.setBackground(Color.decode("#FFFFFF"));
        button.setFocusable(false);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Czy chcesz nadpisac save?", "Save", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    swiat.save();
                }
            }
        });

        legenda.add(button);

        button = new JButton("Load");

        button.setPreferredSize(new Dimension(80, 60)); // Ustawienie niestandardowych wymiarów
        button.setBackground(Color.decode("#FFFFFF"));
        button.setFocusable(false);

        button.addActionListener(new LoadActionListener(swiat, this));

        legenda.add(button);


        legendPanel.add(legenda);

        komunikaty = new JPanel(new GridLayout(40, 3));

        for (int i = 0; i < 120; i++) {
            squarePanel = new JPanel();
            squarePanel.setBackground(Color.WHITE);
            komunikaty.add(squarePanel);
        }

        legendPanel.add(komunikaty);

        add(legendPanel);

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        setFocusable(true);
        requestFocus();
    }

    private void addButtons(Swiat swiat) {
        if(swiat.Getuklad_planszy() == 4) {
            buttonPanel = new JPanel(new GridLayout(swiat.Getwyskosc(), swiat.Getszerokosc()));
            for (int i = 0; i < swiat.Getwyskosc(); i++) {
                for (int j = 0; j < swiat.Getszerokosc(); j++) {
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(80, 60)); // Ustawienie niestandardowych wymiarów

                    Organizm org = swiat.GetOrganizmy(j, i);

                    if (org != null) {
                        button.setBackground(Color.decode(org.rysowanie()));
                    } else {
                        button.setBackground(Color.decode("#FFFFFF"));
                    }

                    button.setFocusable(false);

                    button.addActionListener(new AddOrgActionListner(swiat, this, j, i, button));

                    buttonPanel.add(button);
                }
            }
        }
        else{
            buttonPanel = new JPanel();
            buttonPanel.setLayout(null);
            int a, a1 ,a2;
            if(swiat.Getszerokosc() % 2 == 0){
                a1 = 868 / (swiat.Getszerokosc() + swiat.Getszerokosc()/2);
            }
            else{
                a1 = 940 / (swiat.Getszerokosc() + (swiat.Getszerokosc()+1)/2);
            }
            a2 = (int)(500/(swiat.Getwyskosc() + 0.5 ));

            if(a1 < a2) {
                a = a1;
            }
            else{
                a = a2;
            }

            for (int i = 0; i < swiat.Getwyskosc(); i++) {
                for (int j = 0; j < swiat.Getszerokosc(); j++) {
                    Organizm org = swiat.GetOrganizmy(j, i);

                    Color kolor;

                    if (org != null) {
                        kolor = Color.decode(org.rysowanie());
                    } else {
                        kolor = Color.decode("#FFFFFF");
                    }
                    HexButton hex = new HexButton(a, kolor);
                    if(j % 2 == 0) {
                        hex.setBounds((int) (1.5 * a * j), i * 2 * a, 2 * a, 2 * a);
                    }
                    else {
                        hex.setBounds((int) (1.5 * a * j), i * 2 * a + a, 2 * a, 2 * a);
                    }

                    hex.setFocusable(false);

                    hex.addActionListener(new AddOrgActionListner(swiat, this, j, i, hex));
                    buttonPanel.add(hex);
                }
            }

        }
    }

    public void updateButtons(Swiat swiat) {
        for (int i = 0; i < swiat.Getwyskosc(); i++) {
            for(int j = 0; j < swiat.Getszerokosc(); j++) {
                if(swiat.Getuklad_planszy() == 4) {
                    JButton button = (JButton) buttonPanel.getComponent(i * swiat.Getszerokosc() + j);
                    Organizm org = swiat.GetOrganizmy(j, i);

                    if (org != null) {
                        button.setBackground(Color.decode(org.rysowanie()));
                    } else {
                        button.setBackground(Color.decode("#FFFFFF"));
                    }
                }
                else {
                    HexButton button = (HexButton) buttonPanel.getComponent(i * swiat.Getszerokosc() + j);

                    Organizm org = swiat.GetOrganizmy(j, i);
                    if (org != null) {
                        button.setFillColor(Color.decode(org.rysowanie()));
                    } else {
                        button.setFillColor(Color.decode("#FFFFFF"));
                    }
                }





            }
        }


        legendPanel.remove(1);

        komunikaty.removeAll();

        for (int i = 0; i < 120; i++) {
            if(i == 0 && swiat.GetKomunikat() != "") {
                JLabel label = new JLabel(swiat.GetKomunikat());
                swiat.Usunkomunikat();
                komunikaty.add(label);
            }
            else if(!swiat.CzypusteWydarzenia()) {
                JLabel label = new JLabel(swiat.wezWydarzenie());
                komunikaty.add(label);
            }
            else {
                JPanel squarePanel = new JPanel();
                squarePanel.setBackground(Color.decode("#FFFFFF"));
                komunikaty.add(squarePanel);
            }
        }

        legendPanel.add(komunikaty);
        revalidate();
        repaint();
    }

    public void newButtons(Swiat swiat) {
        getContentPane().removeAll();
        buttonPanel.removeAll();
        if(swiat.Getuklad_planszy() == 4) {
            buttonPanel = new JPanel(new GridLayout(swiat.Getwyskosc(), swiat.Getszerokosc()));
            for (int i = 0; i < swiat.Getwyskosc(); i++) {
                for (int j = 0; j < swiat.Getszerokosc(); j++) {
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(80, 60)); // Ustawienie niestandardowych wymiarów

                    Organizm org = swiat.GetOrganizmy(j, i);

                    if (org != null) {
                        button.setBackground(Color.decode(org.rysowanie()));
                    } else {
                        button.setBackground(Color.decode("#FFFFFF"));
                    }

                    button.setFocusable(false);

                    button.addActionListener(new AddOrgActionListner(swiat, this, j, i, button));

                    buttonPanel.add(button);
                }
            }
        }
        else {
            buttonPanel = new JPanel();
            buttonPanel.setLayout(null);
            int a, a1, a2;
            if (swiat.Getszerokosc() % 2 == 0) {
                a1 = 868 / (swiat.Getszerokosc() + swiat.Getszerokosc() / 2);
            } else {
                a1 = 940 / (swiat.Getszerokosc() + (swiat.Getszerokosc() + 1) / 2);
            }
            a2 = (int) (500 / (swiat.Getwyskosc() + 0.5));

            if (a1 < a2) {
                a = a1;
            } else {
                a = a2;
            }

            for (int i = 0; i < swiat.Getwyskosc(); i++) {
                for (int j = 0; j < swiat.Getszerokosc(); j++) {
                    Organizm org = swiat.GetOrganizmy(j, i);

                    Color kolor;

                    if (org != null) {
                        kolor = Color.decode(org.rysowanie());
                    } else {
                        kolor = Color.decode("#FFFFFF");
                    }
                    HexButton hex = new HexButton(a, kolor);
                    if (j % 2 == 0) {
                        hex.setBounds((int) (1.5 * a * j), i * 2 * a, 2 * a, 2 * a);
                    } else {
                        hex.setBounds((int) (1.5 * a * j), i * 2 * a + a, 2 * a, 2 * a);
                    }

                    hex.setFocusable(false);

                    hex.addActionListener(new AddOrgActionListner(swiat, this, j, i, hex));
                    buttonPanel.add(hex);
                }
            }
        }
        getContentPane().add(buttonPanel);
        getContentPane().add(legendPanel);
        revalidate();
        repaint();
    }
}