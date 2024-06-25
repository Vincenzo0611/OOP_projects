#pragma once
#include <iostream>
#include "Organizm.h"
#include "Zwierze.h"

class Zolw : public Zwierze
{
private:

public:
	Zolw(int x, int y, Swiat* swiat);
	Zolw(int x, int y, bool dz, Swiat* swiat);
	void rysowanie() override;
	virtual std::string nazwa() override;
	void stworz_nowe_zwierze(int x, int y, Swiat* sw) override;
	bool CzyRuszac() override;
	int CzyOdbijaAtak(Organizm* atakujacy) override;
	~Zolw();
};