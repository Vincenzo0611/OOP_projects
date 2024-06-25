from Roslina import Roslina
from Organizm import Organizm

class Trawa(Roslina):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 0, 0, swiat.IDTRAWA, swiat)

    def rysowanie(self):
        return "#00E277"

    def nazwa(self):
        return "Trawa"

    def stworz_nowe_roslina(self, x, y, sw):
        org = Trawa(x, y, True, sw)
