#pragma once
#include <iostream>
#include "Organizm.h"
#include "Zwierze.h"

class Lis : public Zwierze
{
private:

public:
	Lis(int x, int y, Swiat* swiat);
	Lis(int x, int y, bool dz, Swiat* swiat);
	void rysowanie() override;
	virtual std::string nazwa() override;
	void stworz_nowe_zwierze(int x, int y, Swiat* sw) override;
	virtual bool CzyWech(Organizm* organizm) override;
	~Lis();
};