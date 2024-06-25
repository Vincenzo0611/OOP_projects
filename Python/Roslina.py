from abc import abstractmethod
from Organizm import Organizm

class Roslina(Organizm):
    def __init__(self, x, y, dz, sila, inicjatywa, idi, swiat):
        super().__init__(x, y, sila, inicjatywa, idi, dz, swiat)
    @abstractmethod
    def rysowanie(self):
        pass
    @abstractmethod
    def nazwa(self):
        pass
    @abstractmethod
    def stworz_nowe_roslina(self, x, y, sw):
        pass
    def akcja(self):
        q = 0
        for r in range(self.Ileprob()):
            szansa = self.swiat.rand.randint(0, self.SzansaNaRozsiew())
            q = 0
            while q == 0:
                random = self.swiat.rand.randint(0, self.swiat.Getuklad_planszy())
                if (szansa != 0):
                    q = 1
                    random = self.swiat.BRAK
                if (random == self.swiat.GORA and self.GetY() != 0):
                    q = 1
                    if (self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1) == None):
                        self.swiat.dodajWydarzenie(self, None, self.swiat.ROZPRZESTRZENIANIE)
                        self.zasadzGora()
                elif (random == self.swiat.DOL and self.GetY() != self.swiat.Getwyskosc() - 1):
                    q = 1
                    if (self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1) == None):
                        self.swiat.dodajWydarzenie(self, None, self.swiat.ROZPRZESTRZENIANIE)
                        self.zasadzDol()
                elif (random == self.swiat.PRAWO and self.GetX() != self.swiat.Getszerokosc() - 1):
                    q = 1
                    if (self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()) == None):
                        self.swiat.dodajWydarzenie(self, None, self.swiat.ROZPRZESTRZENIANIE)
                        self.zasadzPrawo()
                elif (random == self.swiat.LEWO and self.GetX() != 0):
                    q = 1
                    if (self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()) == None):
                        self.swiat.dodajWydarzenie(self, None, self.swiat.ROZPRZESTRZENIANIE)
                        self.zasadzLewo()
                elif (random == self.swiat.LEWOG):
                    if(self.GetX() % 2 == 0 and self.GetX() != 0 and self.GetY() != 0):
                        q = 1
                        if (self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() - 1) == None):
                            self.swiat.dodajWydarzenie(self, None, self.swiat.ROZPRZESTRZENIANIE)
                            self.zasadzLewoG()
                    elif (self.GetX() % 2 != 0 and self.GetX() != 0 and self.GetY() != self.swiat.Getwyskosc() - 1):
                        q = 1
                        if (self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() + 1) == None):
                            self.swiat.dodajWydarzenie(self, None, self.swiat.ROZPRZESTRZENIANIE)
                            self.zasadzLewoG()
                elif (random == self.swiat.PRAWOG):
                    if(self.GetX() % 2 == 0 and self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != 0):
                        q = 1
                        if (self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() - 1) == None):
                            self.swiat.dodajWydarzenie(self, None, self.swiat.ROZPRZESTRZENIANIE)
                            self.zasadzPrawoG()
                    elif (self.GetX() % 2 != 0 and self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != self.swiat.Getwyskosc() - 1):
                        q = 1
                        if (self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() + 1) == None):
                            self.swiat.dodajWydarzenie(self, None, self.swiat.ROZPRZESTRZENIANIE)
                            self.zasadzPrawoG()

    def kolizja(self, organizm, kierunek):
        pass
    def Ileprob(self):
        return 1
    def SzansaNaRozsiew(self):
        return 30

    def zasadzGora(self):
        self.stworz_nowe_roslina(self.GetX(), self.GetY() - 1, self.swiat)
    def zasadzDol(self):
        self.stworz_nowe_roslina(self.GetX(), self.GetY() + 1, self.swiat)
    def zasadzLewo(self):
        self.stworz_nowe_roslina(self.GetX() - 1, self.GetY(), self.swiat)
    def zasadzPrawo(self):
        self.stworz_nowe_roslina(self.GetX() + 1, self.GetY(), self.swiat)
    def zasadzLewoG(self):
        if(self.GetX() % 2 == 0):
            self.stworz_nowe_roslina(self.GetX() - 1, self.GetY() - 1, self.swiat)
        else:
            self.stworz_nowe_roslina(self.GetX() - 1, self.GetY() + 1, self.swiat)
    def zasadzPrawoG(self):
        if(self.GetX() % 2 == 0):
            self.stworz_nowe_roslina(self.GetX() + 1, self.GetY() - 1, self.swiat)
        else:
            self.stworz_nowe_roslina(self.GetX() + 1, self.GetY() + 1, self.swiat)