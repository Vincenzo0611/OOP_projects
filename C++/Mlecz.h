#pragma once
#include <iostream>
#include "Organizm.h"
#include "Roslina.h"

class Mlecz : public Roslina
{
public:
	Mlecz(int x, int y, Swiat* swiat);
	Mlecz(int x, int y, bool dz, Swiat* swiat);
	void rysowanie() override;
	virtual std::string nazwa() override;
	void stworz_nowe_Roslina(int x, int y, Swiat* sw) override;
	int Ileprob() override;
	~Mlecz();
};