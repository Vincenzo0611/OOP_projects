#include <iostream>
#include <windows.h>
#include "Antylopa.h"

Antylopa::Antylopa(int x, int y, Swiat* swiat) : Zwierze(x, y, 4, 4, IDANTYLOPA, swiat)
{
}

Antylopa::Antylopa(int x, int y, bool dz, Swiat* swiat) : Zwierze(x, y, dz, 4, 4, IDANTYLOPA, swiat)
{
}

void Antylopa::rysowanie()
{
	HANDLE col;
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLORANTYLOPA);
	std::cout << "A";
}

std::string Antylopa::nazwa()
{
	return "A";
}

void Antylopa::kolizja(Organizm* organizm, int kierunek)
{
	if (typeid(*this) == typeid(*organizm))
	{
		Zwierze::kolizja(organizm, kierunek);
	}
	else
	{
		int random = rand() % 2;
		if (random == 0)
		{
			if (organizm->GetY() != 0 && this->swiat->GetOrganizmy(organizm->GetX(), organizm->GetY() - 1) == nullptr)
			{
				this->swiat->dodajWydarzenie(this, organizm, UCIEKA);
				this->idzXY(organizm->GetX(), organizm->GetY() - 1);
			}
			else if (organizm->GetY() != this->swiat->Getwyskosc() - 1 && this->swiat->GetOrganizmy(organizm->GetX(), organizm->GetY() + 1) == nullptr)
			{
				this->swiat->dodajWydarzenie(this, organizm, UCIEKA);
				this->idzXY(organizm->GetX(), organizm->GetY() + 1);
			}
			else if (organizm->GetX() != 0 && this->swiat->GetOrganizmy(organizm->GetX() - 1, organizm->GetY()) == nullptr)
			{
				this->swiat->dodajWydarzenie(this, organizm, UCIEKA);
				this->idzXY(organizm->GetX() - 1, organizm->GetY());
			}
			else if (organizm->GetX() != this->swiat->Getszerokosc() - 1 && this->swiat->GetOrganizmy(organizm->GetX() + 1, organizm->GetY()) == nullptr)
			{
				this->swiat->dodajWydarzenie(this, organizm, UCIEKA);
				this->idzXY(organizm->GetX() + 1, organizm->GetY());
			}
		}
		else
		{
			Zwierze::kolizja(organizm, kierunek);
		}
	}
}

void Antylopa::stworz_nowe_zwierze(int x, int y, Swiat* sw)
{
	Organizm* org = new Antylopa(x, y, true, sw);
}

int Antylopa::ileRuchu()
{
	return 2;
}

bool Antylopa::CzyUcieka()
{
	int random = rand() % 2;
	if (random == 0)
		return true;
	return false;
}

Antylopa::~Antylopa()
{
}