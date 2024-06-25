#pragma once
#include <iostream>
#include "Organizm.h"
#include "Roslina.h"

class BarszczSosnowskiego : public Roslina
{
public:
	BarszczSosnowskiego(int x, int y, Swiat* swiat);
	BarszczSosnowskiego(int x, int y, bool dz, Swiat* swiat);
	void rysowanie() override;
	virtual std::string nazwa() override;
	void akcja() override;
	void stworz_nowe_Roslina(int x, int y, Swiat* sw) override;
	bool CzyNieZabija() override;
	int SzansaNaRozsiew() override;
	~BarszczSosnowskiego();
};