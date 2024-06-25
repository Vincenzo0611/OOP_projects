from Roslina import Roslina
from Organizm import Organizm

class Mlecz(Roslina):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 0, 0, swiat.IDMLECZ, swiat)

    def rysowanie(self):
        return "#eeea18"

    def nazwa(self):
        return "Mlecz"

    def stworz_nowe_roslina(self, x, y, sw):
        org = Mlecz(x, y, True, sw)

    def ile_prob(self):
        return 3
