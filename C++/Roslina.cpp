#include "Roslina.h"
#include <cstdlib>
#include <ctime>
#include "define.h"

Roslina::Roslina(int x, int y, int sila, int inicjatywa, int id, Swiat* swiat) : Organizm(x, y, sila, inicjatywa, id, swiat)
{
}
Roslina::Roslina(int x, int y, bool dz, int sila, int inicjatywa, int id, Swiat* swiat) : Organizm(x, y, sila, inicjatywa, id, dz, swiat)
{
}

void Roslina::akcja()
{
    int q = 0;
    for (int x = 0; x < this->Ileprob(); x++)
    {
        int szansa = rand() % this->SzansaNaRozsiew();
        q = 0;
        while (q == 0)
        {
            int random = rand() % 4;
            if (szansa != 0)
            {
                q = 1;
                random = 5;
            }

            if (random == GORA && GetY() != 0)
            {
                q = 1;
                if (this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1) == nullptr)
                {
                    this->swiat->dodajWydarzenie(this, nullptr, ROZPRZESTRZENIANIE);
                    this->zasadzGora();
                }
                else
                {
                    this->kolizja(this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1), GORA);
                }
            }
            else if (random == DOL && GetY() != this->swiat->Getwyskosc() - 1)
            {
                q = 1;
                if (this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1) == nullptr)
                {
                    this->swiat->dodajWydarzenie(this, nullptr, ROZPRZESTRZENIANIE);
                    this->zasadzDol();
                }
                else
                {
                    this->kolizja(this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1), DOL);
                }
            }
            else if (random == PRAWO && GetX() != this->swiat->Getszerokosc() - 1)
            {
                q = 1;
                if (this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY()) == nullptr)
                {
                    this->swiat->dodajWydarzenie(this, nullptr, ROZPRZESTRZENIANIE);
                    this->zasadzPrawo();
                }
                else
                {
                    this->kolizja(this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY()), PRAWO);
                }
            }
            else if (random == LEWO && GetX() != 0)
            {
                q = 1;
                if (this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY()) == nullptr)
                {
                    this->swiat->dodajWydarzenie(this, nullptr, ROZPRZESTRZENIANIE);
                    this->zasadzLewo();
                }
                else
                {
                    this->kolizja(this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY()), LEWO);
                }
            }
        }
    }
}

void Roslina::kolizja(Organizm* organizm, int kierunek)
{
    if (this->GetSila() >= organizm->GetSila())
    {
        //usun organizm
        //zasadz
        int x = organizm->GetX();
        int y = organizm->GetY();
        this->swiat->deletefromList(organizm);
        this->swiat->SetOrganizmy(x, y, nullptr);
        if (kierunek == GORA)
        {
            this->swiat->dodajWydarzenie(this, nullptr, ROZPRZESTRZENIANIE);
            this->zasadzGora();
        }
        else if (kierunek == DOL)
        {
            this->swiat->dodajWydarzenie(this, nullptr, ROZPRZESTRZENIANIE);
            this->zasadzDol();
        }
        else if (kierunek == LEWO)
        {
            this->swiat->dodajWydarzenie(this, nullptr, ROZPRZESTRZENIANIE);
            this->zasadzLewo();
        }
        else if (kierunek == PRAWO)
        {
            this->swiat->dodajWydarzenie(this, nullptr, ROZPRZESTRZENIANIE);
            this->zasadzPrawo();
        }
    }
}

int Roslina::Ileprob()
{
    return 1;
}

int Roslina::SzansaNaRozsiew()
{
    return 30;
}

void Roslina::zasadzGora()
{
    stworz_nowe_Roslina(this->GetX(), this->GetY() - 1, this->swiat);
}
void Roslina::zasadzDol()
{
    stworz_nowe_Roslina(this->GetX(), this->GetY() + 1, this->swiat);
}
void Roslina::zasadzLewo()
{
    stworz_nowe_Roslina(this->GetX() - 1, this->GetY(), this->swiat);
}
void Roslina::zasadzPrawo()
{
    stworz_nowe_Roslina(this->GetX() + 1, this->GetY(), this->swiat);
}


Roslina::~Roslina()
{

}