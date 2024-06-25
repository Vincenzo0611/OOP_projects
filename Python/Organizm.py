from abc import ABC, abstractmethod


class Organizm(ABC):
    def __init__(self, x, y, sila, inicjatywa, idi, dz, swiat):
        self.id = idi
        self.swiat = swiat
        self.x = x
        self.y = y
        self.sila = sila
        self.inicjatywa = inicjatywa
        self.dopiero_zrodzony = dz
        self.rundy = 0
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), self)
        swiat.addToList(self)

    @abstractmethod
    def akcja(self):
        pass

    @abstractmethod
    def kolizja(self, organizm, kierunek):
        pass

    @abstractmethod
    def rysowanie(self):
        pass

    @abstractmethod
    def nazwa(self):
        pass

    @abstractmethod
    def UczestnikWydarzenia(self):
        pass

    def UczestnikWydarzenia(self):
        result = ""

        result = self.nazwa()

        result += "("
        result += str(self.GetX())
        result += ","
        result += str(self.GetY())
        result += ")"

        return result

    def CzyOdbijaAtak(self, atakujacy):
        return 0

    def CzyWech(self, organizm):
        return True

    def CzyDodajeSily(self):
        return False

    def CzyUcieka(self):
        return False

    def CzyNieZabija(self):
        return True

    def idzXY(self, x, y):
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), None)
        self.SetX(x)
        self.SetY(y)
        self.swiat.SetOrganizmy(self.GetX(), self.GetY(), self)

    def GetX(self):
        return self.x

    def GetY(self):
        return self.y

    def GetSila(self):
        return self.sila

    def GetInicjatywa(self):
        return self.inicjatywa

    def GetDZ(self):
        return self.dopiero_zrodzony

    def GetId(self):
        return self.id

    def SetX(self, x):
        self.x = x

    def SetY(self, y):
        self.y = y

    def SetSila(self, sila):
        self.sila = sila

    def SetInicjatywa(self, inicjatywa):
        self.inicjatywa = inicjatywa

    def SetDZ(self, zrodzony):
        self.dopiero_zrodzony = zrodzony

    def WyzerujRundy(self):
        self.rundy = 0

    def SetRundy(self, r):
        self.rundy = r

    def KolejnaRunda(self):
        self.rundy += 1

    def GetRundy(self):
        return self.rundy



