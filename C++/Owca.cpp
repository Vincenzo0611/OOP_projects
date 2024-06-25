#include <iostream>
#include <windows.h>
#include "Owca.h"

Owca::Owca(int x, int y, Swiat* swiat) : Zwierze(x, y, 4, 4, IDOWCA, swiat)
{
}

Owca::Owca(int x, int y, bool dz, Swiat* swiat) : Zwierze(x, y, dz, 4, 4, IDOWCA, swiat)
{
}

void Owca::rysowanie()
{
	HANDLE col;
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLOROWCA);
	std::cout << "O";
}

std::string Owca::nazwa()
{
	return "O";
}

void Owca::stworz_nowe_zwierze(int x, int y, Swiat* sw)
{
	Organizm* org = new Owca(x, y, true, sw);
}


Owca::~Owca()
{
}