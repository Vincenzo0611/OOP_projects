#include <iostream>
#include <windows.h>
#include "Wilk.h"

Wilk::Wilk(int x, int y, Swiat* swiat) : Zwierze(x, y, 9, 5, IDWILK, swiat)
{
}
Wilk::Wilk(int x, int y, bool dz, Swiat* swiat) : Zwierze(x, y, dz, 9, 5, IDWILK, swiat)
{
}

void Wilk::rysowanie()
{
	HANDLE col;
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLORWILK);
	std::cout << "W";
}

std::string Wilk::nazwa()
{
	return "W";
}

void Wilk::stworz_nowe_zwierze(int x, int y, Swiat* sw)
{
	Organizm* org = new Wilk(x, y, true, sw);
}


Wilk::~Wilk()
{
}