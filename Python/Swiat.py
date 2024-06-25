import random
import csv
from Czlowiek import Czlowiek
from Antylopa import Antylopa
from BarszczSosnowskiego import BarszczSosnowskiego
from Guarana import Guarana
from Lis import Lis
from Mlecz import Mlecz
from Owca import Owca
from Trawa import Trawa
from WilczeJagody import WilczeJagody
from Wilk import Wilk
from Zolw import Zolw
from CyberOwca import CyberOwca


class Swiat:
    def __init__(self, m=40, n=40, uklad_planszy=4):
        self.GORA = 0
        self.DOL = 1
        self.PRAWO = 2
        self.LEWO = 3
        self.LEWOG = 4
        self.PRAWOG = 5
        self.BRAK = 6
        self.ILIST = 5
        self.CZAS_UMIEJETNOSCI = 5
        self.CZAS_ROZMNAZANIA = 2

        self.ROZMNAZANIE = 1
        self.ROZPRZESTRZENIANIE = 2
        self.KILL = 3
        self.ODBITY = 4
        self.UCIEKA = 5
        self.UMIEJETNOSC_A = 6
        self.UMIEJETNOSC_N = 7

        self.IDCZLOWIEK = 1
        self.IDWILK = 2
        self.IDOWCA = 3
        self.IDLIS = 4
        self.IDZOLW = 5
        self.IDANTYLOPA = 6
        self.IDTRAWA = 7
        self.IDMLECZ = 8
        self.IDGUARANA = 9
        self.IDWILCZEJAGODY = 10
        self.IDBARSZCZSOS = 11
        self.IDCYBEROWCA = 12

        self.szerokosc = m
        self.wysokosc = n
        self.uklad_planszy = uklad_planszy
        self.kolejnosc = [[] for _ in range(self.ILIST)]
        self.wydarzenia = []
        self.organizmy = [None] * (m * n)
        self.kierunek_ruchu = self.BRAK
        self.rundy = self.CZAS_UMIEJETNOSCI
        self.aktywna = False
        self.czlowiekzyje = True
        self.komunikat = ""
        self.rand = random.Random()

        self.x_barszczu = -1
        self.y_barszczu = -1

        org = Czlowiek(0, 0, self)
        for _ in range((m * n) // 10):
            id = self.rand.randint(2, 12)
            x = self.rand.randint(0, m - 1)
            y = self.rand.randint(0, n - 1)
            if self.GetOrganizmy(x, y) is None:
                if id == self.IDWILK:
                    org = Wilk(x, y, False, self)
                elif id == self.IDOWCA:
                    org = Owca(x, y, False, self)
                elif id == self.IDLIS:
                    org = Lis(x, y, False, self)
                elif id == self.IDZOLW:
                    org = Zolw(x, y, False, self)
                elif id == self.IDANTYLOPA:
                    org = Antylopa(x, y, False, self)
                elif id == self.IDTRAWA:
                    org = Trawa(x, y, False, self)
                elif id == self.IDMLECZ:
                    org = Mlecz(x, y, False, self)
                elif id == self.IDGUARANA:
                    org = Guarana(x, y, False, self)
                elif id == self.IDWILCZEJAGODY:
                    org = WilczeJagody(x, y, False, self)
                elif id == self.IDBARSZCZSOS:
                    org = BarszczSosnowskiego(x, y, False, self)
                elif id == self.IDCYBEROWCA:
                    org = CyberOwca(x, y, False, self)

    def wykonajTure(self):
        self.znajdz_Barszcz()
        for i in range(self.ILIST - 1, -1, -1):
            j = 0
            while j < len(self.kolejnosc[i]):
                if not self.kolejnosc[i][j].GetDZ():
                    self.kolejnosc[i][j].KolejnaRunda()
                    self.kolejnosc[i][j].akcja()
                else:
                    self.kolejnosc[i][j].SetDZ(False)
                j += 1
        if self.rundy >= self.CZAS_UMIEJETNOSCI and self.aktywna:
            self.ZmienAktywna()
            self.WyzerujRundy()
            self.dodajWydarzenie(None, None, self.UMIEJETNOSC_N)
        self.KolejnaRunda()

    def umiejetnosc(self):
        if not self.czlowiekzyje:
            self.komunikat = "Nie można użyć umiejętności, człowiek nie żyje"
        elif self.rundy >= self.CZAS_UMIEJETNOSCI:
            self.ZmienAktywna()
            self.WyzerujRundy()
            if self.aktywna:
                self.dodajWydarzenie(None, None, self.UMIEJETNOSC_A)
            else:
                self.dodajWydarzenie(None, None, self.UMIEJETNOSC_N)
        else:
            if self.CzyAktywna():
                self.komunikat = f"Umiejętność aktywna przez {self.CZAS_UMIEJETNOSCI - self.rundy} "
                if self.CZAS_UMIEJETNOSCI - self.rundy == 1:
                    self.komunikat += "turę"
                elif self.CZAS_UMIEJETNOSCI - self.rundy == 5:
                    self.komunikat += "tur"
                else:
                    self.komunikat += "tur"
            else:
                self.komunikat = f"Umiejętność gotowa za {self.CZAS_UMIEJETNOSCI - self.rundy} "
                if self.CZAS_UMIEJETNOSCI - self.rundy == 1:
                    self.komunikat += "turę"
                elif self.CZAS_UMIEJETNOSCI - self.rundy == 5:
                    self.komunikat += "tur"
                else:
                    self.komunikat += "turę"

    def rysujSwiat(self, frame):
        frame.updateButtons(self)

    def addToList(self, organizm):
        i = organizm.GetInicjatywa()
        if i < 2:
            self.kolejnosc[i].append(organizm)
        elif i == 5:
            self.kolejnosc[3].append(organizm)
        elif i == 7:
            self.kolejnosc[4].append(organizm)
        else:
            self.kolejnosc[2].append(organizm)

    def deletefromList(self, organizm):
        i = organizm.GetInicjatywa()
        j = 0

        if isinstance(organizm, Czlowiek):
            self.czlowiekzyje = False

        if i < 2:
            while j < len(self.kolejnosc[i]):
                if self.kolejnosc[i][j] != organizm:
                    j += 1
                else:
                    self.kolejnosc[i].pop(j)
        elif i == 5:
            while j < len(self.kolejnosc[3]):
                if self.kolejnosc[3][j] != organizm:
                    j += 1
                else:
                    self.kolejnosc[3].pop(j)
        elif i == 7:
            while j < len(self.kolejnosc[4]):
                if self.kolejnosc[4][j] != organizm:
                    j += 1
                else:
                    self.kolejnosc[4].pop(j)
        else:
            while j < len(self.kolejnosc[2]):
                if self.kolejnosc[2][j] != organizm:
                    j += 1
                else:
                    self.kolejnosc[2].pop(j)

    def Getszerokosc(self):
        return self.szerokosc

    def Getwyskosc(self):
        return self.wysokosc

    def Setkierunek(self, kierunek):
        self.kierunek_ruchu = kierunek

    def Getkierunek(self):
        return self.kierunek_ruchu

    def Getuklad_planszy(self):
        return self.uklad_planszy

    def SetOrganizmy(self, x, y, n):
        self.organizmy[y * self.Getszerokosc() + x] = n

    def GetOrganizmy(self, x, y):
        return self.organizmy[y * self.Getszerokosc() + x]

    def WyzerujRundy(self):
        self.rundy = 0

    def KolejnaRunda(self):
        self.rundy += 1

    def GetRundy(self):
        return self.rundy

    def GetKomunikat(self):
        return self.komunikat

    def SetRundy(self, r):
        self.rundy = r

    def CzyAktywna(self):
        return self.aktywna

    def ZmienAktywna(self):
        if self.aktywna:
            self.aktywna = False
        else:
            self.aktywna = True

    def dodajWydarzenie(self, organizm1, organizm2, kod):
        s = ""

        if organizm1 is not None:
            s += organizm1.UczestnikWydarzenia()

        if kod == self.ROZMNAZANIE:
            s += " rozmnazyl sie z "
        elif kod == self.ROZPRZESTRZENIANIE:
            s += " rozprzestrzenil sie"
        elif kod == self.KILL:
            s += " zjadl "
        elif kod == self.ODBITY:
            s += " odbil atak "
        elif kod == self.UCIEKA:
            s += " ucieka od "
        elif kod == self.UMIEJETNOSC_A:
            s += "Czlowiek uzyl umiejetnosci"
        elif kod == self.UMIEJETNOSC_N:
            s += "Umiejetnosc przestala dzialac"

        if organizm2 is not None:
            s += organizm2.UczestnikWydarzenia()

        self.wydarzenia.append(s)

    def save(self):

        size = 0
        for i in range(self.ILIST):
            size += len(self.kolejnosc[i])

        rows = [
            [self.szerokosc, self.wysokosc, self.kierunek_ruchu, self.rundy, self.uklad_planszy, self.aktywna, self.czlowiekzyje, size]
        ]
        with open("plik.csv", mode="w", newline="") as file:
            writer = csv.writer(file)  # Utworzenie obiektu writer

            # Zapisanie wierszy danych
            for row in rows:
                writer.writerow(row)

        with open("file.csv", mode="w", newline="") as file:
            writer = csv.writer(file)  # Create a writer object

            # Loop to add rows
            for i in range(self.ILIST - 1, -1, -1):
                j = 0
                while j < len(self.kolejnosc[i]):
                    row = [self.kolejnosc[i][j].GetId(), self.kolejnosc[i][j].GetX(), self.kolejnosc[i][j].GetY(), self.kolejnosc[i][j].GetSila(), self.kolejnosc[i][j].GetRundy(), self.kolejnosc[i][j].GetDZ()]

                    # Write the row to the CSV file
                    writer.writerow(row)
                    j += 1
    def load(self, gra):
        with open("plik.csv", mode="r") as file:
            reader = csv.reader(file)  # Utworzenie obiektu reader

            # Odczytanie wierszy danych
            for row in reader:
                self.szerokosc = int(row[0])
                self.wysokosc = int(row[1])
                self.kierunek_ruchu = int(row[2])
                self.rundy = int(row[3])
                self.uklad_planszy = int(row[4])
                self.aktywna = bool(row[5])
                self.czlowiekzyje = bool(row[6])
                size = int(row[7])

            self.organizmy = None

            self.organizmy = [None] * (self.szerokosc * self.wysokosc)

            for i in range(self.ILIST):
                self.kolejnosc[i].clear()

        with open("file.csv", mode="r") as file:
            reader = csv.reader(file)
            for row in reader:

                org = None

                id = int(row[0])
                x = int(row[1])
                y = int(row[2])
                sila = int(row[3])
                rundy = int(row[4])
                dz = bool(row[5])
                if id == self.IDCZLOWIEK:
                    org = Czlowiek(x, y, self)
                elif id == self.IDWILK:
                    org = Wilk(x, y, dz, self)
                elif id == self.IDOWCA:
                    org = Owca(x, y, dz, self)
                elif id == self.IDLIS:
                    org = Lis(x, y, dz, self)
                elif id == self.IDZOLW:
                    org = Zolw(x, y, dz, self)
                elif id == self.IDANTYLOPA:
                    org = Antylopa(x, y, dz, self)
                elif id == self.IDTRAWA:
                    org = Trawa(x, y, dz, self)
                elif id == self.IDMLECZ:
                    org = Mlecz(x, y, dz, self)
                elif id == self.IDGUARANA:
                    org = Guarana(x, y, dz, self)
                elif id == self.IDWILCZEJAGODY:
                    org = WilczeJagody(x, y, dz, self)
                elif id == self.IDBARSZCZSOS:
                    org = BarszczSosnowskiego(x, y, dz, self)

                if org is not None:
                    org.SetSila(sila)
                    org.SetRundy(rundy)

        gra.Load(self)

    def CzypusteWydarzenia(self):
        return len(self.wydarzenia) == 0

    def wezWydarzenie(self):
        r = self.wydarzenia[0]
        self.wydarzenia.pop(0)
        return r

    def Usunkomunikat(self):
        self.komunikat = ""

    def znajdz_Barszcz(self):
        if(self.x_barszczu == -1):
            j = 0
            istnieje = False
            while j < len(self.kolejnosc[0]):
                if (self.kolejnosc[0][j].GetId() == self.IDBARSZCZSOS):
                    self.x_barszczu = self.kolejnosc[0][j].GetX()
                    self.y_barszczu = self.kolejnosc[0][j].GetY()
                    istnieje = True
                    break
                j += 1
            if(not istnieje):
                self.x_barszczu = -1
                self.y_barszczu = -1
        elif(self.GetOrganizmy(self.x_barszczu, self.y_barszczu).GetId() != self.IDBARSZCZSOS):
            j = 0
            istnieje = False
            while j < len(self.kolejnosc[0]):
                if(self.kolejnosc[0][j].GetId() == self.IDBARSZCZSOS):
                    self.x_barszczu = self.kolejnosc[0][j].GetX()
                    self.y_barszczu = self.kolejnosc[0][j].GetY()
                    istnieje = True
                    break
                j += 1
            if (not istnieje):
                self.x_barszczu = -1
                self.y_barszczu = -1