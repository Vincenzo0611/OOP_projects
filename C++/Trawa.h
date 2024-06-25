#pragma once
#include <iostream>
#include "Organizm.h"
#include "Roslina.h"

class Trawa : public Roslina
{
public:
	Trawa(int x, int y, Swiat* swiat);
	Trawa(int x, int y, bool dz, Swiat* swiat);
	void rysowanie() override;
	virtual std::string nazwa() override;
	void stworz_nowe_Roslina(int x, int y, Swiat* sw) override;
	~Trawa();
};