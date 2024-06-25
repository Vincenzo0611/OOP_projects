package Main;

public abstract  class Organizm {
       private int id;
       private int sila;
       private int inicjatywa;
       private int x;
       private int y;
       private int rundy;

       private boolean dopiero_zrodzony;

       protected Swiat swiat;
       public Organizm(int x, int y, int sila, int inicjatywa, int id, Swiat swiat)
       {
              this.id = id;
              this.swiat = swiat;
              this.x = x;
              this.y = y;
              this.sila = sila;
              this.inicjatywa = inicjatywa;
              this.dopiero_zrodzony = false;
              this.rundy = 0;
              this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
              swiat.addToList(this);
       }
       public Organizm(int x, int y, int sila, int inicjatywa, int id,  boolean dz, Swiat swiat)
       {
              this.id = id;
              this.swiat = swiat;
              this.x = x;
              this.y = y;
              this.sila = sila;
              this.inicjatywa = inicjatywa;
              this.dopiero_zrodzony = dz;
              this.rundy = 0;
              this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
              swiat.addToList(this);
       }
       public abstract void akcja();
       public abstract void kolizja(Organizm organizm, int kierunek);
       public abstract String rysowanie();
       public abstract String nazwa();
       public String UczestnikWydarzenia()
       {
              String result = "";

              result = this.nazwa();

              result += "(";
              result += String.valueOf(this.GetX());
              result += ",";
              result += String.valueOf(this.GetY());
              result += ")";

              return result;
       }
       public int CzyOdbijaAtak(Organizm atakujacy)
       {
              return 0;
       }
       public boolean CzyWech(Organizm organizm)
       {
              return true;
       }
       public boolean CzyDodajeSily()
       {
              return false;
       }
       public boolean CzyUcieka()
       {
              return false;
       }
       public boolean CzyNieZabija()
       {
              return true;
       }
       public void idzXY(int x, int y)
       {
              this.swiat.SetOrganizmy(this.GetX(), this.GetY(), null);
              this.SetX(x);
              this.SetY(y);
              this.swiat.SetOrganizmy(this.GetX(), this.GetY(), this);
       }
       public int GetX()
       {
              return x;
       }
       public int GetY()
       {
              return y;
       }
       public int GetSila()
       {
              return sila;
       }
       public int GetInicjatywa()
       {
              return inicjatywa;
       }
       public boolean GetDZ()
       {
              return dopiero_zrodzony;
       }
       public int GetId()
       {
              return id;
       }
       public void SetX(int x)
       {
              this.x = x;
       }
       public void SetY(int y)
       {
              this.y = y;
       }
       public void SetSila(int sila)
       {
              this.sila= sila;
       }
       public void SetInicjatywa(int inicjatywa)
       {
              this.inicjatywa = inicjatywa;
       }
       public void SetDZ(boolean zrodzony)
       {
              this.dopiero_zrodzony = zrodzony;
       }
       public void WyzerujRundy()
       {
              this.rundy = 0;
       }
       public void SetRundy(int r)
       {
              this.rundy = r;
       }

       public void KolejnaRunda()
       {
              this.rundy++;
       }
       public int GetRundy()
       {
              return this.rundy;
       }

}
