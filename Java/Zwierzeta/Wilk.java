package Zwierzeta;

import Main.Swiat;
import Main.Organizm;

public class Wilk extends Zwierze{
    public Wilk(int x, int y, Swiat swiat)
    {
        super(x, y, 9, 5, swiat.IDWILK, swiat);
    }
    public Wilk(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 9, 5, swiat.IDWILK, swiat);
    }

    @Override public String rysowanie()
    {
        return "#6a6565";
    }

    @Override public String nazwa()
    {
        return "Wilk";
    }

    @Override public void stworz_nowe_zwierze(int x, int y, Swiat sw)
    {
        Organizm org = new Wilk(x, y, true, sw);
    }
}
