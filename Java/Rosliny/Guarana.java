package Rosliny;

import Main.Swiat;
import Main.Organizm;

public class Guarana extends Roslina{
    public Guarana(int x, int y, Swiat swiat)
    {
        super(x, y, 0, 0, swiat.IDGUARANA, swiat);
    }
    public Guarana(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 0, 0, swiat.IDGUARANA, swiat);
    }

    @Override public String rysowanie()
    {
        return "#b20020";
    }

    @Override public String nazwa()
    {
        return "Guarana";
    }

    @Override public void stworz_nowe_Roslina(int x, int y, Swiat sw)
    {
        Organizm org = new Guarana(x, y, true, sw);
    }

    @Override public boolean CzyDodajeSily()
    {
        return true;
    }
}
