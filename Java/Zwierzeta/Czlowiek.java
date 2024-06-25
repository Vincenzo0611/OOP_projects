package Zwierzeta;

import Main.Swiat;
import Main.Organizm;
public class Czlowiek extends Zwierze{
    public Czlowiek(int x, int y, Swiat swiat)
    {
        super(x, y, 5, 4, swiat.IDCZLOWIEK, swiat);
    }
    public Czlowiek(int x, int y, boolean dz, Swiat swiat)
    {
        super(x, y, dz, 5, 4, swiat.IDCZLOWIEK, swiat);
    }

    @Override public String rysowanie()
    {
        return "#18ebd5";
    }

    @Override public String nazwa()
    {
        return "Czlowiek";
    }

    @Override public void akcja()
    {
        if(swiat.Getuklad_planszy() == 4) {
            if (this.swiat.Getkierunek() == this.swiat.GORA && GetY() != 0) {
                if (this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1) == null) {
                    this.idzGora();
                } else {
                    this.kolizja(this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1), this.swiat.GORA);
                }
            } else if (this.swiat.Getkierunek() == this.swiat.DOL && GetY() != this.swiat.Getwyskosc() - 1) {
                if (this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1) == null) {
                    this.idzDol();
                } else {
                    this.kolizja(this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1), this.swiat.DOL);
                }
            } else if (this.swiat.Getkierunek() == this.swiat.PRAWO && GetX() != this.swiat.Getszerokosc() - 1) {
                if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()) == null) {
                    this.idzPrawo();
                } else {
                    this.kolizja(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()), this.swiat.PRAWO);
                }
            } else if (this.swiat.Getkierunek() == this.swiat.LEWO && GetX() != 0) {
                if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()) == null) {
                    this.idzLewo();
                } else {
                    this.kolizja(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()), this.swiat.LEWO);
                }
            }
        }
        else {
            if (this.swiat.Getkierunek() == this.swiat.GORA && GetY() != 0) {
                if (this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1) == null) {
                    this.idzGora();
                } else {
                    this.kolizja(this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1), this.swiat.GORA);
                }
            } else if (this.swiat.Getkierunek() == this.swiat.DOL && GetY() != this.swiat.Getwyskosc() - 1) {
                if (this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1) == null) {
                    this.idzDol();
                } else {
                    this.kolizja(this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1), this.swiat.DOL);
                }
            } else if(this.GetX() % 2 == 0)
            {
                if (this.swiat.Getkierunek() == this.swiat.PRAWO && GetX() != this.swiat.Getszerokosc() - 1) {
                    if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()) == null) {
                        this.idzPrawo();
                    } else {
                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()), this.swiat.PRAWO);
                    }
                } else if (this.swiat.Getkierunek() == this.swiat.LEWO && GetX() != 0) {
                    if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()) == null) {
                        this.idzLewo();
                    } else {
                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()), this.swiat.LEWO);
                    }
                }
                else if(this.swiat.Getkierunek() == this.swiat.LEWOG && GetX() != 0 && GetY() != 0){
                    if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() - 1) == null) {
                        this.idzLewoG();
                    } else {
                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() - 1), this.swiat.LEWOG);
                    }
                }
                else if(this.swiat.Getkierunek() == this.swiat.PRAWOG && GetX() != this.swiat.Getszerokosc() - 1 && GetY() != 0){
                    if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() - 1) == null) {
                        this.idzPrawoG();
                    } else {
                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() - 1), this.swiat.PRAWOG);
                    }
                }
            }
            else{
                if (this.swiat.Getkierunek() == this.swiat.PRAWO && GetX() != this.swiat.Getszerokosc() - 1 && GetY() != this.swiat.Getwyskosc() - 1) {
                    if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() + 1) == null) {
                        this.idzPrawoG();
                    } else {
                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()+1), this.swiat.PRAWOG);
                    }
                } else if (this.swiat.Getkierunek() == this.swiat.LEWO && GetX() != 0 && GetY() != this.swiat.Getwyskosc() - 1) {
                    if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() + 1) == null) {
                        this.idzLewoG();
                    } else {
                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() + 1), this.swiat.LEWOG);
                    }
                }
                else if(this.swiat.Getkierunek() == this.swiat.LEWOG && GetX() != 0 && GetY() != 0){
                    if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()) == null) {
                        this.idzLewo();
                    } else {
                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()), this.swiat.LEWO);
                    }
                }
                else if(this.swiat.Getkierunek() == this.swiat.PRAWOG && GetX() != this.swiat.Getszerokosc() - 1 && GetY() != 0){
                    if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()) == null) {
                        this.idzPrawo();
                    } else {
                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()), this.swiat.PRAWO);
                    }
                }
            }
        }
    }

    @Override public int CzyOdbijaAtak(Organizm atakujacy)
     {
        if (this.swiat.CzyAktywna())
        return 1;
        return 0;
    }

    @Override public void stworz_nowe_zwierze(int x, int y, Swiat sw)
    {

    }
}
