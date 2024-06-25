#pragma once
#include <iostream>
#include "Organizm.h"
#include "Zwierze.h"

class Owca : public Zwierze
{
private:

public:
	Owca(int x, int y, Swiat* swiat);
	Owca(int x, int y, bool dz, Swiat* swiat);
	void rysowanie() override;
	virtual std::string nazwa() override;
	void stworz_nowe_zwierze(int x, int y, Swiat* sw) override;
	~Owca();
};