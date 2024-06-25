from Zwierze import Zwierze

class Czlowiek(Zwierze):
    def __init__(self, x, y, swiat):
        super().__init__(x, y, False, 5, 4, swiat.IDCZLOWIEK, swiat)

    def rysowanie(self):
        return "#18ebd5"

    def nazwa(self):
        return "Czlowiek"

    def akcja(self):
        if self.swiat.Getuklad_planszy() == 4:
            if self.swiat.Getkierunek() == self.swiat.GORA and self.GetY() != 0:
                if self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1) is None:
                    self.idzGora()
                else:
                    self.kolizja(self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1), self.swiat.GORA)
            elif self.swiat.Getkierunek() == self.swiat.DOL and self.GetY() != self.swiat.Getwyskosc() - 1:
                if self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1) is None:
                    self.idzDol()
                else:
                    self.kolizja(self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1), self.swiat.DOL)
            elif self.swiat.Getkierunek() == self.swiat.PRAWO and self.GetX() != self.swiat.Getszerokosc() - 1:
                if self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()) is None:
                    self.idzPrawo()
                else:
                    self.kolizja(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()), self.swiat.PRAWO)
            elif self.swiat.Getkierunek() == self.swiat.LEWO and self.GetX() != 0:
                if self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()) is None:
                    self.idzLewo()
                else:
                    self.kolizja(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()), self.swiat.LEWO)
        else:
            if self.swiat.Getkierunek() == self.swiat.GORA and self.GetY() != 0:
                if self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1) is None:
                    self.idzGora()
                else:
                    self.kolizja(self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1), self.swiat.GORA)
            elif self.swiat.Getkierunek() == self.swiat.DOL and self.GetY() != self.swiat.Getwyskosc() - 1:
                if self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1) is None:
                    self.idzDol()
                else:
                    self.kolizja(self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1), self.swiat.DOL)
            elif self.GetX() % 2 == 0:
                if self.swiat.Getkierunek() == self.swiat.PRAWO and self.GetX() != self.swiat.Getszerokosc() - 1:
                    if self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()) is None:
                        self.idzPrawo()
                    else:
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()), self.swiat.PRAWO)
                elif self.swiat.Getkierunek() == self.swiat.LEWO and self.GetX() != 0:
                    if self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()) is None:
                        self.idzLewo()
                    else:
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()), self.swiat.LEWO)
                elif self.swiat.Getkierunek() == self.swiat.LEWOG and self.GetX() != 0 and self.GetY() != 0:
                    if self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() - 1) is None:
                        self.idzLewoG()
                    else:
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() - 1), self.swiat.LEWOG)
                elif self.swiat.Getkierunek() == self.swiat.PRAWOG and self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != 0:
                    if self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() - 1) is None:
                        self.idzPrawoG()
                    else:
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() - 1), self.swiat.PRAWOG)
            else:
                if self.swiat.Getkierunek() == self.swiat.PRAWO and self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != self.swiat.Getwyskosc() - 1:
                    if self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() + 1) is None:
                        self.idzPrawoG()
                    else:
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() + 1), self.swiat.PRAWOG)
                elif self.swiat.Getkierunek() == self.swiat.LEWO and self.GetX() != 0 and self.GetY() != self.swiat.Getwyskosc() - 1:
                    if self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() + 1) is None:
                        self.idzLewoG()
                    else:
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() + 1), self.swiat.LEWOG)
                elif self.swiat.Getkierunek() == self.swiat.LEWOG and self.GetX() != 0 and self.GetY() != 0:
                    if self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()) is None:
                        self.idzLewo()
                    else:
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()), self.swiat.LEWO)
                elif self.swiat.Getkierunek() == self.swiat.PRAWOG and self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != 0:
                    if self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()) is None:
                        self.idzPrawo()
                    else:
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()), self.swiat.PRAWO)

    def CzyOdbijaAtak(self, atakujacy):
        if self.swiat.CzyAktywna():
            return 1
        return 0

    def stworz_nowe_zwierze(self, x, y, sw):
        pass
