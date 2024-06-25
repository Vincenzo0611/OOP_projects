package Zwierzeta;

import Main.Swiat;
import Main.Organizm;

public class Owca extends Zwierze{
    public Owca(int x, int y, Swiat swiat)
    {
        super(x, y, 4, 4, swiat.IDOWCA, swiat);
    }

    public Owca(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 4, 4, swiat.IDOWCA, swiat);
    }

    @Override public String rysowanie()
    {
        return "#1c1a1a";
    }

    @Override public String nazwa()
    {
        return "Owca";
    }

    @Override public void stworz_nowe_zwierze(int x, int y, Swiat sw)
    {
        Organizm org = new Owca(x, y, true, sw);
    }
}
