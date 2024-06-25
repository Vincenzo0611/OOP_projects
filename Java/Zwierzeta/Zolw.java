package Zwierzeta;

import Main.Swiat;
import Main.Organizm;

public class Zolw extends Zwierze {
    public Zolw(int x, int y, Swiat swiat)
    {
        super(x, y, 2, 1, swiat.IDZOLW, swiat);
    }

    public Zolw(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 2, 1, swiat.IDZOLW, swiat);
    }

    @Override public String rysowanie()
    {
        return "#216d33";
    }

    @Override public String nazwa()
    {
        return "Zolw";
    }

    @Override public void stworz_nowe_zwierze(int x, int y, Swiat sw)
    {
        Organizm org = new Zolw(x, y, true, sw);
    }

    @Override public boolean CzyRuszac()
    {
        int random = this.swiat.rand.nextInt(4);

        if (random == 3)
            return true;
        return false;
    }

    @Override public int CzyOdbijaAtak(Organizm atakujacy)
    {
        if (atakujacy.GetSila() < 5)
            return 2;
        return 0;
    }
}
