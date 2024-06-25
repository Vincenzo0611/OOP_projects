from Zwierze import Zwierze
from Organizm import Organizm

class Owca(Zwierze):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 4, 4, swiat.IDOWCA, swiat)

    def rysowanie(self):
        return "#ffffff"

    def nazwa(self):
        return "Owca"

    def stworz_nowe_zwierze(self, x, y, sw):
        org = Owca(x, y, True, sw)
