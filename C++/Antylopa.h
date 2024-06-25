#pragma once
#include <iostream>
#include "Organizm.h"
#include "Zwierze.h"

class Antylopa : public Zwierze
{
private:

public:
	Antylopa(int x, int y, Swiat* swiat);
	Antylopa(int x, int y, bool dz, Swiat* swiat);
	void rysowanie() override;
	virtual std::string nazwa() override;
	void kolizja(Organizm* organizm, int kierunek) override;
	void stworz_nowe_zwierze(int x, int y, Swiat* sw) override;
	int ileRuchu() override;
	bool CzyUcieka() override;
	~Antylopa();
};