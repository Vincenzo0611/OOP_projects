package Rosliny;

import Main.Swiat;
import Main.Organizm;

public class WilczeJagody extends Roslina{
    public WilczeJagody(int x, int y, Swiat swiat)
    {
        super(x, y, 99, 0, swiat.IDWILCZEJAGODY, swiat);
    }
    public WilczeJagody(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 99, 0, swiat.IDWILCZEJAGODY, swiat);
    }

    @Override public String rysowanie()
    {
        return "#04167a";
    }

    @Override public String nazwa()
    {
        return "Wilcze Jagody";
    }

    @Override public void stworz_nowe_Roslina(int x, int y, Swiat sw)
    {
        Organizm org = new WilczeJagody(x, y, true, sw);
    }

    @Override public int SzansaNaRozsiew()
    {
        return 100;
    }
}
