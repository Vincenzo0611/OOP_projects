#ifndef SWIAT_H
#define SWIAT_H

#include <iostream>
#include <vector>
#include <string>
#include "Organizm.h"
#include "define.h"

class Organizm;


class Swiat{
private:
	std::vector < Organizm* > kolejnosc[ILIST];
	std::vector < std::string > wydarzenia;
	int wysokosc;
	int szerokosc;
	int kierunek_ruchu;
	int rundy;
	bool aktywna;
	bool czlowiekzyje;
	std::string komunikat;
	Organizm** organizmy;
public:
	Swiat();
	Swiat(int m);
	Swiat(int m, int n);
	void wykonajTure();
	void rysujSwiat();
	void umiejetnosc();
	void addToList(Organizm* organizm);
	void deletefromList(Organizm* organizm);
	int Getszerokosc() const;
	int Getwyskosc() const;
	void Setkierunek(int kierunek);
	int Getkierunek() const;
	void SetOrganizmy(int x, int y, Organizm* n);
	void WyzerujRundy();
	void SetRundy(int r);
	void KolejnaRunda();
	int GetRundy();
	bool CzyAktywna();
	void ZmienAktywna();
	void dodajWydarzenie(Organizm* organizm1, Organizm* organizm2, int kod);
	void save();
	void load();
	Organizm* GetOrganizmy(int x, int y) const;
	~Swiat();
};

#endif