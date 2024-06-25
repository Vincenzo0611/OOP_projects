from Roslina import Roslina
from Organizm import Organizm

class BarszczSosnowskiego(Roslina):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 10, 0, swiat.IDBARSZCZSOS, swiat)

    def rysowanie(self):
        return "#d45454"

    def nazwa(self):
        return "Barszcz Sosnowskiego"

    def akcja(self):
        if self.GetY() != 0 and self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1) is not None and self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1).GetInicjatywa() > 0 and not isinstance(self, type(self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1))) and self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1).GetId() != self.swiat.IDCYBEROWCA:
            organizm = self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1)
            self.swiat.dodajWydarzenie(self, organizm, self.swiat.KILL)
            x = organizm.GetX()
            y = organizm.GetY()
            self.swiat.deletefromList(organizm)
            self.swiat.SetOrganizmy(x, y, None)

        if self.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1) is not None and self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1).GetInicjatywa() > 0 and not isinstance(self, type(self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1))) and self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1).GetId() != self.swiat.IDCYBEROWCA:
            organizm = self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1)
            self.swiat.dodajWydarzenie(self, organizm, self.swiat.KILL)
            x = organizm.GetX()
            y = organizm.GetY()
            self.swiat.deletefromList(organizm)
            self.swiat.SetOrganizmy(x, y, None)

        if self.GetX() != 0 and self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()) is not None and self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()).GetInicjatywa() > 0 and not isinstance(self, type(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()))) and self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()).GetId() != self.swiat.IDCYBEROWCA:
            organizm = self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY())
            self.swiat.dodajWydarzenie(self, organizm, self.swiat.KILL)
            x = organizm.GetX()
            y = organizm.GetY()
            self.swiat.deletefromList(organizm)
            self.swiat.SetOrganizmy(x, y, None)

        if self.GetX() != self.swiat.Getszerokosc() - 1 and self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()) is not None and self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()).GetInicjatywa() > 0 and not isinstance(self, type(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()))) and self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()).GetId() != self.swiat.IDCYBEROWCA:
            organizm = self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY())
            self.swiat.dodajWydarzenie(self, organizm, self.swiat.KILL)
            x = organizm.GetX()
            y = organizm.GetY()
            self.swiat.deletefromList(organizm)
            self.swiat.SetOrganizmy(x, y, None)

        if self.swiat.Getuklad_planszy() == 6:
            if self.GetX() % 2 == 0:
                if self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != 0 and self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() - 1) is not None and self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() - 1).GetInicjatywa() > 0 and not isinstance(self, type(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() - 1))):
                    organizm = self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() - 1)
                    self.swiat.dodajWydarzenie(self, organizm, self.swiat.KILL)
                    x = organizm.GetX()
                    y = organizm.GetY()
                    self.swiat.deletefromList(organizm)
                    self.swiat.SetOrganizmy(x, y, None)

                if self.GetX() != 0 and self.GetY() != 0 and self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() - 1) is not None and self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() - 1).GetInicjatywa() > 0 and not isinstance(self, type(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() - 1))):
                    organizm = self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() - 1)
                    self.swiat.dodajWydarzenie(self, organizm, self.swiat.KILL)
                    x = organizm.GetX()
                    y = organizm.GetY()
                    self.swiat.deletefromList(organizm)
                    self.swiat.SetOrganizmy(x, y, None)
            else:
                if self.GetX() != self.swiat.Getszerokosc() - 1 and self.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() + 1) is not None and self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() + 1).GetInicjatywa() > 0 and not isinstance(self, type(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() + 1))):
                    organizm = self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY() + 1)
                    self.swiat.dodajWydarzenie(self, organizm, self.swiat.KILL)
                    x = organizm.GetX()
                    y = organizm.GetY()
                    self.swiat.deletefromList(organizm)
                    self.swiat.SetOrganizmy(x, y, None)

                if self.GetX() != 0 and self.GetY() != self.swiat.Getwyskosc() - 1 and self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() + 1) is not None and self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() + 1).GetInicjatywa() > 0 and not isinstance(self, type(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() + 1))):
                    organizm = self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY() + 1)
                    self.swiat.dodajWydarzenie(self, organizm, self.swiat.KILL)
                    x = organizm.GetX()
                    y = organizm.GetY()
                    self.swiat.deletefromList(organizm)
                    self.swiat.SetOrganizmy(x, y, None)

        super().akcja()

    def stworz_nowe_roslina(self, x, y, sw):
        org = BarszczSosnowskiego(x, y, True, sw)

    def CzyNieZabija(self):
        return False

    def SzansaNaRozsiew(self):
        return 100