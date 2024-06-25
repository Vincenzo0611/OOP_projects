from abc import abstractmethod
from Organizm import Organizm


class Zwierze(Organizm):
    def __init__(self, x, y, dz, sila, inicjatywa, idi, swiat):
        super().__init__(x, y, sila, inicjatywa, idi, dz, swiat)

    @abstractmethod
    def stworz_nowe_zwierze(self, x, y, sw):
        pass

    def akcja(self):
        for r in range(self.ileRuchu()):
            if self.swiat.GetOrganizmy(self.GetX(), self.GetY()) is None:
                return

            if self is None:
                return

            if not self.CzyRuszac():
                return

            q = 0
            while q == 0:
                random = self.swiat.rand.randint(0, self.swiat.Getuklad_planszy() - 1)

                if random == self.swiat.GORA and self.GetY() != 0:
                    q = 1
                    if self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1) is None:
                        self.idzGora()
                    elif self.CzyWech(self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1)):
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1), self.swiat.GORA)

                elif random == self.swiat.DOL and self.GetY() != self.swiat.Getwyskosc() - 1:
                    q = 1
                    if self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1) is None:
                        self.idzDol()
                    elif self.CzyWech(self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1)):
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1), self.swiat.DOL)

                elif random == self.swiat.PRAWO and self.GetX() != self.swiat.Getszerokosc() - 1:
                    q = 1
                    if self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()) is None:
                        self.idzPrawo()
                    elif self.CzyWech(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY())):
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()), self.swiat.PRAWO)

                elif random == self.swiat.LEWO and self.GetX() != 0:
                    q = 1
                    if self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()) is None:
                        self.idzLewo()
                    elif self.CzyWech(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY())):
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()), self.swiat.LEWO)

                elif random == self.swiat.LEWOG:
                    if self.GetX() % 2 == 0 and self.GetX() != 0 and self.GetY() != 0:
                        q = 1
                        if self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() - 1) is None:
                            self.idzLewoG()
                        elif self.CzyWech(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() - 1)):
                            self.kolizja(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() - 1), self.swiat.LEWOG)

                    elif self.GetX() % 2 != 0 and self.GetX() != 0 and self.GetY() != self.swiat.Getwyskosc() - 1:
                        q = 1
                        if self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() + 1) is None:
                            self.idzLewoG()
                        elif self.CzyWech(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() + 1)):
                            self.kolizja(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() + 1), self.swiat.LEWOG)

                elif random == self.swiat.PRAWOG:
                    if self.GetX() % 2 == 0 and self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != 0:
                        q = 1
                        if self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() - 1) is None:
                            self.idzPrawoG()
                        elif self.CzyWech(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() - 1)):
                            self.kolizja(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() - 1), self.swiat.PRAWOG)

                    elif self.GetX() % 2 != 0 and self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != self.swiat.Getwyskosc() - 1:
                        q = 1
                        if self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() + 1) is None:
                            self.idzPrawoG()
                        elif self.CzyWech(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() + 1)):
                            self.kolizja(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() + 1), self.swiat.PRAWOG)

    def kolizja(self, organizm, kierunek):
        if organizm.CzyNieZabija() or self.GetId() == self.swiat.IDCYBEROWCA:
            if self.__class__ == organizm.__class__:
                if self.GetRundy() >= self.swiat.CZAS_ROZMNAZANIA and organizm.GetRundy() >= self.swiat.CZAS_ROZMNAZANIA:
                    if self.GetY() != 0 and self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1) == None:
                        self.WyzerujRundy()
                        organizm.WyzerujRundy()
                        self.swiat.dodajWydarzenie(self, organizm, self.swiat.ROZMNAZANIE)
                        self.stworz_nowe_zwierze(self.GetX(), self.GetY() - 1, self.swiat)
                    elif self.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(self.GetX(),
                                                                                                self.GetY() + 1) == None:
                        self.WyzerujRundy()
                        organizm.WyzerujRundy()
                        self.swiat.dodajWydarzenie(self, organizm, self.swiat.ROZMNAZANIE)
                        self.stworz_nowe_zwierze(self.GetX(), self.GetY() + 1, self.swiat)
                    elif self.GetX() != self.swiat.Getszerokosc() - 1 and self.swiat.GetOrganizmy(self.GetX() + 1,
                                                                                                  self.GetY()) == None:
                        self.WyzerujRundy()
                        organizm.WyzerujRundy()
                        self.swiat.dodajWydarzenie(self, organizm, self.swiat.ROZMNAZANIE)
                        self.stworz_nowe_zwierze(self.GetX() + 1, self.GetY(), self.swiat)
                    elif self.GetX() != 0 and self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()) == None:
                        self.WyzerujRundy()
                        organizm.WyzerujRundy()
                        self.swiat.dodajWydarzenie(self, organizm, self.swiat.ROZMNAZANIE)
                        self.stworz_nowe_zwierze(self.GetX() - 1, self.GetY(), self.swiat)
                    elif self.GetX() % 2 == 0 and self.swiat.Getuklad_planszy() == 6:
                        if self.GetX() != 0 and self.GetY() != 0 and self.swiat.GetOrganizmy(self.GetX() - 1,
                                                                                             self.GetY() - 1) == None:
                            self.WyzerujRundy()
                            organizm.WyzerujRundy()
                            self.swiat.dodajWydarzenie(self, organizm, self.swiat.ROZMNAZANIE)
                            self.stworz_nowe_zwierze(self.GetX() - 1, self.GetY() - 1, self.swiat)
                        elif self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != 0 and self.swiat.GetOrganizmy(
                                self.GetX() + 1, self.GetY() - 1) == None:
                            self.WyzerujRundy()
                            organizm.WyzerujRundy()
                            self.swiat.dodajWydarzenie(self, organizm, self.swiat.ROZMNAZANIE)
                    elif self.GetX() % 2 != 0 and self.swiat.Getuklad_planszy() == 6:
                        if self.GetX() != 0 and self.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(
                                self.GetX() - 1, self.GetY() + 1) == None:
                            self.WyzerujRundy()
                            organizm.WyzerujRundy()
                            self.swiat.dodajWydarzenie(self, organizm, self.swiat.ROZMNAZANIE)
                            self.stworz_nowe_zwierze((self.GetX() - 1), self.GetY() + 1, self.swiat)
                        elif self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(
                                self.GetX() + 1, self.GetY() + 1) == None:
                            self.WyzerujRundy()
                            organizm.WyzerujRundy()
                            self.swiat.dodajWydarzenie(self, organizm, self.swiat.ROZMNAZANIE)
                            self.stworz_nowe_zwierze((self.GetX() + 1), self.GetY() + 1, self.swiat)
            elif not organizm.CzyUcieka():
                if self.GetSila() < organizm.GetSila():
                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.KILL)
                    x = self.GetX()
                    y = self.GetY()
                    self.swiat.deletefromList(self)
                    self.swiat.SetOrganizmy(x, y, None)
                elif organizm.CzyOdbijaAtak(self) == 0:
                    if organizm.CzyDodajeSily():
                        self.SetSila(self.GetSila() + 3)
                    self.swiat.dodajWydarzenie(self, organizm, self.swiat.KILL)
                    x = organizm.GetX()
                    y = organizm.GetY()
                    self.swiat.deletefromList(organizm)
                    self.swiat.SetOrganizmy(x, y, None)
                    if kierunek == self.swiat.GORA:
                        self.idzGora()
                    elif kierunek == self.swiat.DOL:
                        self.idzDol()
                    elif kierunek == self.swiat.LEWO:
                        self.idzLewo()
                    elif kierunek == self.swiat.PRAWO:
                        self.idzPrawo()
                    elif kierunek == self.swiat.LEWOG:
                        self.idzLewoG()
                    elif kierunek == self.swiat.PRAWOG:
                        self.idzPrawoG()
                elif organizm.CzyOdbijaAtak(self) == 1:
                    x = organizm.GetX()
                    y = organizm.GetY()
                    k = 0
                    while k == 0:
                        r = self.swiat.rand.randint(0, self.swiat.Getuklad_planszy())
                        if r == self.swiat.GORA and y != 0:
                            if self.swiat.GetOrganizmy(x, y - 1) == None:
                                self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                k = 1
                                self.idzXY(x, y - 1)
                            elif self.swiat.GetOrganizmy(x, y - 1) == self:
                                self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                k = 1
                        elif r == self.swiat.DOL and y != self.swiat.Getwyskosc() - 1:
                            if self.swiat.GetOrganizmy(x, y + 1) == None:
                                self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                k = 1
                                self.idzXY(x, y + 1)
                            elif self.swiat.GetOrganizmy(x, y + 1) == self:
                                self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                k = 1
                        elif r == self.swiat.PRAWO and x != self.swiat.Getszerokosc() - 1:
                            if self.swiat.GetOrganizmy(x + 1, y) == None:
                                self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                k = 1
                                self.idzXY(x + 1, y)
                            elif self.swiat.GetOrganizmy(x + 1, y) == self:
                                self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                k = 1
                        elif r == self.swiat.LEWO and x != 0:
                            if self.swiat.GetOrganizmy(x - 1, y) == None:
                                self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                k = 1
                                self.idzXY(x - 1, y)
                            elif self.swiat.GetOrganizmy(x - 1, y) == self:
                                self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                k = 1
                        elif r == self.swiat.LEWOG:
                            if x % 2 == 0 and x != 0 and y != 0:
                                if self.swiat.GetOrganizmy(x - 1, y - 1) == None:
                                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                    k = 1
                                    self.idzXY(x - 1, y - 1)
                                elif self.swiat.GetOrganizmy(x - 1, y - 1) == self:
                                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                    k = 1
                            elif x % 2 != 0 and x != 0 and y != self.swiat.Getwyskosc() - 1:
                                if self.swiat.GetOrganizmy(x - 1, y + 1) == None:
                                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                    k = 1
                                    self.idzXY(x - 1, y + 1)
                                elif self.swiat.GetOrganizmy(x - 1, y + 1) == self:
                                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                    k = 1
                        elif r == self.swiat.PRAWOG:
                            if (x % 2 == 0 and x != self.swiat.Getszerokosc() - 1 and y != 0):
                                if (self.swiat.GetOrganizmy(x + 1, y - 1) == None):
                                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                    k = 1
                                    self.idzXY(x + 1, y - 1)
                                elif (self.swiat.GetOrganizmy(x + 1, y - 1) == self):
                                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                    k = 1
                            elif (
                                    x % 2 != 0 and x != self.swiat.Getszerokosc() - 1 and y != self.swiat.Getwyskosc() - 1):
                                if (self.swiat.GetOrganizmy(x + 1, y + 1) == None):
                                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                    k = 1
                                    self.idzXY(x + 1, y + 1)
                                elif (self.swiat.GetOrganizmy(x + 1, y + 1) == self):
                                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
                                    k = 1
                elif (organizm.CzyOdbijaAtak(self) == 2):
                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.ODBITY)
            else:
                ucieka = False
                if (organizm.GetY() != 0 and self.swiat.GetOrganizmy(organizm.GetX(), organizm.GetY() - 1) == None):
                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.UCIEKA)
                    ucieka = True
                    organizm.idzXY(organizm.GetX(), organizm.GetY() - 1)
                elif (organizm.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(organizm.GetX(),
                                                                                                 organizm.GetY() + 1) == None):
                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.UCIEKA)
                    ucieka = True
                    organizm.idzXY(organizm.GetX(), organizm.GetY() + 1)
                elif (organizm.GetX() != 0 and self.swiat.GetOrganizmy(organizm.GetX() - 1, organizm.GetY()) == None):
                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.UCIEKA)
                    ucieka = True
                    organizm.idzXY(organizm.GetX() - 1, organizm.GetY())
                elif (organizm.GetX() != self.swiat.Getszerokosc() - 1 and self.swiat.GetOrganizmy(organizm.GetX() + 1,
                                                                                                   organizm.GetY()) == None):
                    self.swiat.dodajWydarzenie(organizm, self, self.swiat.UCIEKA)
                    ucieka = True
                    organizm.idzXY(organizm.GetX() + 1, organizm.GetY())
                elif (organizm.GetX() % 2 == 0 and self.swiat.Getuklad_planszy() == 6):
                    if (
                            organizm.GetX() != self.swiat.Getszerokosc() - 1 and organizm.GetY() != 0 and self.swiat.GetOrganizmy(
                            organizm.GetX() + 1, organizm.GetY() - 1) == None):
                        self.swiat.dodajWydarzenie(organizm, self, self.swiat.UCIEKA)
                        ucieka = True
                        organizm.idzXY(organizm.GetX() + 1, organizm.GetY() - 1)
                    elif (organizm.GetX() != 0 and organizm.GetY() != 0 and self.swiat.GetOrganizmy(organizm.GetX() - 1,
                                                                                                    organizm.GetY() - 1) == None):
                        self.swiat.dodajWydarzenie(organizm, self, self.swiat.UCIEKA)
                        ucieka = True
                        organizm.idzXY(organizm.GetX() - 1, organizm.GetY() - 1)
                elif (organizm.GetX() % 2 != 0 and self.swiat.Getuklad_planszy() == 6):
                    if (
                            organizm.GetX() != self.swiat.Getszerokosc() - 1 and organizm.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(
                            organizm.GetX() + 1, organizm.GetY() + 1) == None):
                        self.swiat.dodajWydarzenie(organizm, self, self.swiat.UCIEKA)
                        ucieka = True
                        organizm.idzXY(organizm.GetX() + 1, organizm.GetY() + 1)
                    elif (
                            organizm.GetX() != 0 and organizm.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(
                            organizm.GetX() - 1, organizm.GetY() + 1) == None):
                        self.swiat.dodajWydarzenie(organizm, self, self.swiat.UCIEKA)
                        ucieka = True
                        organizm.idzXY(organizm.GetX() - 1, organizm.GetY() + 1)
                if (not ucieka):
                    self.swiat.dodajWydarzenie(self, organizm, self.swiat.KILL)
                    x = organizm.GetX()
                    y = organizm.GetY()
                    self.swiat.deletefromList(organizm)
                    self.swiat.SetOrganizmy(x, y, None)
                if (kierunek == self.swiat.GORA):
                    self.idzGora()
                elif (kierunek == self.swiat.DOL):
                    self.idzDol()
                elif (kierunek == self.swiat.LEWO):
                    self.idzLewo()
                elif (kierunek == self.swiat.PRAWO):
                    self.idzPrawo()
                elif (kierunek == self.swiat.PRAWOG):
                    self.idzPrawoG()
                elif (kierunek == self.swiat.LEWOG):
                    self.idzLewoG()
        else:
            if(self.GetId() != self.swiat.IDCYBEROWCA):
                x = self.GetX()
                y = self.GetY()
                self.swiat.deletefromList(self)
                self.swiat.SetOrganizmy(x, y, None)

    @abstractmethod
    def rysowanie(self):
        pass

    @abstractmethod
    def nazwa(self):
        pass

    def CzyRuszac(self):
        return True

    def ileRuchu(self):
        return 1

    def idzGora(self):
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), None)
        self.SetY(self.GetY() - 1)
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), self)

    def idzDol(self):
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), None)
        self.SetY(self.GetY() + 1)
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), self)

    def idzLewo(self):
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), None)
        self.SetX(self.GetX() - 1)
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), self)

    def idzPrawo(self):
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), None)
        self.SetX(self.GetX() + 1)
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), self)

    def idzLewoG(self):
        if (self.GetX() % 2 == 0):
            self.swiat.SetOrganizmy(self.GetX(), self.GetY(), None)
            self.SetX(self.GetX() - 1)
            self.SetY(self.GetY() - 1)
            self.swiat.SetOrganizmy(self.GetX(), self.GetY(), self)
        else:
            self.swiat.SetOrganizmy(self.GetX(), self.GetY(), None)
            self.SetX(self.GetX() - 1)
            self.SetY(self.GetY() + 1)
            self.swiat.SetOrganizmy(self.GetX(), self.GetY(), self)

    def idzPrawoG(self):
        if (self.GetX() % 2 == 0):
            self.swiat.SetOrganizmy(self.GetX(), self.GetY(), None)
            self.SetX(self.GetX() + 1)
            self.SetY(self.GetY() - 1)
            self.swiat.SetOrganizmy(self.GetX(), self.GetY(), self)
        else:
            self.swiat.SetOrganizmy(self.GetX(), self.GetY(), None)
            self.SetX(self.GetX() + 1)
            self.SetY(self.GetY() + 1)
            self.swiat.SetOrganizmy(self.GetX(), self.GetY(), self)