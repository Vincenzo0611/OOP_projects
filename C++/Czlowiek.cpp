#include <iostream>
#include "Czlowiek.h"
#include <windows.h>

Czlowiek::Czlowiek(int x, int y, Swiat* swiat) : Zwierze(x, y, 5, 4, IDCZLOWIEK, swiat)
{
}
Czlowiek::Czlowiek(int x, int y, bool dz, Swiat* swiat) : Zwierze(x, y, dz, 5, 4, IDCZLOWIEK, swiat)
{
}

void Czlowiek::rysowanie()
{
    HANDLE col;
    col = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(col, KOLORCZLOWIEK);
	std::cout << "H";
}

std::string Czlowiek::nazwa()
{
    return "H";
}

void Czlowiek::akcja() 
{
    if (this->swiat->Getkierunek() == GORA && GetY() != 0)
    {
        if (this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1) == nullptr)
        {
            this->idzGora();
        }
        else
        {
            this->kolizja(this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1), GORA);
        }
    }
    else if (this->swiat->Getkierunek() == DOL && GetY() != this->swiat->Getwyskosc() - 1)
    {
        if (this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1) == nullptr)
        {
            this->idzDol();
        }
        else
        {
            this->kolizja(this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1), DOL);
        }
    }
    else if (this->swiat->Getkierunek() == PRAWO && GetX() != this->swiat->Getszerokosc() - 1)
    {
        if (this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY()) == nullptr)
        {
            this->idzPrawo();
        }
        else
        {
            this->kolizja(this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY()), PRAWO);
        }
    }
    else if (this->swiat->Getkierunek() == LEWO && GetX() != 0)
    {
        if (this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY()) == nullptr)
        {
            this->idzLewo();
        }
        else
        {
            this->kolizja(this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY()), LEWO);
        }
    }
}

int Czlowiek::CzyOdbijaAtak(Organizm* atakujacy)
{
    if (this->swiat->CzyAktywna())
        return 1;
    return 0;
}

void Czlowiek::stworz_nowe_zwierze(int x, int y, Swiat* sw)
{

}

Czlowiek::~Czlowiek()
{
}