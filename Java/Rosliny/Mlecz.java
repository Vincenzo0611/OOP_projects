package Rosliny;

import Main.Swiat;
import Main.Organizm;

public class Mlecz extends Roslina{
    public Mlecz(int x, int y, Swiat swiat)
    {
        super(x, y, 0, 0, swiat.IDMLECZ, swiat);
    }
    public Mlecz(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 0, 0, swiat.IDMLECZ, swiat);
    }

    @Override public String rysowanie()
    {
        return "#eeea18";
    }

    @Override public String nazwa()
    {
        return "Mlecz";
    }

    @Override public void stworz_nowe_Roslina(int x, int y, Swiat sw)
    {
        Organizm org = new Mlecz(x, y, true, sw);
    }

    @Override public int Ileprob()
    {
        return 3;
    }
}
