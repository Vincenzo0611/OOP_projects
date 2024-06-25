#include <iostream>
#include <windows.h>
#include "WilczeJagody.h"

WilczeJagody::WilczeJagody(int x, int y, Swiat* swiat) : Roslina(x, y, 99, 0, IDWILCZEJAGODY, swiat)
{
}
WilczeJagody::WilczeJagody(int x, int y, bool dz, Swiat* swiat) : Roslina(x, y, dz, 99, 0, IDWILCZEJAGODY, swiat)
{
}

void WilczeJagody::rysowanie()
{
	HANDLE col;
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLORWILCZEJAGODY);
	std::cout << "J";
}

std::string WilczeJagody::nazwa()
{
	return "J";
}

void WilczeJagody::stworz_nowe_Roslina(int x, int y, Swiat* sw)
{
	Organizm* org = new WilczeJagody(x, y, true, sw);
}

int WilczeJagody::SzansaNaRozsiew()
{
	return 100;
}

WilczeJagody::~WilczeJagody()
{
}