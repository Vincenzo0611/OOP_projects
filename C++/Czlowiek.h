#pragma once
#include <iostream>
#include "Organizm.h"
#include "Zwierze.h"

class Czlowiek : public Zwierze
{
public:
	Czlowiek(int x, int y, Swiat* swiat);
	Czlowiek(int x, int y, bool dz, Swiat* swiat);
	void rysowanie() override;
	void stworz_nowe_zwierze(int x, int y, Swiat* sw) override;
	virtual void akcja() override;
	virtual std::string nazwa() override;
	int CzyOdbijaAtak(Organizm* atakujacy) override;
	~Czlowiek();
};