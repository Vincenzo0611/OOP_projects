from Zwierze import Zwierze
from Organizm import Organizm

class Zolw(Zwierze):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 2, 1, swiat.IDZOLW, swiat)

    def rysowanie(self):
        return "#216d33"

    def nazwa(self):
        return "Zolw"

    def stworz_nowe_zwierze(self, x, y, sw):
        org = Zolw(x, y, True, sw)

    def CzyRuszac(self):
        random = self.swiat.rand.randint(0, 3)

        if random == 3:
            return True
        return False

    def CzyOdbijaAtak(self, atakujacy):
        if atakujacy.GetSila() < 5:
            return 2
        return 0
