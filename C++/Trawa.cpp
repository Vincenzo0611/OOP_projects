#include <iostream>
#include <windows.h>
#include "Trawa.h"

Trawa::Trawa(int x, int y, Swiat* swiat) : Roslina(x, y, 0, 0, IDTRAWA, swiat)
{
}
Trawa::Trawa(int x, int y, bool dz, Swiat* swiat) : Roslina(x, y, dz, 0, 0, IDTRAWA, swiat)
{
}

void Trawa::rysowanie()
{
	HANDLE col;
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLORTRAWA);
	std::cout << "T";
}

std::string Trawa::nazwa()
{
	return "T";
}

void Trawa::stworz_nowe_Roslina(int x, int y, Swiat* sw)
{
	Organizm* org = new Trawa(x, y, true, sw);
}


Trawa::~Trawa()
{
}