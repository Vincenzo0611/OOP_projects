from Roslina import Roslina
from Organizm import Organizm

class Guarana(Roslina):
    def __init__(self, x, y, dz, swiat):
        super().__init__(x, y, dz, 0, 0, swiat.IDGUARANA, swiat)

    def rysowanie(self):
        return "#b20020"

    def nazwa(self):
        return "Guarana"

    def stworz_nowe_roslina(self, x, y, sw):
        org = Guarana(x, y, True, sw)

    def czy_dodaje_sily(self):
        return True
