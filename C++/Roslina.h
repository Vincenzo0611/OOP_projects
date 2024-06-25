#pragma once
#include "Organizm.h"


class Roslina : public Organizm
{
public:
	Roslina(int x, int y, int sila, int inicjatywa, int id, Swiat* swiat);
	Roslina(int x, int y, bool dz, int sila, int inicjatywa, int id, Swiat* swiat);
	virtual void akcja() override;
	virtual void kolizja(Organizm* organizm, int kierunek) override;
	virtual void rysowanie() = 0;
	virtual std::string nazwa() = 0;
	virtual void stworz_nowe_Roslina(int x, int y, Swiat* sw) = 0;
	virtual int Ileprob();
	virtual int SzansaNaRozsiew();
	void zasadzGora();
	void zasadzDol();
	void zasadzLewo();
	void zasadzPrawo();
	virtual ~Roslina();
};