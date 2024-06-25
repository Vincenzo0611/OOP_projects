#pragma once
#include "Organizm.h"


class Zwierze : public Organizm
{
public:
	Zwierze(int x, int y, int sila, int inicjatywa, int id, Swiat* swiat);
	Zwierze(int x, int y, bool dz, int sila, int inicjatywa, int id, Swiat* swiat);
	virtual void akcja() override;
	virtual void kolizja(Organizm* organizm, int kierunek) override;
	virtual void rysowanie() = 0;
	virtual std::string nazwa() = 0;
	virtual void stworz_nowe_zwierze(int x, int y, Swiat* sw) = 0;
	virtual bool CzyRuszac();
	virtual int ileRuchu();
	void idzGora();
	void idzDol();
	void idzLewo();
	void idzPrawo();
	virtual ~Zwierze();
};	