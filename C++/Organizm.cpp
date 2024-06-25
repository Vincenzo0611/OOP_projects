#include "Organizm.h"

Organizm::Organizm(int x, int y, int sila, int inicjatywa, int id, Swiat* swiat)
{
	this->id = id;
	this->swiat = swiat;
	this->x = x;
	this->y = y;
	this->sila = sila;
	this->inicjatywa = inicjatywa;
	this->dopiero_zrodzony = false;
	this->rundy = 0;
	this->swiat->SetOrganizmy(this->GetX(), this->GetY(), this);
	swiat->addToList(this);
}

Organizm::Organizm(int x, int y, int sila, int inicjatywa, int id, bool dz, Swiat* swiat)
{
	this->id = id;
	this->swiat = swiat;
	this->x = x;
	this->y = y;
	this->sila = sila;
	this->inicjatywa = inicjatywa;
	this->dopiero_zrodzony = dz;
	this->rundy = 0;
	this->swiat->SetOrganizmy(this->GetX(), this->GetY(), this);
	swiat->addToList(this);
}

int Organizm::CzyOdbijaAtak(Organizm* atakujacy)
{
	return 0;
}

bool Organizm::CzyWech(Organizm* organizm)
{
	return true;
}

bool Organizm::CzyDodajeSily()
{
	return false;
}

bool Organizm::CzyUcieka()
{
	return false;
}

bool Organizm::CzyNieZabija()
{
	return true;
}

void Organizm::idzXY(int x, int y)
{
	this->swiat->SetOrganizmy(this->GetX(), this->GetY(), nullptr);
	this->SetX(x);
	this->SetY(y);
	this->swiat->SetOrganizmy(this->GetX(), this->GetY(), this);
}

int Organizm::GetY() const
{
	return y;
}
int Organizm::GetX() const
{
	return x;
}
int Organizm::GetSila() const
{
	return sila;
}
int Organizm::GetInicjatywa() const
{
	return inicjatywa;
}
int Organizm::GetId() const
{
	return id;
}
bool Organizm::GetDZ() const
{
	return dopiero_zrodzony;
}
void Organizm::SetX(int x)
{
	this->x = x;
}
void Organizm::SetY(int y)
{
	this->y = y;
}
void Organizm::SetSila(int sila)
{
	this->sila = sila;
}
void Organizm::SetInicjatywa(int inicjatywa)
{
	this->inicjatywa = inicjatywa;
}
void Organizm::SetDZ(bool zrodzony)
{
	this->dopiero_zrodzony = zrodzony;
}


void Organizm::WyzerujRundy()
{
	this->rundy = 0;
}
void Organizm::KolejnaRunda()
{
	this->rundy++;
}
int Organizm::GetRundy()
{
	return this->rundy;
}

void Organizm::SetRundy(int r)
{
	this->rundy = r;
}

std::string Organizm::UczestnikWydarzenia()
{
	std::string result = "";

	result = this->nazwa();

	result += "(";
	result += std::to_string(this->GetX());
	result += ",";
	result += std::to_string(this->GetY());
	result += ")";

	return result;
}

Organizm::~Organizm()
{
}