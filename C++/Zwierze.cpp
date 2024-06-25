#include "Zwierze.h"
#include <cstdlib>
#include <ctime>
#include "define.h"

Zwierze::Zwierze(int x, int y, int sila, int inicjatywa, int id, Swiat* swiat) : Organizm(x, y, sila, inicjatywa, id, swiat)
{
}
Zwierze::Zwierze(int x, int y, bool dz, int sila, int inicjatywa, int id, Swiat* swiat) : Organizm(x, y, sila, inicjatywa, id, dz, swiat)
{
}

void Zwierze::akcja()
{
    for (int r = 0; r < this->ileRuchu(); r++)
    {
        if (this->swiat->GetOrganizmy(this->GetX(), this->GetY()) == nullptr)
            return;
        if (this == nullptr)
            return;
        if (!this->CzyRuszac())
            return;
        int q = 0;
        while (q == 0)
        {
            int random = rand() % 4;

            if (random == GORA && GetY() != 0)
            {
                q = 1;
                if (this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1) == nullptr)
                {
                    this->idzGora();
                }
                else if (this->CzyWech(this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1)))
                {
                    this->kolizja(this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1), GORA);
                }
            }
            else if (random == DOL && GetY() != this->swiat->Getwyskosc() - 1)
            {
                q = 1;
                if (this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1) == nullptr)
                {
                    this->idzDol();
                }
                else  if (this->CzyWech(this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1)))
                {
                    this->kolizja(this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1), DOL);
                }
            }
            else if (random == PRAWO && GetX() != this->swiat->Getszerokosc() - 1)
            {
                q = 1;
                if (this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY()) == nullptr)
                {
                    this->idzPrawo();
                }
                else  if (this->CzyWech(this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY())))
                {
                    this->kolizja(this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY()), PRAWO);
                }
            }
            else if (random == LEWO && GetX() != 0)
            {
                q = 1;
                if (this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY()) == nullptr)
                {
                    this->idzLewo();
                }
                else if (this->CzyWech(this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY())))
                {
                    this->kolizja(this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY()), LEWO);
                }
            }
        }
    }

}

void Zwierze::kolizja(Organizm* organizm, int kierunek)
{
    if (organizm->CzyNieZabija())
    {
        if (typeid(*this) == typeid(*organizm))
        {
            if (this->GetRundy() >= CZAS_ROZMNAZANIA && organizm->GetRundy() >= CZAS_ROZMNAZANIA)
            {
                //rozmnazaj
                if (this->GetY() != 0 && this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1) == nullptr)
                {
                    this->WyzerujRundy();
                    organizm->WyzerujRundy();
                    this->swiat->dodajWydarzenie(this, organizm, ROZMNAZANIE);
                    stworz_nowe_zwierze(this->GetX(), (this->GetY() - 1), this->swiat);
                }
                else if (this->GetY() != this->swiat->Getwyskosc() - 1 && this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1) == nullptr)
                {
                    this->WyzerujRundy();
                    organizm->WyzerujRundy();
                    this->swiat->dodajWydarzenie(this, organizm, ROZMNAZANIE);
                    stworz_nowe_zwierze(this->GetX(), (this->GetY() + 1), this->swiat);
                }
                else if (this->GetX() != this->swiat->Getszerokosc() - 1 && this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY()) == nullptr)
                {
                    this->WyzerujRundy();
                    organizm->WyzerujRundy();
                    this->swiat->dodajWydarzenie(this, organizm, ROZMNAZANIE);
                    stworz_nowe_zwierze((this->GetX() + 1), this->GetY(), this->swiat);
                }
                else if (this->GetX() != 0 && this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY()) == nullptr)
                {
                    this->WyzerujRundy();
                    organizm->WyzerujRundy();
                    this->swiat->dodajWydarzenie(this, organizm, ROZMNAZANIE);
                    stworz_nowe_zwierze((this->GetX() - 1), this->GetY(), this->swiat);
                }
            }
        }
        else if (!organizm->CzyUcieka())
        {
            if (this->GetSila() < organizm->GetSila())
            {
                //usun this
                this->swiat->dodajWydarzenie(organizm, this, KILL);
                int x = this->GetX();
                int y = this->GetY();
                this->swiat->deletefromList(this);
                this->swiat->SetOrganizmy(x, y, nullptr);
            }
            else if (organizm->CzyOdbijaAtak(this) == 0)
            {
                //usun organizm
                //wykonaj ruch
                if (organizm->CzyDodajeSily())
                    this->SetSila(this->GetSila() + 3);
                this->swiat->dodajWydarzenie(this, organizm, KILL);
                int x = organizm->GetX();
                int y = organizm->GetY();
                this->swiat->deletefromList(organizm);
                this->swiat->SetOrganizmy(x, y, nullptr);
                if (kierunek == GORA)
                    this->idzGora();
                else if (kierunek == DOL)
                    this->idzDol();
                else if (kierunek == LEWO)
                    this->idzLewo();
                else if (kierunek == PRAWO)
                    this->idzPrawo();
            }
            else if (organizm->CzyOdbijaAtak(this) == 1)
            {
                //umiejetnisc czlowieka
                int x = organizm->GetX();
                int y = organizm->GetY();
                int k = 0;
                while (k == 0)
                {
                    int r = rand() % 4;
                    if (r == GORA && y != 0)
                    {
                        if (this->swiat->GetOrganizmy(x, y - 1) == nullptr)
                        {
                            this->swiat->dodajWydarzenie(organizm, this, ODBITY);
                            k = 1;
                            this->idzXY(x, y - 1);
                        }
                        else if (this->swiat->GetOrganizmy(x, y - 1) == this)
                        {
                            this->swiat->dodajWydarzenie(organizm, this, ODBITY);
                            k = 1;
                        }
                    }
                    else if (r == DOL && y != this->swiat->Getwyskosc() - 1)
                    {
                        
                        if (this->swiat->GetOrganizmy(x, y + 1) == nullptr)
                        {
                            this->swiat->dodajWydarzenie(organizm, this, ODBITY);
                            k = 1;
                            this->idzXY(x, y + 1);
                        } 
                        else if (this->swiat->GetOrganizmy(x, y + 1) == this)
                        {
                            this->swiat->dodajWydarzenie(organizm, this, ODBITY);
                            k = 1;
                        }
                    }
                    else if (r == PRAWO && x != this->swiat->Getszerokosc() - 1)
                    {
                        if (this->swiat->GetOrganizmy(x + 1, y) == nullptr)
                        {
                            this->swiat->dodajWydarzenie(organizm, this, ODBITY);
                            k = 1;
                            this->idzXY(x + 1, y);
                        }
                        else if (this->swiat->GetOrganizmy(x + 1, y) == this)
                        {
                            this->swiat->dodajWydarzenie(organizm, this, ODBITY);
                            k = 1;
                        }
                    }
                    else if (r == LEWO && x != 0)
                    {
                        if (this->swiat->GetOrganizmy(x -1, y) == nullptr)
                        {
                            this->swiat->dodajWydarzenie(organizm, this, ODBITY);
                            k = 1;
                            this->idzXY(x - 1,y);
                        }
                        else if (this->swiat->GetOrganizmy(x -1, y) == this)
                        {
                            this->swiat->dodajWydarzenie(organizm, this, ODBITY);
                            k = 1;
                        }
                    }
                }
            }
            else if (organizm->CzyOdbijaAtak(this) == 2)
            {
                // zolw

                this->swiat->dodajWydarzenie(organizm, this, ODBITY);

            }
        }
        else
        {
            //ucieczka antylopy
            bool ucieka = false;


            if (organizm->GetY() != 0 && this->swiat->GetOrganizmy(organizm->GetX(), organizm->GetY() - 1) == nullptr)
            {
                this->swiat->dodajWydarzenie(organizm, this, UCIEKA);
                ucieka = true;
                organizm->idzXY(organizm->GetX(), organizm->GetY() - 1);
            }
            else if (organizm->GetY() != this->swiat->Getwyskosc() - 1 && this->swiat->GetOrganizmy(organizm->GetX(), organizm->GetY() + 1) == nullptr)
            {
                this->swiat->dodajWydarzenie(organizm, this, UCIEKA);
                ucieka = true;
                organizm->idzXY(organizm->GetX(), organizm->GetY() + 1);
            }
            else if (organizm->GetX() != 0 && this->swiat->GetOrganizmy(organizm->GetX() - 1, organizm->GetY()) == nullptr)
            {
                this->swiat->dodajWydarzenie(organizm, this, UCIEKA);
                ucieka = true;
                organizm->idzXY(organizm->GetX() - 1, organizm->GetY());
            }
            else if (organizm->GetX() != this->swiat->Getszerokosc() - 1 && this->swiat->GetOrganizmy(organizm->GetX() + 1, organizm->GetY()) == nullptr)
            {
                this->swiat->dodajWydarzenie(organizm, this, UCIEKA);
                ucieka = true;
                organizm->idzXY(organizm->GetX() + 1, organizm->GetY());
            }


            if (!ucieka)
            {
                this->swiat->dodajWydarzenie(this, organizm, KILL);
                int x = organizm->GetX();
                int y = organizm->GetY();
                this->swiat->deletefromList(organizm);
                this->swiat->SetOrganizmy(x, y, nullptr);
            }


            if (kierunek == GORA)
                this->idzGora();
            else if (kierunek == DOL)
                this->idzDol();
            else if (kierunek == LEWO)
                this->idzLewo();
            else if (kierunek == PRAWO)
                this->idzPrawo();
        }
    }
    else
    {
        int x = this->GetX();
        int y = this->GetY();
        this->swiat->deletefromList(this);
        this->swiat->SetOrganizmy(x, y, nullptr);
    }

}

void Zwierze::idzGora()
{
    this->swiat->SetOrganizmy(this->GetX(), this->GetY(), nullptr);
    this->SetY(this->GetY() - 1);
    this->swiat->SetOrganizmy(this->GetX(), this->GetY(), this);
}
void Zwierze::idzDol()
{
    this->swiat->SetOrganizmy(this->GetX(), this->GetY(), nullptr);
    this->SetY(this->GetY() + 1);
    this->swiat->SetOrganizmy(this->GetX(), this->GetY(), this);
}
void Zwierze::idzLewo()
{
    this->swiat->SetOrganizmy(this->GetX(), this->GetY(), nullptr);
    this->SetX(this->GetX() - 1);
    this->swiat->SetOrganizmy(this->GetX(), this->GetY(), this);
}
void Zwierze::idzPrawo()
{
    this->swiat->SetOrganizmy(this->GetX(), this->GetY(), nullptr);
    this->SetX(this->GetX() + 1);
    this->swiat->SetOrganizmy(this->GetX(), this->GetY(), this);
}

bool Zwierze::CzyRuszac()
{
    return true;
}

int Zwierze::ileRuchu()
{
    return 1;
}

Zwierze::~Zwierze()
{

}