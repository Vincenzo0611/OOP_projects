package Zwierzeta;

import Main.Swiat;
import Main.Organizm;


public class Antylopa extends Zwierze{
    public Antylopa(int x, int y, Swiat swiat)
    {
        super(x, y, 4, 4, swiat.IDANTYLOPA, swiat);
    }

    public Antylopa(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 4, 4, swiat.IDANTYLOPA, swiat);
    }

    @Override public String rysowanie()
    {
        return "#a6730c";
    }

    @Override public String nazwa()
    {
        return "Antylopa";
    }

    @Override public void kolizja(Organizm organizm, int kierunek)
    {
        if (this.getClass().equals(organizm.getClass()))
        {
            super.kolizja(organizm, kierunek);
        }
	    else
        {
            int random = this.swiat.rand.nextInt(2);
            if (random == 0)
            {
                if (organizm.GetY() != 0 && this.swiat.GetOrganizmy(organizm.GetX(), organizm.GetY() - 1) == null)
                {
                    this.swiat.dodajWydarzenie(this, organizm, this.swiat.UCIEKA);
                    this.idzXY(organizm.GetX(), organizm.GetY() - 1);
                }
			    else if (organizm.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(organizm.GetX(), organizm.GetY() + 1) == null)
                {
                    this.swiat.dodajWydarzenie(this, organizm, this.swiat.UCIEKA);
                    this.idzXY(organizm.GetX(), organizm.GetY() + 1);
                }
			    else if (organizm.GetX() != 0 && this.swiat.GetOrganizmy(organizm.GetX() - 1, organizm.GetY()) == null)
                {
                    this.swiat.dodajWydarzenie(this, organizm, this.swiat.UCIEKA);
                    this.idzXY(organizm.GetX() - 1, organizm.GetY());
                }
			    else if (organizm.GetX() != this.swiat.Getszerokosc() - 1 && this.swiat.GetOrganizmy(organizm.GetX() + 1, organizm.GetY()) == null)
                {
                    this.swiat.dodajWydarzenie(this, organizm, this.swiat.UCIEKA);
                    this.idzXY(organizm.GetX() + 1, organizm.GetY());
                }
                else if(swiat.Getuklad_planszy() == 6)
                {
                    if(this.GetX() % 2 == 0) {
                        if (organizm.GetX() != this.swiat.Getszerokosc() - 1 && organizm.GetY() != 0 && this.swiat.GetOrganizmy(organizm.GetX() + 1, organizm.GetY() - 1) == null)
                        {
                            this.swiat.dodajWydarzenie(this, organizm, this.swiat.UCIEKA);
                            this.idzXY(organizm.GetX() + 1, organizm.GetY()- 1);
                        }
                        else if (organizm.GetX() != 0 && organizm.GetY() != 0 && this.swiat.GetOrganizmy(organizm.GetX() - 1, organizm.GetY() - 1) == null)
                        {
                            this.swiat.dodajWydarzenie(this, organizm, this.swiat.UCIEKA);
                            this.idzXY(organizm.GetX() - 1, organizm.GetY()- 1);
                        }
                    }
                    else{
                        if (organizm.GetX() != this.swiat.Getszerokosc() - 1 && organizm.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(organizm.GetX() + 1, organizm.GetY() + 1) == null)
                        {
                            this.swiat.dodajWydarzenie(this, organizm, this.swiat.UCIEKA);
                            this.idzXY(organizm.GetX() + 1, organizm.GetY()+ 1);
                        }
                        else if (organizm.GetX() != 0 && organizm.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(organizm.GetX() - 1, organizm.GetY() + 1) == null)
                        {
                            this.swiat.dodajWydarzenie(this, organizm, this.swiat.UCIEKA);
                            this.idzXY(organizm.GetX() - 1, organizm.GetY()+ 1);
                        }
                    }
                }
            }
            else
            {
               super.kolizja(organizm, kierunek);
            }
        }
    }

    @Override public void stworz_nowe_zwierze(int x, int y, Swiat sw)
    {
        Organizm org = new Antylopa(x, y, true, sw);
    }

    @Override public int ileRuchu()
    {
        return 2;
    }

    @Override public boolean CzyUcieka()
    {
        int random = this.swiat.rand.nextInt(2);
        if (random == 0)
            return true;
        return false;
    }
}
