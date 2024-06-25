#include <iostream>
#include <windows.h>
#include "Mlecz.h"

Mlecz::Mlecz(int x, int y, Swiat* swiat) : Roslina(x, y, 0, 0, IDMLECZ, swiat)
{
}
Mlecz::Mlecz(int x, int y, bool dz, Swiat* swiat) : Roslina(x, y, dz, 0, 0, IDMLECZ, swiat)
{
}

void Mlecz::rysowanie()
{
	HANDLE col;
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLORMLECZ);
	std::cout << "M";
}

std::string Mlecz::nazwa()
{
	return "M";
}

void Mlecz::stworz_nowe_Roslina(int x, int y, Swiat* sw)
{
	Organizm* org = new Mlecz(x, y, true, sw);
}

int Mlecz::Ileprob()
{
	return 3;
}

Mlecz::~Mlecz()
{
}