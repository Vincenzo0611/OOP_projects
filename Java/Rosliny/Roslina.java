package Rosliny;

import Main.Organizm;
import Main.Swiat;

public abstract class Roslina extends Organizm
{
    public Roslina(int x, int y, int sila, int inicjatywa, int id, Swiat swiat)
    {
        super(x, y, sila, inicjatywa, id, swiat);
    }
    public Roslina(int x, int y, boolean dz, int sila, int inicjatywa, int id, Swiat swiat)
    {
        super(x, y, sila, inicjatywa, id, dz, swiat);
    }

    public abstract String rysowanie();
    public abstract String nazwa();
    public abstract void stworz_nowe_Roslina(int x, int y, Swiat sw);

    @Override public void akcja()
    {
        int q = 0;
        for (int x = 0; x < this.Ileprob(); x++)
        {
            int szansa = this.swiat.rand.nextInt(this.SzansaNaRozsiew());
            q = 0;
            while (q == 0)
            {
                int random = this.swiat.rand.nextInt(swiat.Getuklad_planszy());
                if (szansa != 0)
                {
                    q = 1;
                    random = swiat.BRAK;
                }

                if (random == this.swiat.GORA && GetY() != 0)
                {
                    q = 1;
                    if (this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1) == null)
                    {
                        this.swiat.dodajWydarzenie(this, null, this.swiat.ROZPRZESTRZENIANIE);
                        this.zasadzGora();
                    }
                }
                else if (random == this.swiat.DOL && GetY() != this.swiat.Getwyskosc() - 1)
                {
                    q = 1;
                    if (this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1) == null)
                    {
                        this.swiat.dodajWydarzenie(this, null, this.swiat.ROZPRZESTRZENIANIE);
                        this.zasadzDol();
                    }
                }
                else if (random == this.swiat.PRAWO && GetX() != this.swiat.Getszerokosc() - 1)
                {
                    q = 1;
                    if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()) == null)
                    {
                        this.swiat.dodajWydarzenie(this, null, this.swiat.ROZPRZESTRZENIANIE);
                        this.zasadzPrawo();
                    }
                }
                else if (random == this.swiat.LEWO && GetX() != 0)
                {
                    q = 1;
                    if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()) == null)
                    {
                        this.swiat.dodajWydarzenie(this, null, this.swiat.ROZPRZESTRZENIANIE);
                        this.zasadzLewo();
                    }
                }
                else if (random == this.swiat.LEWOG)
                {
                    if(this.GetX() % 2 == 0 && GetX() != 0 && GetY() != 0) {
                        q = 1;
                        if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() - 1) == null) {
                            this.swiat.dodajWydarzenie(this, null, this.swiat.ROZPRZESTRZENIANIE);
                            this.zasadzLewoG();
                        }
                    }
                    else if (this.GetX() % 2 != 0 && GetX() != 0 && GetY() != this.swiat.Getwyskosc() - 1){
                        q = 1;
                        if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() + 1) == null) {
                            this.swiat.dodajWydarzenie(this, null, this.swiat.ROZPRZESTRZENIANIE);
                            this.zasadzLewoG();
                        }
                    }
                }
                else if (random == this.swiat.PRAWOG)
                {
                    if(this.GetX() % 2 == 0 && GetX() != this.swiat.Getszerokosc() - 1 && GetY() != 0) {
                        q = 1;
                        if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() - 1) == null) {
                            this.swiat.dodajWydarzenie(this, null, this.swiat.ROZPRZESTRZENIANIE);
                            this.zasadzPrawoG();
                        }
                    }
                    else if (this.GetX() % 2 != 0 && GetX() != this.swiat.Getszerokosc() - 1 && GetY() != this.swiat.Getwyskosc() - 1){
                        q = 1;
                        if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() + 1) == null) {
                            this.swiat.dodajWydarzenie(this, null, this.swiat.ROZPRZESTRZENIANIE);
                            this.zasadzPrawoG();
                        }
                    }
                }
            }
        }
    }

    @Override public void kolizja(Organizm organizm, int kierunek)
    {

    }

    public int Ileprob()
    {
        return 1;
    }

    public int SzansaNaRozsiew()
    {
        return 30;
    }

    public void zasadzGora()
    {
        stworz_nowe_Roslina(this.GetX(), this.GetY() - 1, this.swiat);
    }
    public void zasadzDol()
    {
        stworz_nowe_Roslina(this.GetX(), this.GetY() + 1, this.swiat);
    }
    public void zasadzLewo()
    {
        stworz_nowe_Roslina(this.GetX() - 1, this.GetY(), this.swiat);
    }
    public void zasadzPrawo()
    {
        stworz_nowe_Roslina(this.GetX() + 1, this.GetY(), this.swiat);
    }
    public void zasadzLewoG()
    {
        if(this.GetX() % 2 == 0) {
            stworz_nowe_Roslina(this.GetX() - 1, this.GetY() - 1, this.swiat);
        }
        else {
            stworz_nowe_Roslina(this.GetX() - 1, this.GetY() + 1, this.swiat);
        }
    }
    public void zasadzPrawoG()
    {
        if(this.GetX() % 2 == 0) {
            stworz_nowe_Roslina(this.GetX() + 1, this.GetY() - 1, this.swiat);
        }
        else {
            stworz_nowe_Roslina(this.GetX() + 1, this.GetY() + 1, this.swiat);
        }
    }


}