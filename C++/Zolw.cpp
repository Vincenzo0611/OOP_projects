#include <iostream>
#include <windows.h>
#include "Zolw.h"

Zolw::Zolw(int x, int y, Swiat* swiat) : Zwierze(x, y, 2, 1, IDZOLW, swiat)
{
}

Zolw::Zolw(int x, int y, bool dz, Swiat* swiat) : Zwierze(x, y, dz, 2, 1, IDZOLW, swiat)
{
}

void Zolw::rysowanie()
{
	HANDLE col;
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLORZOLW);
	std::cout << "Z";
}

std::string Zolw::nazwa()
{
	return "Z";
}

void Zolw::stworz_nowe_zwierze(int x, int y, Swiat* sw)
{
	Organizm* org = new Zolw(x, y, true, sw);
}

bool Zolw::CzyRuszac()
{
	int random = rand() % 4;

	if (random == 3)
		return true;
	return false;
}

int Zolw::CzyOdbijaAtak(Organizm* atakujacy)
{
	if (atakujacy->GetSila() < 5)
		return 2;
	return 0;
}

Zolw::~Zolw()
{
}