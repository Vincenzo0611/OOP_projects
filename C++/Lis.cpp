 #include <iostream>
#include <windows.h>
#include "Lis.h"

Lis::Lis(int x, int y, Swiat* swiat) : Zwierze(x, y, 3, 7, IDLIS, swiat)
{
}

Lis::Lis(int x, int y, bool dz, Swiat* swiat) : Zwierze(x, y, dz, 3, 7, IDLIS, swiat)
{
}

void Lis::rysowanie()
{
    HANDLE col;
    col = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(col, KOLORLIS);
	std::cout << "L";
}

std::string Lis::nazwa()
{
    return "L";
}

void Lis::stworz_nowe_zwierze(int x, int y, Swiat* sw)
{
	Organizm* org = new Lis(x, y, true, sw);
}

bool Lis::CzyWech(Organizm* organizm)
{
    if (this->GetSila() >= organizm->GetSila())
        return true;
    return false;
}

Lis::~Lis()
{
}