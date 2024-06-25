#include <iostream>
#include <windows.h>
#include "Guarana.h"

Guarana::Guarana(int x, int y, Swiat* swiat) : Roslina(x, y, 0, 0, IDGUARANA, swiat)
{
}
Guarana::Guarana(int x, int y, bool dz, Swiat* swiat) : Roslina(x, y, dz, 0, 0, IDGUARANA, swiat)
{
}

void Guarana::rysowanie()
{
	HANDLE col;
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLORGUARANA);
	std::cout << "G";
}

std::string Guarana::nazwa()
{
	return "G";
}

void Guarana::stworz_nowe_Roslina(int x, int y, Swiat* sw)
{
	Organizm* org = new Guarana(x, y, true, sw);
}

bool Guarana::CzyDodajeSily()
{
	return true;
}

Guarana::~Guarana()
{
}