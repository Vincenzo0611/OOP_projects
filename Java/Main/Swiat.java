package Main;

import java.io.*;
import java.util.Vector;
import java.util.Random;
import Zwierzeta.*;
import Rosliny.*;

public class Swiat {
        
        public final int GORA = 0;
        public final int DOL = 1;
        public final int PRAWO = 2;
        public final int LEWO = 3;
        public final int LEWOG = 4;
        public final int PRAWOG = 5;
        public final int BRAK = 6;
        public final int ILIST = 5;
        public final int CZAS_UMIEJETNOSCI = 5;
        public final int CZAS_ROZMNAZANIA = 2;
                
        public final int ROZMNAZANIE = 1;
        public final int ROZPRZESTRZENIANIE = 2;
        public final int KILL = 3;
        public final int ODBITY = 4;
        public final int UCIEKA = 5;
        public final int UMIEJETNOSC_A = 6;
        public final int UMIEJETNOSC_N = 7;
        
        public final int IDCZLOWIEK = 1;
        public final int IDWILK = 2;
        public final int IDOWCA = 3;
        public final int IDLIS = 4;
        public final int IDZOLW = 5;
        public final int IDANTYLOPA = 6;
        public final int IDTRAWA = 7;
        public final int IDMLECZ = 8;
        public final int IDGUARANA = 9;
        public final int IDWILCZEJAGODY = 10;
        public final int IDBARSZCZSOS = 11;
        
        
        private Vector<Organizm>[] kolejnosc;
        private Vector<String> wydarzenia;
        private int wysokosc;
        private int szerokosc;
        private int uklad_planszy;
        private int kierunek_ruchu;
        private int rundy;
        private boolean aktywna;
        private boolean czlowiekzyje;
        private String komunikat;
        private Organizm[] organizmy;
        public Random rand;

        public Swiat()
        {
                this.organizmy = new Organizm[40 * 40];

                for (int i = 0; i < 40*40; i++)
                {
                        organizmy[i] = null;
                }
                this.kolejnosc = new Vector[ILIST];
                this.wydarzenia = new Vector<String>();
                for(int i = 0; i < ILIST;  i++) {
                        this.kolejnosc[i] = new Vector<Organizm>();
                }
                this.szerokosc = 40;
                this.wysokosc = 40;
                this.Setkierunek(BRAK);
                this.aktywna = false;
                this.czlowiekzyje = true;
                this.rundy = CZAS_UMIEJETNOSCI;
                this.komunikat = "";
                this.rand = new Random();
        }


        public Swiat(int m, int n, int uklad_planszy)
        {
                this.organizmy = new Organizm[m * n];

                for (int i = 0; i < m * n; i++)
                {
                        organizmy[i] = null;
                }

                this.szerokosc = m;
                this.wysokosc = n;
                this.uklad_planszy = uklad_planszy;
                this.kolejnosc = new Vector[ILIST];
                this.wydarzenia = new Vector<String>();
                for(int i = 0; i < ILIST;  i++) {
                        this.kolejnosc[i] = new Vector<Organizm>();
                }
                this.Setkierunek(BRAK);
                this.aktywna = false;
                this.czlowiekzyje = true;
                this.rundy = CZAS_UMIEJETNOSCI;
                this.komunikat = "";
                this.rand = new Random();

                int id = 0, x, y;
                Organizm org = new Czlowiek(0, 0, this);
                for (int i = 0; i < (szerokosc * wysokosc) / 10; i++)
                {
                        id = rand.nextInt(11) + 2;
                        x = rand.nextInt(szerokosc);
                        y = rand.nextInt(wysokosc);
                        if (this.GetOrganizmy(x, y) == null)
                        {
                                if (id == IDWILK)
                                        org = new Wilk(x, y, this);
                                else if (id == IDOWCA)
                                        org = new Owca(x, y, this);
                                else if (id == IDLIS)
                                        org = new Lis(x, y, this);
                                else if (id == IDZOLW)
                                        org = new Zolw(x, y, this);
                                else if (id == IDANTYLOPA)
                                        org = new Antylopa(x, y, this);
                                else if (id == IDTRAWA)
                                        org = new Trawa(x, y, this);
                                else if (id == IDMLECZ)
                                        org = new Mlecz(x, y, this);
                                else if (id == IDGUARANA)
                                        org = new Guarana(x, y, this);
                                else if (id == IDWILCZEJAGODY)
                                        org = new WilczeJagody(x, y, this);
                                else if (id == IDBARSZCZSOS)
                                        org = new BarszczSosnowskiego(x, y, this);
                        }

                }


        }
        public void wykonajTure()
        {
                for (int i = ILIST - 1; i >= 0; i--)
                {
                        int j = 0;
                        while (j < kolejnosc[i].size())
                        {
                                if (kolejnosc[i].get(j).GetDZ() == false)
                                {
                                        kolejnosc[i].get(j).KolejnaRunda();
                                        kolejnosc[i].get(j).akcja();
                                }
			else
                                kolejnosc[i].get(j).SetDZ(false);
                                j++;
                        }
                }
                if (this.rundy >= CZAS_UMIEJETNOSCI && this.aktywna == true)
                {
                        this.ZmienAktywna();
                        this.WyzerujRundy();
                        this.dodajWydarzenie(null, null, UMIEJETNOSC_N);
                }
                this.KolejnaRunda();
        }

        public void umiejetnosc()
        {
                if(this.czlowiekzyje == false)
                {
                        this.komunikat = "Nie mozna uzyc umiejetnosci, czlowiek nie zyje";
                }
	            else if (this.rundy >= CZAS_UMIEJETNOSCI)
                {
                        this.ZmienAktywna();
                        this.WyzerujRundy();
                        if(this.aktywna) {
                                this.dodajWydarzenie(null, null, UMIEJETNOSC_A);
                        }
		                else {
                                this.dodajWydarzenie(null, null, UMIEJETNOSC_N);
                        }
                }
                else
                {
                        if (this.CzyAktywna())
                        {
                                //juz aktywna ile tur jeszcze
                                this.komunikat = "Umiejetnosc aktywna przez ";
                                this.komunikat += String.valueOf(CZAS_UMIEJETNOSCI - this.rundy);
                                if (CZAS_UMIEJETNOSCI - this.rundy == 1) {
                                        this.komunikat += " ture";
                                }
			                    else if (CZAS_UMIEJETNOSCI - this.rundy == 5) {
                                        this.komunikat += " tur";
                                }
                                else {
                                        this.komunikat += " tury";
                                }
                        }
		                else
                        {
                                //umiejetnosc gotowa w nastepnej turze
                                this.komunikat = "Umiejetnosc gotowa za ";
                                this.komunikat += String.valueOf(CZAS_UMIEJETNOSCI - this.rundy);
                                if (CZAS_UMIEJETNOSCI - this.rundy == 1) {
                                        this.komunikat += " ture";
                                }
			                    else if (CZAS_UMIEJETNOSCI - this.rundy == 5) {
                                        this.komunikat += " tur";
                                }
			                    else {
                                        this.komunikat += " tury";
                                }
                        }
                }
        }

        public void rysujSwiat(Przyciski frame)
        {
                frame.updateButtons( this);
        }

        public void addToList(Organizm organizm)
        {
                int i = organizm.GetInicjatywa();
                if (i < 2)
                {
                        kolejnosc[i].add(organizm);
                }
                else if (i == 5)
                {
                        kolejnosc[3].add(organizm);
                }
                else if (i == 7)
                {
                        kolejnosc[4].add(organizm);
                }
                else
                {
                        kolejnosc[2].add(organizm);
                }
        }

        public void deletefromList(Organizm organizm)
        {
                int i = organizm.GetInicjatywa();
                int j = 0;

                if (organizm instanceof Czlowiek)
                        this.czlowiekzyje = false;

                if (i < 2)
                {
                        while (j < kolejnosc[i].size())
                        {
                                if (kolejnosc[i].get(j) != organizm)
                                        j++;
                                else
                                        kolejnosc[i].remove(j);
                        }
                }
                else if (i == 5)
                {
                        while (j < kolejnosc[3].size())
                        {
                                if (kolejnosc[3].get(j) != organizm)
                                        j++;
                                else
                                        kolejnosc[3].remove(j);
                        }
                }
                else if (i == 7)
                {
                        while (j < kolejnosc[4].size())
                        {
                                if (kolejnosc[4].get(j) != organizm)
                                        j++;
                                else
                                        kolejnosc[4].remove(j);
                        }
                }
                else
                {
                        while (j < kolejnosc[2].size())
                        {
                                if (kolejnosc[2].get(j) != organizm)
                                        j++;
                                else
                                        kolejnosc[2].remove(j);
                        }
                }
        }

        public int Getszerokosc()
        {
                return szerokosc;
        }
        public int Getwyskosc()
        {
                return wysokosc;
        }

        public void Setkierunek(int kierunek)
        {
                this.kierunek_ruchu = kierunek;
        }
        public int Getkierunek()
        {
                return this.kierunek_ruchu;
        }
        public int Getuklad_planszy()
        {
                return this.uklad_planszy;
        }

        public void SetOrganizmy(int x, int y, Organizm n)
        {
                this.organizmy[y * this.Getszerokosc() + x] = n;
        }

        public Organizm GetOrganizmy(int x, int y)
        {
                return this.organizmy[y* this.Getszerokosc() + x];
        }

        public void WyzerujRundy()
        {
                this.rundy = 0;
        }
        public void KolejnaRunda()
        {
                this.rundy++;
        }
        public int GetRundy()
        {
                return this.rundy;
        }

        public String GetKomunikat()
        {
                return this.komunikat;
        }

        public void SetRundy(int r)
        {
                this.rundy = r;
        }

        public boolean CzyAktywna()
        {
                return this.aktywna;
        }
        public void ZmienAktywna()
        {
                if (this.aktywna) {
                        this.aktywna = false;
                }
	            else {
                        this.aktywna = true;
                }
        }


        public void dodajWydarzenie(Organizm organizm1, Organizm organizm2, int kod)
        {
                String s = "";

                if (organizm1 != null)
                        s += organizm1.UczestnikWydarzenia();

                if (kod == ROZMNAZANIE)
                        s += " rozmnazyl sie z ";
                else if (kod == ROZPRZESTRZENIANIE)
                        s += " rozprzestrzenil sie";
                else if (kod == KILL)
                        s += " zjadl ";
                else if (kod == ODBITY)
                        s += " odbil atak ";
                else if (kod == UCIEKA)
                        s += " ucieka od ";
                else if (kod == UMIEJETNOSC_A)
                        s += "Czlowiek uzyl umiejetnosci";
                else if (kod == UMIEJETNOSC_N)
                        s += "Umiejetnosc przestala dzialac";

                if (organizm2 != null)
                        s += organizm2.UczestnikWydarzenia();

                this.wydarzenia.add(s);
        }

        public void save()
        {
                try {
                        FileOutputStream fileOutputStream = new FileOutputStream("save.bin");
                        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                        dataOutputStream.writeInt(this.szerokosc);
                        dataOutputStream.writeInt(this.wysokosc);
                        dataOutputStream.writeInt(this.kierunek_ruchu);
                        dataOutputStream.writeInt(this.rundy);
                        dataOutputStream.writeInt(this.uklad_planszy);
                        dataOutputStream.writeBoolean(this.aktywna);
                        dataOutputStream.writeBoolean(this.czlowiekzyje);
                        int size = 0;
                        for (int i = 0; i < ILIST; i++)
                                size += kolejnosc[i].size();

                        dataOutputStream.writeInt(size);
                        dataOutputStream.close();
                        fileOutputStream = new FileOutputStream("org.bin");
                        dataOutputStream = new DataOutputStream(fileOutputStream);
                        for (int i = ILIST - 1; i >= 0; i--)
                        {
                                int j = 0;
                                while (j < kolejnosc[i].size())
                                {
                                        dataOutputStream.writeInt(kolejnosc[i].get(j).GetId());
                                        dataOutputStream.writeInt(kolejnosc[i].get(j).GetX());
                                        dataOutputStream.writeInt(kolejnosc[i].get(j).GetY());
                                        dataOutputStream.writeInt(kolejnosc[i].get(j).GetSila());
                                        dataOutputStream.writeInt(kolejnosc[i].get(j).GetRundy());
                                        dataOutputStream.writeBoolean(kolejnosc[i].get(j).GetDZ());
                                        j++;
                                }
                        }
                        dataOutputStream.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        public void load()
        {
                try {
                        FileInputStream fileInputStream = new FileInputStream("save.bin");
                        DataInputStream dataInputStream = new DataInputStream(fileInputStream);

                        organizmy = null;

                        for (int i = 0; i < ILIST; i++)
                                this.kolejnosc[i].clear();

                        this.szerokosc = dataInputStream.readInt();
                        this.wysokosc = dataInputStream.readInt();
                        this.kierunek_ruchu = dataInputStream.readInt();
                        this.rundy = dataInputStream.readInt();
                        this.uklad_planszy = dataInputStream.readInt();
                        this.aktywna = dataInputStream.readBoolean();
                        this.czlowiekzyje = dataInputStream.readBoolean();

                        int size = dataInputStream.readInt();
                        dataInputStream.close();

                        this.organizmy = new Organizm[this.wysokosc * this.szerokosc];

                        for (int i = 0; i < this.szerokosc * this.wysokosc; i++)
                        {
                                organizmy[i] = null;
                        }

                        fileInputStream = new FileInputStream("org.bin");
                        dataInputStream = new DataInputStream(fileInputStream);

                        int id, x, y, sila, rundy;
                        boolean dz;
                        Organizm org = null;
                        for (int i = 0; i < size; i++)
                        {
                                id = dataInputStream.readInt();
                                x = dataInputStream.readInt();
                                y = dataInputStream.readInt();
                                sila = dataInputStream.readInt();
                                rundy = dataInputStream.readInt();
                                dz = dataInputStream.readBoolean();
                                if (id == IDCZLOWIEK)
                                        org = new Czlowiek(x, y, dz, this);
                                else if (id == IDWILK)
                                        org = new Wilk(x, y, dz, this);
                                else if (id == IDOWCA)
                                        org = new Owca(x, y, dz, this);
                                else if (id == IDLIS)
                                        org = new Lis(x, y, dz, this);
                                else if (id == IDZOLW)
                                        org = new Zolw(x, y, dz, this);
                                else if (id == IDANTYLOPA)
                                        org = new Antylopa(x, y, dz, this);
                                else if (id == IDTRAWA)
                                        org = new Trawa(x, y, dz, this);
                                else if (id == IDMLECZ)
                                        org = new Mlecz(x, y, dz, this);
                                else if (id == IDGUARANA)
                                        org = new Guarana(x, y, dz, this);
                                else if (id == IDWILCZEJAGODY)
                                        org = new WilczeJagody(x, y, dz, this);
                                else if (id == IDBARSZCZSOS)
                                        org = new BarszczSosnowskiego(x, y, dz, this);

                                if (org != null)
                                {
                                        org.SetSila(sila);
                                        org.SetRundy(rundy);
                                }
                        }

                        dataInputStream.close();

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public boolean CzypusteWydarzenia()
        {
                return wydarzenia.isEmpty();
        }

        public String wezWydarzenie()
        {
                String r = wydarzenia.get(0);
                wydarzenia.remove(0);
                return r;

        }

        public void Usunkomunikat()
        {
                this.komunikat = "";
        }
}
