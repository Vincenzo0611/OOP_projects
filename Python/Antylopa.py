from Zwierze import Zwierze
from Organizm import Organizm

class Antylopa(Zwierze):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 4, 4, swiat.IDANTYLOPA, swiat)

    def rysowanie(self):
        return "#a6730c"

    def nazwa(self):
        return "Antylopa"

    def kolizja(self, organizm, kierunek):
        if self.__class__ == organizm.__class__:
            super().kolizja(organizm, kierunek)
        else:
            random = self.swiat.rand.randint(0, 2)
            if random == 0:
                if organizm.GetY() != 0 and self.swiat.GetOrganizmy(organizm.GetX(), organizm.GetY() - 1) == None:
                    self.swiat.dodajWydarzenie(self, organizm, self.swiat.UCIEKA)
                    self.idzXY(organizm.GetX(), organizm.GetY() - 1)
                elif organizm.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(organizm.GetX(), organizm.GetY() + 1) == None:
                    self.swiat.dodajWydarzenie(self, organizm, self.swiat.UCIEKA)
                    self.idzXY(organizm.GetX(), organizm.GetY() + 1)
                elif organizm.GetX() != 0 and self.swiat.GetOrganizmy(organizm.GetX() - 1, organizm.GetY()) == None:
                    self.swiat.dodajWydarzenie(self, organizm, self.swiat.UCIEKA)
                    self.idzXY(organizm.GetX() - 1, organizm.GetY())
                elif organizm.GetX() != self.swiat.Getszerokosc() - 1 and self.swiat.GetOrganizmy(organizm.GetX() + 1, organizm.GetY()) == None:
                    self.swiat.dodajWydarzenie(self, organizm, self.swiat.UCIEKA)
                    self.idzXY(organizm.GetX() + 1, organizm.GetY())
                elif self.swiat.Getuklad_planszy() == 6:
                    if self.GetX() % 2 == 0:
                        if organizm.GetX() != self.swiat.Getszerokosc() - 1 and organizm.GetY() != 0 and self.swiat.GetOrganizmy(organizm.GetX() + 1, organizm.GetY() - 1) == None:
                            self.swiat.dodajWydarzenie(self, organizm, self.swiat.UCIEKA)
                            self.idzXY(organizm.GetX() + 1, organizm.GetY() - 1)
                        elif organizm.GetX() != 0 and organizm.GetY() != 0 and self.swiat.GetOrganizmy(organizm.GetX() - 1, organizm.GetY() - 1) == None:
                            self.swiat.dodajWydarzenie(self, organizm, self.swiat.UCIEKA)
                            self.idzXY(organizm.GetX() - 1, organizm.GetY() - 1)
                    else:
                        if organizm.GetX() != self.swiat.Getszerokosc() - 1 and organizm.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(organizm.GetX() + 1, organizm.GetY() + 1) == None:
                            self.swiat.dodajWydarzenie(self, organizm, self.swiat.UCIEKA)
                            self.idzXY(organizm.GetX() + 1, organizm.GetY() + 1)
                        elif organizm.GetX() != 0 and organizm.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(organizm.GetX() - 1, organizm.GetY() + 1) == None:
                            self.swiat.dodajWydarzenie(self, organizm, self.swiat.UCIEKA)
                            self.idzXY(organizm.GetX() - 1, organizm.GetY() + 1)
            else:
                super().kolizja(organizm, kierunek)

    def stworz_nowe_zwierze(self, x, y, sw):
        org = Antylopa(x, y, True, sw)

    def ileRuchu(self):
        return 2

    def CzyUcieka(self):
        random = self.swiat.rand.randint(0, 2)
        if random == 0:
            return True
        return False
