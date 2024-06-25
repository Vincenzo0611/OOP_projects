from Zwierze import Zwierze
from Organizm import Organizm

class CyberOwca(Zwierze):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 11, 4, swiat.IDCYBEROWCA, swiat)

    def rysowanie(self):
        return "#db0fdb"

    def nazwa(self):
        return "CyberOwca"

    def stworz_nowe_zwierze(self, x, y, sw):
        org = CyberOwca(x, y, True, sw)

    def akcja(self):
        if(self.swiat.x_barszczu == -1):
            super().akcja()
        else:
            if(self.x < self.swiat.x_barszczu):
                if self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()) is None:
                    self.idzPrawo()
                else:
                    self.kolizja(self.swiat.GetOrganizmy(self.GetX() + 1, self.GetY()), self.swiat.PRAWO)
            elif self.x > self.swiat.x_barszczu:
                if self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()) is None:
                    self.idzLewo()
                else:
                    self.kolizja(self.swiat.GetOrganizmy(self.GetX() - 1, self.GetY()), self.swiat.LEWO)
            else:
                if(self.y < self.swiat.y_barszczu):
                    if self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1) is None:
                        self.idzDol()
                    else:
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX(), self.GetY() + 1), self.swiat.DOL)
                elif self.y > self.swiat.y_barszczu:
                    if self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1) is None:
                        self.idzGora()
                    else:
                        self.kolizja(self.swiat.GetOrganizmy(self.GetX(), self.GetY() - 1), self.swiat.GORA)
