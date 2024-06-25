from Roslina import Roslina
from Organizm import Organizm

class WilczeJagody(Roslina):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 99, 0, swiat.IDWILCZEJAGODY, swiat)

    def rysowanie(self):
        return "#04167a"

    def nazwa(self):
        return "Wilcze Jagody"

    def stworz_nowe_roslina(self, x, y, sw):
        org = WilczeJagody(x, y, True, sw)

    def szansa_na_rozsiew(self):
        return 100
