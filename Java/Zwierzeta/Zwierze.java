package Zwierzeta;

import Main.Swiat;
import Main.Organizm;

public abstract class Zwierze extends Organizm {
        public Zwierze(int x, int y, int sila, int inicjatywa, int id, Swiat swiat)
        {
                super(x, y, sila, inicjatywa, id, swiat);
        }
        public Zwierze(int x, int y, boolean dz, int sila, int inicjatywa, int id, Swiat swiat)
        {
               super(x, y, sila, inicjatywa, id, dz, swiat);
        }
        @Override public void akcja()
        {
                for (int r = 0; r < this.ileRuchu(); r++)
                {
                        if (this.swiat.GetOrganizmy(this.GetX(), this.GetY()) == null) {
                                return;
                        }
                        if (this == null) {
                                return;
                        }
                        if (!this.CzyRuszac()) {
                                return;
                        }
                        int q = 0;
                        while (q == 0)
                        {
                                int random = this.swiat.rand.nextInt(swiat.Getuklad_planszy());

                                if (random == this.swiat.GORA && GetY() != 0)
                                {
                                        q = 1;
                                        if (this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1) == null)
                                        {
                                                this.idzGora();
                                        }
                                        else if (this.CzyWech(this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1)))
                                        {
                                                this.kolizja(this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1), this.swiat.GORA);
                                        }
                                }
                                else if (random == this.swiat.DOL && GetY() != this.swiat.Getwyskosc() - 1)
                                {
                                        q = 1;
                                        if (this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1) == null)
                                        {
                                                this.idzDol();
                                        }
                                        else  if (this.CzyWech(this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1)))
                                        {
                                                this.kolizja(this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1), this.swiat.DOL);
                                        }
                                }
                                else if (random == this.swiat.PRAWO && GetX() != this.swiat.Getszerokosc() - 1)
                                {
                                        q = 1;
                                        if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()) == null)
                                        {
                                                this.idzPrawo();
                                        }
                                        else  if (this.CzyWech(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY())))
                                        {
                                                this.kolizja(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()), this.swiat.PRAWO);
                                        }
                                }
                                else if (random == this.swiat.LEWO && GetX() != 0)
                                {
                                        q = 1;
                                        if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()) == null)
                                        {
                                                this.idzLewo();
                                        }
                                        else if (this.CzyWech(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY())))
                                        {
                                                this.kolizja(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()), this.swiat.LEWO);
                                        }
                                }
                                else if (random == this.swiat.LEWOG)
                                {
                                        if(this.GetX() % 2 == 0 && GetX() != 0 && GetY() != 0) {
                                                q = 1;
                                                if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() - 1) == null) {
                                                        this.idzLewoG();
                                                } else if (this.CzyWech(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() - 1))) {
                                                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() - 1), this.swiat.LEWOG);
                                                }
                                        }
                                        else if(this.GetX() % 2 != 0 && GetX() != 0 && GetY() != this.swiat.Getwyskosc() - 1){
                                                q = 1;
                                                if (this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()+1) == null) {
                                                        this.idzLewoG();
                                                } else if (this.CzyWech(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()+1))) {
                                                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()+1), this.swiat.LEWOG);
                                                }
                                        }
                                }
                                else if (random == this.swiat.PRAWOG)
                                {
                                        if(this.GetX() % 2 == 0 && GetX() != this.swiat.Getszerokosc() - 1 && GetY() != 0) {
                                                q = 1;
                                                if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() - 1) == null) {
                                                        this.idzPrawoG();
                                                } else if (this.CzyWech(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() - 1))) {
                                                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() - 1), this.swiat.PRAWOG);
                                                }
                                        }
                                        else if(this.GetX() % 2 != 0 && GetX() != this.swiat.Getszerokosc() - 1 && GetY() != this.swiat.Getwyskosc() - 1){
                                                q = 1;
                                                if (this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()+1) == null) {
                                                        this.idzPrawoG();
                                                } else if (this.CzyWech(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()+1))) {
                                                        this.kolizja(this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()+1), this.swiat.PRAWOG);
                                                }
                                        }
                                }
                        }
                }

        }
        @Override public  void kolizja(Organizm organizm, int kierunek)
        {
                if (organizm.CzyNieZabija())
                {
                        if (this.getClass().equals(organizm.getClass()))
                        {
                                if (this.GetRundy() >= this.swiat.CZAS_ROZMNAZANIA && organizm.GetRundy() >= this.swiat.CZAS_ROZMNAZANIA)
                                {
                                        //rozmnazaj
                                        if (this.GetY() != 0 && this.swiat.GetOrganizmy(this.GetX(), this.GetY() - 1) == null)
                                        {
                                                this.WyzerujRundy();
                                                organizm.WyzerujRundy();
                                                this.swiat.dodajWydarzenie(this, organizm, this.swiat.ROZMNAZANIE);
                                                stworz_nowe_zwierze(this.GetX(), (this.GetY() - 1), this.swiat);
                                        }
                                        else if (this.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(this.GetX(), this.GetY() + 1) == null)
                                        {
                                                this.WyzerujRundy();
                                                organizm.WyzerujRundy();
                                                this.swiat.dodajWydarzenie(this, organizm, this.swiat.ROZMNAZANIE);
                                                stworz_nowe_zwierze(this.GetX(), (this.GetY() + 1), this.swiat);
                                        }
                                        else if (this.GetX() != this.swiat.Getszerokosc() - 1 && this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY()) == null)
                                        {
                                                this.WyzerujRundy();
                                                organizm.WyzerujRundy();
                                                this.swiat.dodajWydarzenie(this, organizm, this.swiat.ROZMNAZANIE);
                                                stworz_nowe_zwierze((this.GetX() + 1), this.GetY(), this.swiat);
                                        }
                                        else if (this.GetX() != 0 && this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY()) == null)
                                        {
                                                this.WyzerujRundy();
                                                organizm.WyzerujRundy();
                                                this.swiat.dodajWydarzenie(this, organizm, this.swiat.ROZMNAZANIE);
                                                stworz_nowe_zwierze((this.GetX() - 1), this.GetY(), this.swiat);
                                        }
                                        else if(this.GetX() % 2 == 0 && swiat.Getuklad_planszy() == 6) {
                                                if (this.GetX() != 0 && this.GetY() != 0 && this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() - 1) == null)
                                                {
                                                        this.WyzerujRundy();
                                                        organizm.WyzerujRundy();
                                                        this.swiat.dodajWydarzenie(this, organizm, this.swiat.ROZMNAZANIE);
                                                        stworz_nowe_zwierze((this.GetX() - 1), this.GetY() - 1, this.swiat);
                                                }
                                                else if (this.GetX() != this.swiat.Getszerokosc() - 1 && this.GetY() != 0 && this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() - 1) == null)
                                                {
                                                        this.WyzerujRundy();
                                                        organizm.WyzerujRundy();
                                                        this.swiat.dodajWydarzenie(this, organizm, this.swiat.ROZMNAZANIE);
                                                        stworz_nowe_zwierze((this.GetX() + 1), this.GetY() - 1, this.swiat);
                                                }
                                        }
                                        else if(this.GetX() % 2 != 0 && swiat.Getuklad_planszy() == 6) {
                                                if (this.GetX() != 0 && this.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(this.GetX() - 1, this.GetY() + 1) == null)
                                                {
                                                        this.WyzerujRundy();
                                                        organizm.WyzerujRundy();
                                                        this.swiat.dodajWydarzenie(this, organizm, this.swiat.ROZMNAZANIE);
                                                        stworz_nowe_zwierze((this.GetX() - 1), this.GetY() + 1, this.swiat);
                                                }
                                                else if (this.GetX() != this.swiat.Getszerokosc() - 1 && this.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(this.GetX() + 1, this.GetY() + 1) == null)
                                                {
                                                        this.WyzerujRundy();
                                                        organizm.WyzerujRundy();
                                                        this.swiat.dodajWydarzenie(this, organizm, this.swiat.ROZMNAZANIE);
                                                        stworz_nowe_zwierze((this.GetX() + 1), this.GetY() + 1, this.swiat);
                                                }
                                        }
                                }
                        }
                        else if (!organizm.CzyUcieka())
                        {
                                if (this.GetSila() < organizm.GetSila())
                                {
                                        //usun this
                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.KILL);
                                        int x = this.GetX();
                                        int y = this.GetY();
                                        this.swiat.deletefromList(this);
                                        this.swiat.SetOrganizmy(x, y, null);
                                }
                                else if (organizm.CzyOdbijaAtak(this) == 0)
                                {
                                        //usun organizm
                                        //wykonaj ruch
                                        if (organizm.CzyDodajeSily()) {
                                                this.SetSila(this.GetSila() + 3);
                                        }
                                        this.swiat.dodajWydarzenie(this, organizm, this.swiat.KILL);
                                        int x = organizm.GetX();
                                        int y = organizm.GetY();
                                        this.swiat.deletefromList(organizm);
                                        this.swiat.SetOrganizmy(x, y, null);
                                        if (kierunek == this.swiat.GORA) {
                                                this.idzGora();
                                        }
                                        else if (kierunek == this.swiat.DOL) {
                                                this.idzDol();
                                        }
                                        else if (kierunek == this.swiat.LEWO) {
                                                this.idzLewo();
                                        }
                                        else if (kierunek == this.swiat.PRAWO) {
                                                this.idzPrawo();
                                        }
                                        else if (kierunek == this.swiat.LEWOG) {
                                                this.idzLewoG();
                                        }else if (kierunek == this.swiat.PRAWOG) {
                                                this.idzPrawoG();
                                        }
                                }
                                else if (organizm.CzyOdbijaAtak(this) == 1)
                                {
                                        //umiejetnisc czlowieka
                                        int x = organizm.GetX();
                                        int y = organizm.GetY();
                                        int k = 0;
                                        while (k == 0)
                                        {
                                                int r = this.swiat.rand.nextInt(swiat.Getuklad_planszy());
                                                if (r == this.swiat.GORA && y != 0)
                                                {
                                                        if (this.swiat.GetOrganizmy(x, y - 1) == null)
                                                        {
                                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                k = 1;
                                                                this.idzXY(x, y - 1);
                                                        }
                                                        else if (this.swiat.GetOrganizmy(x, y - 1) == this)
                                                        {
                                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                k = 1;
                                                        }
                                                }
                                                else if (r == this.swiat.DOL && y != this.swiat.Getwyskosc() - 1)
                                                {

                                                        if (this.swiat.GetOrganizmy(x, y + 1) == null)
                                                        {
                                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                k = 1;
                                                                this.idzXY(x, y + 1);
                                                        }
                                                        else if (this.swiat.GetOrganizmy(x, y + 1) == this)
                                                        {
                                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                k = 1;
                                                        }
                                                }
                                                else if (r == this.swiat.PRAWO && x != this.swiat.Getszerokosc() - 1)
                                                {
                                                        if (this.swiat.GetOrganizmy(x + 1, y) == null)
                                                        {
                                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                k = 1;
                                                                this.idzXY(x + 1, y);
                                                        }
                                                        else if (this.swiat.GetOrganizmy(x + 1, y) == this)
                                                        {
                                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                k = 1;
                                                        }
                                                }
                                                else if (r == this.swiat.LEWO && x != 0)
                                                {
                                                        if (this.swiat.GetOrganizmy(x -1, y) == null)
                                                        {
                                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                k = 1;
                                                                this.idzXY(x - 1,y);
                                                        }
                                                        else if (this.swiat.GetOrganizmy(x -1, y) == this)
                                                        {
                                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                k = 1;
                                                        }
                                                }
                                                else if (r == this.swiat.LEWOG)
                                                {
                                                        if(x % 2 == 0 && x != 0 && y != 0) {
                                                                if (this.swiat.GetOrganizmy(x - 1, y - 1) == null) {
                                                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                        k = 1;
                                                                        this.idzXY(x - 1, y - 1);
                                                                } else if (this.swiat.GetOrganizmy(x - 1, y - 1) == this) {
                                                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                        k = 1;
                                                                }
                                                        }
                                                        else if(x % 2 != 0 && x != 0 && y != this.swiat.Getwyskosc() - 1){
                                                                if (this.swiat.GetOrganizmy(x - 1, y + 1) == null) {
                                                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                        k = 1;
                                                                        this.idzXY(x - 1, y + 1);
                                                                } else if (this.swiat.GetOrganizmy(x - 1, y + 1) == this) {
                                                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                        k = 1;
                                                                }
                                                        }
                                                }
                                                else if (r == this.swiat.PRAWOG)
                                                {
                                                        if(x % 2 == 0 && x != this.swiat.Getszerokosc() - 1 && y != 0) {
                                                                if (this.swiat.GetOrganizmy(x + 1, y - 1) == null) {
                                                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                        k = 1;
                                                                        this.idzXY(x + 1, y - 1);
                                                                } else if (this.swiat.GetOrganizmy(x + 1, y - 1) == this) {
                                                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                        k = 1;
                                                                }
                                                        }
                                                        else if(x % 2 != 0 && x != this.swiat.Getszerokosc() - 1 && y != this.swiat.Getwyskosc() - 1){
                                                                if (this.swiat.GetOrganizmy(x + 1, y + 1) == null) {
                                                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                        k = 1;
                                                                        this.idzXY(x + 1, y + 1);
                                                                } else if (this.swiat.GetOrganizmy(x + 1, y + 1) == this) {
                                                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);
                                                                        k = 1;
                                                                }
                                                        }
                                                }

                                        }
                                }
                                else if (organizm.CzyOdbijaAtak(this) == 2)
                                {
                                        // zolw
                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.ODBITY);

                                }
                        }
                        else
                        {
                                //ucieczka antylopy
                                boolean ucieka = false;


                                if (organizm.GetY() != 0 && this.swiat.GetOrganizmy(organizm.GetX(), organizm.GetY() - 1) == null)
                                {
                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.UCIEKA);
                                        ucieka = true;
                                        organizm.idzXY(organizm.GetX(), organizm.GetY() - 1);
                                }
                                else if (organizm.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(organizm.GetX(), organizm.GetY() + 1) == null)
                                {
                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.UCIEKA);
                                        ucieka = true;
                                        organizm.idzXY(organizm.GetX(), organizm.GetY() + 1);
                                }
                                else if (organizm.GetX() != 0 && this.swiat.GetOrganizmy(organizm.GetX() - 1, organizm.GetY()) == null)
                                {
                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.UCIEKA);
                                        ucieka = true;
                                        organizm.idzXY(organizm.GetX() - 1, organizm.GetY());
                                }
                                else if (organizm.GetX() != this.swiat.Getszerokosc() - 1 && this.swiat.GetOrganizmy(organizm.GetX() + 1, organizm.GetY()) == null)
                                {
                                        this.swiat.dodajWydarzenie(organizm, this, this.swiat.UCIEKA);
                                        ucieka = true;
                                        organizm.idzXY(organizm.GetX() + 1, organizm.GetY());
                                }
                                else if (organizm.GetX() % 2 == 0  && swiat.Getuklad_planszy() == 6)
                                {
                                        if (organizm.GetX() != this.swiat.Getszerokosc() - 1 && organizm.GetY() != 0 && this.swiat.GetOrganizmy(organizm.GetX() + 1, organizm.GetY() - 1) == null) {
                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.UCIEKA);
                                                ucieka = true;
                                                organizm.idzXY(organizm.GetX() + 1, organizm.GetY() - 1);
                                        }
                                        else if (organizm.GetX() != 0 && organizm.GetY() != 0 && this.swiat.GetOrganizmy(organizm.GetX() - 1, organizm.GetY() - 1) == null)
                                        {
                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.UCIEKA);
                                                ucieka = true;
                                                organizm.idzXY(organizm.GetX() - 1, organizm.GetY() - 1);
                                        }
                                }
                                else if(organizm.GetX() % 2 != 0 && swiat.Getuklad_planszy() == 6)
                                {
                                        if (organizm.GetX() != this.swiat.Getszerokosc() - 1 && organizm.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(organizm.GetX() + 1, organizm.GetY() + 1) == null) {
                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.UCIEKA);
                                                ucieka = true;
                                                organizm.idzXY(organizm.GetX() + 1, organizm.GetY() + 1);
                                        }
                                        else if (organizm.GetX() != 0 && organizm.GetY() != this.swiat.Getwyskosc() - 1 && this.swiat.GetOrganizmy(organizm.GetX() - 1, organizm.GetY() + 1) == null)
                                        {
                                                this.swiat.dodajWydarzenie(organizm, this, this.swiat.UCIEKA);
                                                ucieka = true;
                                                organizm.idzXY(organizm.GetX() - 1, organizm.GetY() + 1);
                                        }
                                }


                                if (!ucieka)
                                {
                                        this.swiat.dodajWydarzenie(this, organizm, this.swiat.KILL);
                                        int x = organizm.GetX();
                                        int y = organizm.GetY();
                                        this.swiat.deletefromList(organizm);
                                        this.swiat.SetOrganizmy(x, y, null);
                                }

                                if (kierunek == this.swiat.GORA)
                                        this.idzGora();
                                else if (kierunek == this.swiat.DOL)
                                        this.idzDol();
                                else if (kierunek == this.swiat.LEWO)
                                        this.idzLewo();
                                else if (kierunek == this.swiat.PRAWO)
                                        this.idzPrawo();
                                else if (kierunek == this.swiat.PRAWOG)
                                        this.idzPrawoG();
                                else if (kierunek == this.swiat.LEWOG)
                                        this.idzLewoG();
                                }
                }
                else
                {
                        int x = this.GetX();
                        int y = this.GetY();
                        this.swiat.deletefromList(this);
                        this.swiat.SetOrganizmy(x, y, null);
                }
        }

        public abstract String rysowanie();
        public abstract String nazwa();
        public abstract void stworz_nowe_zwierze(int x, int y, Swiat sw);
        public boolean CzyRuszac()
        {
                return true;
        }
        public int ileRuchu()
        {
                return 1;
        }
        public void idzGora()
        {
                this.swiat.SetOrganizmy(this.GetX(), this.GetY(), null);
                this.SetY(this.GetY() - 1);
                this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
        }
        public void idzDol()
        {
                this.swiat.SetOrganizmy(this.GetX(), this.GetY(), null);
                this.SetY(this.GetY() + 1);
                this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
        }
        public void idzLewo()
        {
                this.swiat.SetOrganizmy(this.GetX(), this.GetY(), null);
                this.SetX(this.GetX() - 1);
                this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
        }
        public void idzPrawo()
        {
                this.swiat.SetOrganizmy(this.GetX(), this.GetY(), null);
                this.SetX(this.GetX() + 1);
                this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
        }
        public void idzLewoG()
        {
                if(this.GetX() % 2 == 0) {
                        this.swiat.SetOrganizmy(this.GetX(), this.GetY(), null);
                        this.SetX(this.GetX() - 1);
                        this.SetY(this.GetY() - 1);
                        this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
                }
                else{
                        this.swiat.SetOrganizmy(this.GetX(), this.GetY(), null);
                        this.SetX(this.GetX() - 1);
                        this.SetY(this.GetY() + 1);
                        this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
                }
        }
        public void idzPrawoG()
        {
                if(this.GetX() % 2 == 0) {
                        this.swiat.SetOrganizmy(this.GetX(), this.GetY(), null);
                        this.SetX(this.GetX() + 1);
                        this.SetY(this.GetY() - 1);
                        this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
                }
                else{
                        this.swiat.SetOrganizmy(this.GetX(), this.GetY(), null);
                        this.SetX(this.GetX() + 1);
                        this.SetY(this.GetY() + 1);
                        this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
                }
        }


}