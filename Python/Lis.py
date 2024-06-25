from Zwierze import Zwierze
from Organizm import Organizm

class Lis(Zwierze):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 3, 7, swiat.IDLIS, swiat)

    def rysowanie(self):
        return "#fd890e"

    def nazwa(self):
        return "Lis"

    def stworz_nowe_zwierze(self, x, y, sw):
        org = Lis(x, y, True, sw)

    def CzyWech(self, organizm):
        if self.GetSila() >= organizm.GetSila():
            return True
        return False
