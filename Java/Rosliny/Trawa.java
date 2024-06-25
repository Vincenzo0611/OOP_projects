package Rosliny;

import Main.Swiat;
import Main.Organizm;

public class Trawa extends Roslina{

    public Trawa(int x, int y, Swiat swiat)
    {
        super(x, y, 0, 0, swiat.IDTRAWA, swiat);
    }
    public Trawa(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 0, 0, swiat.IDTRAWA, swiat);
    }

    @Override public String rysowanie()
    {
        return "#00E277";
    }

    @Override public String nazwa()
    {
        return "Trawa";
    }

    @Override public void stworz_nowe_Roslina(int x, int y, Swiat sw)
    {
        Organizm org = new Trawa(x, y, true, sw);
    }

}
