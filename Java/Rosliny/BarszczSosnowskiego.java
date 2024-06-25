package Rosliny;

import Main.Swiat;
import Main.Organizm;

public class BarszczSosnowskiego extends Roslina{
    public BarszczSosnowskiego(int x, int y, Swiat swiat)
    {
        super(x, y, 10, 0, swiat.IDBARSZCZSOS, swiat);
    }
    public BarszczSosnowskiego(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 10, 0, swiat.IDBARSZCZSOS, swiat);
    }

    @Override public String rysowanie()
    {
        return "#dbd4d4";
    }

    @Override public String nazwa()
    {
        return "Barszcz Sosnowskiego";
    }

    @Override public void akcja()
    {
        if (this.GetY() != 0 && this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1) != null && this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1).GetInicjatywa() > 0 && !this.getClass().equals(this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1)))
        {
            Organizm organizm = this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1);
            this.swiat.dodajWydarzenie(this, organizm, this.swiat.KILL);
            int x = organizm.GetX();
            int y = organizm.GetY();
            this.swiat.deletefromList(organizm);
            this.swiat.SetOrganizmy(x, y, null);
        }
        if (this.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1) != null && this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1).GetInicjatywa() > 0 && !this.getClass().equals(this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1)))
        {
            Organizm organizm = this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1);
            this.swiat.dodajWydarzenie(this, organizm, this.swiat.KILL);
            int x = organizm.GetX();
            int y = organizm.GetY();
            this.swiat.deletefromList(organizm);
            this.swiat.SetOrganizmy(x, y, null);
        }
        if (this.GetX() != 0 && this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()) != null && this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()).GetInicjatywa() > 0 && !this.getClass().equals(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY())))
        {
            Organizm organizm = this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY());
            this.swiat.dodajWydarzenie(this, organizm, this.swiat.KILL);
            int x = organizm.GetX();
            int y = organizm.GetY();
            this.swiat.deletefromList(organizm);
            this.swiat.SetOrganizmy(x, y, null);
        }
        if (this.GetX() != this.swiat.Getszerokosc() - 1 && this.swiat.GetOrganizmy(this.GetX()  + 1, this.GetY()) != null && this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()).GetInicjatywa() > 0 && !this.getClass().equals(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY())))
        {
            Organizm organizm = this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY());
            this.swiat.dodajWydarzenie(this, organizm, this.swiat.KILL);
            int x = organizm.GetX();
            int y = organizm.GetY();
            this.swiat.deletefromList(organizm);
            this.swiat.SetOrganizmy(x, y, null);
        }
        if (swiat.Getuklad_planszy() == 6)
        {
            if(this.GetX() % 2 == 0){
                if (this.GetX() != this.swiat.Getszerokosc() - 1 && this.GetY() != 0 && this.swiat.GetOrganizmy(this.GetX()  + 1, this.GetY() - 1) != null && this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() - 1).GetInicjatywa() > 0 && !this.getClass().equals(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() - 1)))
                {
                    Organizm organizm = this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() - 1);
                    this.swiat.dodajWydarzenie(this, organizm, this.swiat.KILL);
                    int x = organizm.GetX();
                    int y = organizm.GetY();
                    this.swiat.deletefromList(organizm);
                    this.swiat.SetOrganizmy(x, y, null);
                }
                if (this.GetX() != 0 && this.GetY() != 0 && this.swiat.GetOrganizmy(this.GetX()  - 1, this.GetY() - 1) != null && this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() - 1).GetInicjatywa() > 0 && !this.getClass().equals(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() - 1)))
                {
                    Organizm organizm = this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() - 1);
                    this.swiat.dodajWydarzenie(this, organizm, this.swiat.KILL);
                    int x = organizm.GetX();
                    int y = organizm.GetY();
                    this.swiat.deletefromList(organizm);
                    this.swiat.SetOrganizmy(x, y, null);
                }
            }
            else{
                if (this.GetX() != this.swiat.Getszerokosc() - 1 && this.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(this.GetX()  + 1, this.GetY() + 1) != null && this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() + 1).GetInicjatywa() > 0 && !this.getClass().equals(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() + 1)))
                {
                    Organizm organizm = this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() + 1);
                    this.swiat.dodajWydarzenie(this, organizm, this.swiat.KILL);
                    int x = organizm.GetX();
                    int y = organizm.GetY();
                    this.swiat.deletefromList(organizm);
                    this.swiat.SetOrganizmy(x, y, null);
                }
                if (this.GetX() != 0 && this.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(this.GetX()  - 1, this.GetY() + 1) != null && this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() + 1).GetInicjatywa() > 0 && !this.getClass().equals(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() + 1)))
                {
                    Organizm organizm = this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() + 1);
                    this.swiat.dodajWydarzenie(this, organizm, this.swiat.KILL);
                    int x = organizm.GetX();
                    int y = organizm.GetY();
                    this.swiat.deletefromList(organizm);
                    this.swiat.SetOrganizmy(x, y, null);
                }
            }
        }

        super.akcja();
    }

    @Override public void stworz_nowe_Roslina(int x, int y, Swiat sw)
    {
        Organizm org = new BarszczSosnowskiego(x, y, true, sw);
    }

    @Override public boolean CzyNieZabija()
    {
        return false;
    }


    @Override public int SzansaNaRozsiew()
    {
        return 100;
    }

}
