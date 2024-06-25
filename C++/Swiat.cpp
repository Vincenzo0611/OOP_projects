#include "Swiat.h"
#include <windows.h>
#include <fstream>
#include "Czlowiek.h"
#include "Wilk.h"
#include "Owca.h"
#include "Lis.h"
#include "Zolw.h"
#include "Antylopa.h"
#include "Trawa.h"
#include "Mlecz.h"
#include "Guarana.h"
#include "WilczeJagody.h"
#include "BarszczSosnowskiego.h"

Swiat::Swiat()
{
	this->organizmy = new Organizm * [40 * 40];

	for (int i = 0; i < 40*40; i++)
	{
		organizmy[i] = nullptr;
	}

	this->szerokosc = 40;
	this->wysokosc = 40;
	this->Setkierunek(BRAK);
	this->aktywna = false;
	this->czlowiekzyje = true;
	this->rundy = CZAS_UMIEJETNOSCI;
	this->komunikat = "";
}

Swiat::Swiat(int m)
{
	this->organizmy = new Organizm * [m * 40];

	for (int i = 0; i < m*40; i++)
	{
		organizmy[i] = nullptr;
	}

	this->szerokosc = m;
	this->wysokosc = 40;
	this->Setkierunek(BRAK);
	this->aktywna = false;
	this->czlowiekzyje = true;
	this->rundy = CZAS_UMIEJETNOSCI;
	this->komunikat = "";
}

Swiat::Swiat(int m, int n)
{
	this->organizmy = new Organizm * [m * n];

	for (int i = 0; i < m * n; i++)
	{
		organizmy[i] = nullptr;
	}
	
	this->szerokosc = m;
	this->wysokosc = n;
	this->Setkierunek(BRAK);
	this->aktywna = false;
	this->czlowiekzyje = true;
	this->rundy = CZAS_UMIEJETNOSCI;
	this->komunikat = "";

	Organizm* org = new Czlowiek(0, 0, this);
	int id = 0, x, y;
	for (int i = 0; i < (szerokosc * wysokosc) / 15; i++)
	{
		id = rand() % 11 + 2;
		x = rand() % szerokosc;
		y = rand() % wysokosc;
		if (this->GetOrganizmy(x, y) == nullptr)
		{
			if (id == IDWILK)
				org = new Wilk(x, y, this);
			else if (id == IDOWCA)
				org = new Owca(x, y, this);
			else if (id == IDLIS)
				org = new Lis(x, y, this);
			else if (id == IDZOLW)
				org = new Zolw(x, y, this);
			else if (id == IDANTYLOPA)
				org = new Antylopa(x, y, this);
			else if (id == IDTRAWA)
				org = new Trawa(x, y, this);
			else if (id == IDMLECZ)
				org = new Mlecz(x, y, this);
			else if (id == IDGUARANA)
				org = new Guarana(x, y, this);
			else if (id == IDWILCZEJAGODY)
				org = new WilczeJagody(x, y, this);
			else if (id == IDBARSZCZSOS)
				org = new BarszczSosnowskiego(x, y, this);
		}

	}


}
void Swiat::wykonajTure()
{
	for (int i = ILIST - 1; i >= 0; i--)
	{
		int j = 0;
		while (j < kolejnosc[i].size())
		{
			if (kolejnosc[i][j]->GetDZ() == false)
			{
				kolejnosc[i][j]->KolejnaRunda();
				kolejnosc[i][j]->akcja();
			}
			else
				kolejnosc[i][j]->SetDZ(false);
			j++;
		}
	}
	if (this->rundy >= CZAS_UMIEJETNOSCI && this->aktywna == true)
	{
		this->ZmienAktywna();
		this->WyzerujRundy();
		this->dodajWydarzenie(nullptr, nullptr, UMIEJETNOSC_N);
	}
	this->KolejnaRunda();
}

void Swiat::umiejetnosc()
{
	if(this->czlowiekzyje == false)
	{
		this->komunikat = "Nie mozna uzyc umiejetnosci, czlowiek nie zyje";
	}
	else if (this->rundy >= CZAS_UMIEJETNOSCI)
	{
		this->ZmienAktywna();
		this->WyzerujRundy();
		if(this->aktywna)
			this->dodajWydarzenie(nullptr, nullptr, UMIEJETNOSC_A);
		else
			this->dodajWydarzenie(nullptr, nullptr, UMIEJETNOSC_N);
	}
	else
	{
		if (this->CzyAktywna())
		{
			//juz aktywna ile tur jeszcze
			this->komunikat = "Umiejetnosc aktywna przez ";
			this->komunikat += char(CZAS_UMIEJETNOSCI - this->rundy + 48);
			if (CZAS_UMIEJETNOSCI - this->rundy == 1)
				this->komunikat += " ture";
			else if (CZAS_UMIEJETNOSCI - this->rundy == 5)
				this->komunikat += " tur";
			else
				this->komunikat += " tury";
		}
		else
		{
			//umiejetnosc gotowa w nastepnej turze
			this->komunikat = "Umiejetnosc gotowa za ";
			this->komunikat += char(CZAS_UMIEJETNOSCI - this->rundy + 48);
			if (CZAS_UMIEJETNOSCI - this->rundy == 1)
				this->komunikat += " ture";
			else if (CZAS_UMIEJETNOSCI - this->rundy == 5)
				this->komunikat += " tur";
			else
				this->komunikat += " tury";
		}
	}
}

void Swiat::rysujSwiat()
{
	HANDLE col;
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	int i;
	for (int j = 0; j < (szerokosc + 1) * 2; j++)
		std::cout << "-";
	std::cout << "\n";
	for (i = 0; i < wysokosc; i++)
	{
		SetConsoleTextAttribute(col, KOLORDEFAULT);
		std::cout << "|";
		for (int j = 0; j < szerokosc; j++)
		{
			if (organizmy[i * szerokosc + j] != nullptr)
				organizmy[i * szerokosc + j]->rysowanie();
			else
			{
				std::cout << " ";
			}
			std::cout << " ";
		}
		SetConsoleTextAttribute(col, KOLORDEFAULT);
		std::cout << "| ";
		if (this->wydarzenia.size() > i)
			std::cout << this->wydarzenia[i];
		std::cout << "\n";
	}
	col = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	for (int j = 0; j < (szerokosc+1)*2; j++)
		std::cout << "-";

	std::cout << "\nVincenzo Piras 193218\nLegenda: \nstrzalki - ruch, spacja - umiejetnosc, f - zakoncz program, s - zapisz do pliku, l - wczytaj z pliku\n";
	SetConsoleTextAttribute(col, KOLORCZLOWIEK);
	std::cout << "H";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - czlowiek, ";
	SetConsoleTextAttribute(col, KOLORWILK);
	std::cout << "W";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - wilk, ";
	SetConsoleTextAttribute(col, KOLOROWCA);
	std::cout << "O";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - owca, ";
	SetConsoleTextAttribute(col, KOLORLIS);
	std::cout << "L";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - lis\n";
	SetConsoleTextAttribute(col, KOLORZOLW);
	std::cout << "Z";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - zolw, ";
	SetConsoleTextAttribute(col, KOLORANTYLOPA);
	std::cout << "A";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - antylopa, ";
	SetConsoleTextAttribute(col, KOLORTRAWA);
	std::cout << "T";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - trawa, ";
	SetConsoleTextAttribute(col, KOLORMLECZ);
	std::cout << "M";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - mlecz\n";
	SetConsoleTextAttribute(col, KOLORGUARANA);
	std::cout << "G";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - guarana, ";
	SetConsoleTextAttribute(col, KOLORWILCZEJAGODY);
	std::cout << "J";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - wilcze jagody, ";
	SetConsoleTextAttribute(col, KOLORBARSZCZSOS);
	std::cout << "B";
	SetConsoleTextAttribute(col, KOLORDEFAULT);
	std::cout << " - barszcz sosnowskiego\n";

	std::cout << this->komunikat << "\n";
	this->komunikat = "";

	while (i < this->wydarzenia.size())
	{
		std::cout << wydarzenia[i];
		i++;
	}

	this->wydarzenia.clear();

}

void Swiat::addToList(Organizm* organizm)
{
	int i = organizm->GetInicjatywa();
	if (i < 2)
	{
		kolejnosc[i].push_back(organizm);
	}
	else if (i == 5)
	{
		kolejnosc[3].push_back(organizm);
	}
	else if (i == 7)
	{
		kolejnosc[4].push_back(organizm);
	}
	else
	{
		kolejnosc[2].push_back(organizm);
	}
}

void Swiat::deletefromList(Organizm* organizm)
{
	int i = organizm->GetInicjatywa();
	int j = 0;

	Czlowiek* test = dynamic_cast<Czlowiek*>(organizm);
	if (test != nullptr)
		this->czlowiekzyje = false;

	if (i < 2)
	{
		while (j < kolejnosc[i].size())
		{
			if (kolejnosc[i][j] != organizm)
				j++;
			else
				kolejnosc[i].erase(kolejnosc[i].begin() + j);
		}
	}
	else if (i == 5)
	{
		while (j < kolejnosc[3].size())
		{
			if (kolejnosc[3][j] != organizm)
				j++;
			else
				kolejnosc[3].erase(kolejnosc[3].begin() + j);
		}
	}
	else if (i == 7)
	{
		while (j < kolejnosc[4].size())
		{
			if (kolejnosc[4][j] != organizm)
				j++;
			else
				kolejnosc[4].erase(kolejnosc[4].begin() + j);
		}
	}
	else
	{
		while (j < kolejnosc[2].size())
		{
			if (kolejnosc[2][j] != organizm)
				j++;
			else
				kolejnosc[2].erase(kolejnosc[2].begin() + j);
		}
	}
}
int Swiat::Getszerokosc() const
{
	return szerokosc;
}
int Swiat::Getwyskosc() const
{
	return wysokosc;
}

void Swiat::Setkierunek(int kierunek)
{
	this->kierunek_ruchu = kierunek;
}
int Swiat::Getkierunek() const
{
	return this->kierunek_ruchu;
}

void Swiat::SetOrganizmy(int x, int y, Organizm* n)
{
	this->organizmy[y * this->Getszerokosc() + x] = n;
}
Organizm* Swiat::GetOrganizmy(int x, int y) const
{
	return this->organizmy[y* this->Getszerokosc() + x];
}

void Swiat::WyzerujRundy()
{
	this->rundy = 0;
}
void Swiat::KolejnaRunda()
{
	this->rundy++;
}
int Swiat::GetRundy()
{
	return this->rundy;
}

void Swiat::SetRundy(int r)
{
	this->rundy = r;
}

bool Swiat::CzyAktywna()
{
	return this->aktywna;
}
void Swiat::ZmienAktywna()
{
	if (this->aktywna)
		this->aktywna = false;
	else
		this->aktywna = true;
}


void Swiat::dodajWydarzenie(Organizm* organizm1, Organizm* organizm2, int kod)
{
	std::string s = "";

	if (organizm1 != nullptr)
		s += organizm1->UczestnikWydarzenia();

	if (kod == ROZMNAZANIE)
		s += " rozmnazyl sie z ";
	else if (kod == ROZPRZESTRZENIANIE)
		s += " rozprzestrzenil sie";
	else if (kod == KILL)
		s += " zjadl ";
	else if (kod == ODBITY)
		s += " odbil atak ";
	else if (kod == UCIEKA)
		s += " ucieka od ";
	else if (kod == UMIEJETNOSC_A)
		s += "Czlowiek uzyl umiejetnosci";
	else if (kod == UMIEJETNOSC_N)
		s += "Umiejetnosc przestala dzialac";

	if (organizm2 != nullptr)
		s += organizm2->UczestnikWydarzenia();

	this->wydarzenia.push_back(s);
}

void Swiat::save()
{
	FILE* plik;
	fopen_s(&plik, "save.bin", "w");
	if (plik != NULL)
	{
		/*
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
		*/
		int d = this->wysokosc;
		fwrite(&d, sizeof(int), 1, plik);
		d = this->szerokosc;
		fwrite(&d, sizeof(int), 1, plik);
		d = this->kierunek_ruchu;
		fwrite(&d, sizeof(int), 1, plik);
		d = this->rundy;
		fwrite(&d, sizeof(int), 1, plik);
		bool b = this->aktywna;
		fwrite(&b, sizeof(bool), 1, plik);
		b = this->czlowiekzyje;
		fwrite(&b, sizeof(bool), 1, plik);

		int size = 0;
		for (int i = 0; i < ILIST; i++)
			size += kolejnosc[i].size();

		fwrite(&size, sizeof(int), 1, plik);

		fclose(plik);
		//fopen_s(&plik, "org.bin", "w");
		std::ofstream outfile;
		outfile.open("output.txt", std::ios::out);
		if (outfile) {
			for (int i = ILIST - 1; i >= 0; i--)
			{
				int j = 0;
				while (j < kolejnosc[i].size())
				{
					/*
					int tmp = kolejnosc[i][j]->GetId();
					fwrite(&tmp, sizeof(int), 1, plik);
					tmp = kolejnosc[i][j]->GetX();
					fwrite(&tmp, sizeof(int), 1, plik);
					tmp = kolejnosc[i][j]->GetY();
					fwrite(&tmp, sizeof(int), 1, plik);
					tmp = kolejnosc[i][j]->GetSila();
					fwrite(&tmp, sizeof(int), 1, plik);
					tmp = kolejnosc[i][j]->GetRundy();
					fwrite(&tmp, sizeof(int), 1, plik);
					bool tmp2 = kolejnosc[i][j]->GetDZ();
					fwrite(&tmp2, sizeof(bool), 1, plik);
					*/

					int tmp = kolejnosc[i][j]->GetId();
					outfile << tmp << " ";
					tmp = kolejnosc[i][j]->GetX();
					outfile << tmp << " ";
					tmp = kolejnosc[i][j]->GetY();
					outfile << tmp << " ";
					tmp = kolejnosc[i][j]->GetSila();
					outfile << tmp << " ";
					tmp = kolejnosc[i][j]->GetRundy();
					outfile << tmp << " ";
					bool tmp2 = kolejnosc[i][j]->GetDZ();
					outfile << tmp2 << " ";

					j++;
				}
			}
		}
		outfile.close();
		//fclose(plik);
	}


}
void Swiat::load()
{
	FILE* plik;
	fopen_s(&plik, "save.bin", "r");
	if (plik != NULL)
	{
		for (int i = 0; i < szerokosc * wysokosc; i++)
		{
			if (organizmy[i] != nullptr)
				delete organizmy[i];
		}

		delete[] organizmy;


		for (int i = 0; i < ILIST; i++)
			this->kolejnosc[i].clear();


		int szerokoscr, wysokoscr, kierunek_ruchur, rundyr, size;
		bool aktywnar, czlowiekzyjer;

		fread(&wysokoscr, sizeof(int), 1, plik);
		fread(&szerokoscr, sizeof(int), 1, plik);
		fread(&kierunek_ruchur, sizeof(int), 1, plik);
		fread(&rundyr, sizeof(int), 1, plik);
		fread(&aktywnar, sizeof(bool), 1, plik);
		fread(&czlowiekzyjer, sizeof(bool), 1, plik);
		fread(&size, sizeof(int), 1, plik);

		fclose(plik);


		this->wysokosc = wysokoscr;
		this->szerokosc = szerokoscr;
		this->kierunek_ruchu = kierunek_ruchur;
		this->rundy = rundyr;
		this->aktywna = aktywna;
		this->czlowiekzyje = czlowiekzyjer;


		this->organizmy = new Organizm * [this->Getwyskosc() * this->Getszerokosc()];

		for (int i = 0; i < this->Getwyskosc() * this->Getszerokosc(); i++)
		{
			organizmy[i] = nullptr;
		}

		int id = 0, x = 0, y = 0, sila = 0, rundy = 0;
		bool dz = 0;
		Organizm* org = nullptr;

		//fopen_s(&plik, "org.bin", "r");
		std::ifstream infile("output.txt");
		if (infile)
		{

			for (int i = 0; i < size; i++)
			{
				/*
				fread(&id, sizeof(int), 1, plik);
				fread(&x, sizeof(int), 1, plik);
				fread(&y, sizeof(int), 1, plik);
				fread(&sila, sizeof(int), 1, plik);
				fread(&rundy, sizeof(int), 1, plik);
				fread(&dz, sizeof(bool), 1, plik);
				*/
				infile >> id >> x >> y >> sila >> rundy >> dz;
				if (id == IDCZLOWIEK)
					org = new Czlowiek(x, y, dz, this);
				else if (id == IDWILK)
					org = new Wilk(x, y, dz, this);
				else if (id == IDOWCA)
					org = new Owca(x, y, dz, this);
				else if (id == IDLIS)
					org = new Lis(x, y, dz, this);
				else if (id == IDZOLW)
					org = new Zolw(x, y, dz, this);
				else if (id == IDANTYLOPA)
					org = new Antylopa(x, y, dz, this);
				else if (id == IDTRAWA)
					org = new Trawa(x, y, dz, this);
				else if (id == IDMLECZ)
					org = new Mlecz(x, y, dz, this);
				else if (id == IDGUARANA)
					org = new Guarana(x, y, dz, this);
				else if (id == IDWILCZEJAGODY)
					org = new WilczeJagody(x, y, dz, this);
				else if (id == IDBARSZCZSOS)
					org = new BarszczSosnowskiego(x, y, dz, this);

				if (org != nullptr)
				{
					org->SetSila(sila);
					org->SetRundy(rundy);
				}
			}
			infile.close();
			//fclose(plik);
		}
	}
	else
	{
		this->komunikat = "Nie ma save";
	}
}

Swiat::~Swiat()
{
	if (organizmy != nullptr)
	{
		for (int i = 0; i < szerokosc * wysokosc; i++)
		{
			if (organizmy[i] != nullptr)
				delete organizmy[i];
		}
		delete[] organizmy;
	}
	for (int i = 0; i < ILIST; i++)
		this->kolejnosc[i].clear();
}