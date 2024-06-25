#pragma once
#include <iostream>
#include "define.h"
#include "Swiat.h"

class Swiat;

class Organizm{
private:
	int id;
	int sila;
	int inicjatywa;
	int x;
	int y;
	int rundy;
	bool dopiero_zrodzony;
protected:
	Swiat* swiat;
public:
	Organizm(int x, int y, int sila, int inicjatywa, int id, Swiat* swiat);
	Organizm(int x, int y, int sila, int inicjatywa, int id,  bool dz, Swiat* swiat);
	virtual void akcja() = 0;
	virtual void kolizja(Organizm* organizm, int kierunek) = 0;
	virtual void rysowanie() = 0;
	virtual std::string nazwa() = 0;
	std::string UczestnikWydarzenia();
	virtual int CzyOdbijaAtak(Organizm* atakujacy);
	virtual bool CzyWech(Organizm* organizm);
	virtual bool CzyDodajeSily();
	virtual bool CzyUcieka();
	virtual bool CzyNieZabija();
	void idzXY(int x, int y);
	virtual ~Organizm();
	int GetX() const;
	int GetY() const;
	int GetSila() const;
	int GetInicjatywa() const;
	bool GetDZ() const;
	int GetId() const;
	void SetX(int x);
	void SetY(int y);
	void SetSila(int sila);
	void SetInicjatywa(int inicjatywa);
	void SetDZ(bool zrodzony);
	void WyzerujRundy();
	void SetRundy(int r);
	void KolejnaRunda();
	int GetRundy();

};
