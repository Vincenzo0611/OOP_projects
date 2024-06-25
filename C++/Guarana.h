#pragma once
#include <iostream>
#include "Organizm.h"
#include "Roslina.h"

class Guarana : public Roslina
{
public:
	Guarana(int x, int y, Swiat* swiat);
	Guarana(int x, int y, bool dz, Swiat* swiat);
	void rysowanie() override;
	virtual std::string nazwa() override;
	void stworz_nowe_Roslina(int x, int y, Swiat* sw) override;
	bool CzyDodajeSily() override;
	~Guarana();
};