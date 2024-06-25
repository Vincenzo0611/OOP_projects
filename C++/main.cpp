#include <iostream>
#include <conio.h>
#include "Swiat.h"
#include "Organizm.h"
#include "Lista.h"
#include "define.h"
#include "Zwierze.h"
#include "Wilk.h"
#include "Owca.h"
#include "Trawa.h"
#include "Mlecz.h"
#include "Guarana.h"
#include "Lis.h"
#include "Zolw.h"
#include "Antylopa.h"
#include "WilczeJagody.h"
#include "Czlowiek.h"
#include "BarszczSosnowskiego.h"

using namespace std;

int main()
{
	srand(time(NULL));
    
    /*
	Swiat sw(40);

	Organizm* org = new Czlowiek(1, 2, &sw);
    org = new Owca(0, 2, &sw);
    //org = new Owca(2, 2, &sw);
    //org = new Owca(1, 1, &sw);
    org = new Owca(1, 3, &sw);
    org = new Zolw(13, 12, &sw);
    org = new Zolw(15, 14, &sw);
    org = new Lis(10, 5, &sw);
    org = new Lis(11, 5, &sw);
    org = new Lis(13, 5, &sw);
    org = new Lis(14, 5, &sw);
    org = new Lis(14, 7, &sw);
    org = new Lis(14, 8, &sw);
    //org = new Antylopa(10, 15, &sw);
    //org = new Antylopa(8, 15, &sw);
    //org = new Antylopa(10, 17, &sw);
	org = new WilczeJagody(15, 10, &sw);
	org = new Owca(33, 33, &sw);
	//org = new Owca(33, 30, &sw);
	//org = new Owca(30, 30, &sw);
	org = new Trawa(3, 13, &sw);
	org = new Mlecz(13, 13, &sw);
	org = new Guarana(20, 23, &sw);
	org = new Wilk(26, 23, &sw);
	//org = new BarszczSosnowskiego(26, 33, &sw);
	org = new Mlecz(27, 30, &sw);
    
    */
    
    int x, y;

    cout << "Podaj wymiary swiata (x y):\n";
    cin >> x >> y;

    Swiat sw(x, y);
    
    
    system("cls");
	sw.rysujSwiat();
    
	int ch;

    while (1) {
        ch = _getch();
        sw.Setkierunek(BRAK);
        if (ch == 0 || ch == 224) {
            // Special key
            ch = _getch();

            switch (ch) {
            case 72: // Up arrow
                sw.Setkierunek(GORA);
                break;
            case 80: // Down arrow
                sw.Setkierunek(DOL);
                break;
            case 75: // Left arrow
                sw.Setkierunek(LEWO);
                break;
            case 77: // Right arrow
                sw.Setkierunek(PRAWO);
                break;
            default:
                break;
            }
        }
        else if (ch == ' ')
        {
            sw.umiejetnosc();
        }
        else if (ch == 's')
        {
            sw.save();
            
        }
        else if (ch == 'l')
        {
            sw.load();
            system("cls");
            sw.rysujSwiat();
        }
        else if(ch == 'f')
        {
            break;
        }

        if (ch != 's' && ch != 'l')
        {
            sw.wykonajTure();
            system("cls");
            sw.rysujSwiat();
        }
    }
	return 0;
}

