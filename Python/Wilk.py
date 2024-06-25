from Zwierze import Zwierze
from Organizm import Organizm

class Wilk(Zwierze):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 9, 5, swiat.IDWILK, swiat)

    def rysowanie(self):
        return "#6a6565"

    def nazwa(self):
        return "Wilk"

    def stworz_nowe_zwierze(self, x, y, sw):
        org = Wilk(x, y, True, sw)
