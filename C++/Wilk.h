#pragma once
#include <iostream>
#include "Organizm.h"
#include "Zwierze.h"

class Wilk : public Zwierze 
{
private:

public:
	Wilk(int x, int y, Swiat* swiat);
	Wilk(int x, int y, bool dz, Swiat* swiat);
	void rysowanie() override;
	virtual std::string nazwa() override;
	void stworz_nowe_zwierze(int x, int y, Swiat* sw) override;
	~Wilk();
};