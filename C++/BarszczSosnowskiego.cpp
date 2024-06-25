#include <iostream>
#include <windows.h>
#include "BarszczSosnowskiego.h"

BarszczSosnowskiego::BarszczSosnowskiego(int x, int y, Swiat* swiat) : Roslina(x, y, 10, 0, IDBARSZCZSOS, swiat)
{
}
BarszczSosnowskiego::BarszczSosnowskiego(int x, int y, bool dz, Swiat* swiat) : Roslina(x, y, dz, 10, 0, IDBARSZCZSOS, swiat)
{
}

void BarszczSosnowskiego::rysowanie()
{
	HANDLE col;
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLORBARSZCZSOS);
	std::cout << "B";
}

std::string BarszczSosnowskiego::nazwa()
{
	return "B";
}

void BarszczSosnowskiego::akcja()
{
	if (this->GetY() != 0 && this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1) != nullptr && this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1)->GetInicjatywa() > 0 && typeid(*this) != typeid(*this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1)))
	{
		Organizm* organizm = this->swiat->GetOrganizmy(this->GetX(), this->GetY() - 1);
		this->swiat->dodajWydarzenie(this, organizm, KILL);
		int x = organizm->GetX();
		int y = organizm->GetY();
		this->swiat->deletefromList(organizm);
		this->swiat->SetOrganizmy(x, y, nullptr);
	}
	if (this->GetY() != this->swiat->Getwyskosc() - 1 && this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1) != nullptr && this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1)->GetInicjatywa() > 0 && typeid(*this) != typeid(*this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1)))
	{
		Organizm* organizm = this->swiat->GetOrganizmy(this->GetX(), this->GetY() + 1);
		this->swiat->dodajWydarzenie(this, organizm, KILL);
		int x = organizm->GetX();
		int y = organizm->GetY();
		this->swiat->deletefromList(organizm);
		this->swiat->SetOrganizmy(x, y, nullptr);
	}
	if (this->GetX() != 0 && this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY()) != nullptr && this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY())->GetInicjatywa() > 0 && typeid(*this) != typeid(*this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY())))
	{
		Organizm* organizm = this->swiat->GetOrganizmy(this->GetX() - 1, this->GetY());
		this->swiat->dodajWydarzenie(this, organizm, KILL);
		int x = organizm->GetX();
		int y = organizm->GetY();
		this->swiat->deletefromList(organizm);
		this->swiat->SetOrganizmy(x, y, nullptr);
	}
	if (this->GetX() != this->swiat->Getszerokosc() - 1 && this->swiat->GetOrganizmy(this->GetX()  + 1, this->GetY()) != nullptr && this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY())->GetInicjatywa() > 0 && typeid(*this) != typeid(*this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY())))
	{
		Organizm* organizm = this->swiat->GetOrganizmy(this->GetX() + 1, this->GetY());
		this->swiat->dodajWydarzenie(this, organizm, KILL);
		int x = organizm->GetX();
		int y = organizm->GetY();
		this->swiat->deletefromList(organizm);
		this->swiat->SetOrganizmy(x, y, nullptr);
	}

	Roslina::akcja();
}

void BarszczSosnowskiego::stworz_nowe_Roslina(int x, int y, Swiat* sw)
{
	Organizm* org = new BarszczSosnowskiego(x, y, true, sw);
}

bool BarszczSosnowskiego::CzyNieZabija()
{
	return false;
}


int BarszczSosnowskiego::SzansaNaRozsiew()
{
	return 100;
}

BarszczSosnowskiego::~BarszczSosnowskiego()
{
}