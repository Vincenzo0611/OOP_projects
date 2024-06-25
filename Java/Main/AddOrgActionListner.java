package Main;

import Rosliny.*;
import Zwierzeta.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOrgActionListner implements ActionListener {

    private JButton przycisk;
    private Przyciski p;
    private Swiat swiat;

    private int x;
    private int y;

    public AddOrgActionListner(Swiat swiat, Przyciski przyciski, int x, int y, JButton przycisk) {
        this.p = przyciski;
        this.swiat = swiat;
        this.x = x;
        this.y = y;
        this.przycisk = przycisk;
    }

    public AddOrgActionListner(Swiat swiat, Przyciski przyciski, int x, int y, HexButton przycisk) {
        this.p = przyciski;
        this.swiat = swiat;
        this.x = x;
        this.y = y;
        this.przycisk = przycisk;
    }

    public void actionPerformed(ActionEvent e) {

        Organizm org = swiat.GetOrganizmy(x, y);

        if(org == null) {
            String[] options = {"Owca", "Wilk", "Lis", "Zolw", "Antylopa", "Trawa",
                    "Mlecz", "Guarana", "Wilcze Jagody", "Barszcz Sosnowskiego", "Anuluj"};

            JComboBox<String> comboBox = new JComboBox<>(options);

            JOptionPane.showMessageDialog(p, comboBox, "Wybierz Organizm do stworzenia", JOptionPane.QUESTION_MESSAGE);

            String selectedOption = (String) comboBox.getSelectedItem();
            if (selectedOption == "Wilk") {
                org = new Wilk(x, y, swiat);
                if(swiat.Getuklad_planszy() == 4) {
                    przycisk.setBackground(Color.decode(org.rysowanie()));
                }
                else {
                    HexButton hex = (HexButton)przycisk;
                    hex.setFillColor(Color.decode(org.rysowanie()));
                }
            }
            else if (selectedOption == "Owca") {
                org = new Owca(x, y, swiat);
                if(swiat.Getuklad_planszy() == 4) {
                    przycisk.setBackground(Color.decode(org.rysowanie()));
                }
                else {
                    HexButton hex = (HexButton)przycisk;
                    hex.setFillColor(Color.decode(org.rysowanie()));
                }
            }
            else if (selectedOption == "Lis") {
                org = new Lis(x, y, swiat);
                if(swiat.Getuklad_planszy() == 4) {
                    przycisk.setBackground(Color.decode(org.rysowanie()));
                }
                else {
                    HexButton hex = (HexButton)przycisk;
                    hex.setFillColor(Color.decode(org.rysowanie()));
                }
            }
            else if (selectedOption == "Zolw") {
                org = new Zolw(x, y, swiat);
                if(swiat.Getuklad_planszy() == 4) {
                    przycisk.setBackground(Color.decode(org.rysowanie()));
                }
                else {
                    HexButton hex = (HexButton)przycisk;
                    hex.setFillColor(Color.decode(org.rysowanie()));
                }
            }
            else if (selectedOption == "Antylopa") {
                org = new Antylopa(x, y, swiat);
                if(swiat.Getuklad_planszy() == 4) {
                    przycisk.setBackground(Color.decode(org.rysowanie()));
                }
                else {
                    HexButton hex = (HexButton)przycisk;
                    hex.setFillColor(Color.decode(org.rysowanie()));
                }
            }
            else if (selectedOption == "Trawa") {
                org = new Trawa(x, y, swiat);
                if(swiat.Getuklad_planszy() == 4) {
                    przycisk.setBackground(Color.decode(org.rysowanie()));
                }
                else {
                    HexButton hex = (HexButton)przycisk;
                    hex.setFillColor(Color.decode(org.rysowanie()));
                }
            }
            else if (selectedOption == "Mlecz") {
                org = new Mlecz(x, y, swiat);
                if(swiat.Getuklad_planszy() == 4) {
                    przycisk.setBackground(Color.decode(org.rysowanie()));
                }
                else {
                    HexButton hex = (HexButton)przycisk;
                    hex.setFillColor(Color.decode(org.rysowanie()));
                }
            }
            else if (selectedOption == "Guarana") {
                org = new Guarana(x, y, swiat);
                if(swiat.Getuklad_planszy() == 4) {
                    przycisk.setBackground(Color.decode(org.rysowanie()));
                }
                else {
                    HexButton hex = (HexButton)przycisk;
                    hex.setFillColor(Color.decode(org.rysowanie()));
                }
            }
            else if (selectedOption == "Wilcze Jagody") {
                org = new WilczeJagody(x, y, swiat);
                if(swiat.Getuklad_planszy() == 4) {
                    przycisk.setBackground(Color.decode(org.rysowanie()));
                }
                else {
                    HexButton hex = (HexButton)przycisk;
                    hex.setFillColor(Color.decode(org.rysowanie()));
                }
            }
            else if (selectedOption == "Barszcz Sosnowskiego") {
                org = new BarszczSosnowskiego(x, y, swiat);
                if(swiat.Getuklad_planszy() == 4) {
                    przycisk.setBackground(Color.decode(org.rysowanie()));
                }
                else {
                    HexButton hex = (HexButton)przycisk;
                    hex.setFillColor(Color.decode(org.rysowanie()));
                }
            }
        }
        else{
            String message = "To jest ";
            message += org.nazwa();
            message += " z sila rowna ";
            message += Integer.toString(org.GetSila());
            JOptionPane.showMessageDialog(p, message, "Organizm", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
