package Zwierzeta;

import Main.Swiat;
import Main.Organizm;

public class Lis extends Zwierze{
    public Lis(int x, int y, Swiat swiat)
    {
        super(x, y, 3, 7, swiat.IDLIS, swiat);
    }

    public Lis(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 3, 7, swiat.IDLIS, swiat);
    }

    @Override public String rysowanie()
    {
        return "#fd890e";
    }

    @Override public String nazwa()
    {
        return "Lis";
    }

    @Override public void stworz_nowe_zwierze(int x, int y, Swiat sw)
    {
        Organizm org = new Lis(x, y, true, sw);
    }

    @Override public boolean CzyWech(Organizm organizm)
    {
        if (this.GetSila() >= organizm.GetSila())
            return true;
        return false;
    }
}
