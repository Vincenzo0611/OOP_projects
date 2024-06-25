import tkinter
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


class Przyciski():

    def dodawanie(self, x, y, swiat, przycisk):
        def handle_option_selection(event):
            selected_index = options_listbox.curselection()
            selected_option = options_listbox.get(selected_index)
            window.destroy()
            if selected_option == "Wilk":
                org = Wilk(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())
            elif selected_option == "Owca":
                org = Owca(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())
            elif selected_option == "Lis":
                org = Lis(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())
            elif selected_option == "Zolw":
                org = Zolw(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())
            elif selected_option == "Antylopa":
                org = Antylopa(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())
            elif selected_option == "Trawa":
                org = Trawa(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())
            elif selected_option == "Mlecz":
                org = Mlecz(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())
            elif selected_option == "Guarana":
                org = Guarana(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())
            elif selected_option == "WilczeJagody":
                org = WilczeJagody(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())
            elif selected_option == "BarszczSosnowskiego":
                org = BarszczSosnowskiego(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())
            elif selected_option == "CyberOwca":
                org = CyberOwca(x, y, False, swiat)
                if(swiat.Getuklad_planszy() == 4):
                    przycisk.configure(background=org.rysowanie())

        if(swiat.GetOrganizmy(x, y) == None):
            # Tworzenie listy opcji
            options = ["Owca", "Wilk", "Lis", "Zolw", "Antylopa", "Trawa", "Mlecz", "Guarana", "WilczeJagody", "BarszczSosnowskiego", "CyberOwca"]

            # Tworzenie listboxa i dodawanie opcji do niego
            window = tkinter.Toplevel()
            options_listbox = tkinter.Listbox(window)
            for option in options:
                options_listbox.insert(tkinter.END, option)

            options_listbox.bind("<<ListboxSelect>>", handle_option_selection)
            options_listbox.pack()

    def __init__(self, swiat, root):
        self.plansza = tkinter.Frame(root)
        self.legenda = tkinter.Frame(root)

        self.plansza.grid(row=0, column=0)
        self.legenda.grid(row=0, column=1)


        for i in range(0, swiat.Getszerokosc()):
            for j in range(0, swiat.Getwyskosc()):
                if(swiat.GetOrganizmy(j, i) != None):
                    but = tkinter.Button(self.plansza, width=2, bg=swiat.GetOrganizmy(j, i).rysowanie())
                    but.grid(row=i, column=j)
                    but.config(command=lambda x=j, y=i, przycisk=but: self.dodawanie(x, y, swiat, przycisk))
                else:
                    but = tkinter.Button(self.plansza, width=2, bg="#000000")
                    but.grid(row=i, column=j)
                    but.config(command=lambda x=j, y=i, przycisk=but: self.dodawanie(x, y, swiat, przycisk))

        self.leg = tkinter.Frame(self.legenda)
        self.komunikaty = tkinter.Frame(self.legenda)

        self.leg.grid(row=0, column=0)
        self.komunikaty.grid(row=0, column=1)

        c = tkinter.Label(self.leg, text="Czlowiek", fg="#18ebd5", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="Antylopa", fg="#a6730c", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="Barszcz Sosnowskiego", fg="#d45454", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="Guarana", fg="#b20020", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="Lis", fg="#fd890e", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="Owca", fg="#ffffff", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="Mlecz", fg="#eeea18", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="Trawa", fg="#00E277", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="Wilcze Jagody", fg="#04167a", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="Wilk", fg="#6a6565", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="Zolw", fg="#216d33", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="CyberOwca", fg="#db0fdb", bg="#000000")
        c.pack()
        c = tkinter.Label(self.leg, text="WSAD - ruch")
        c.pack()
        c = tkinter.Label(self.leg, text="Spacja - umiejetnosc")
        c.pack()
        c = tkinter.Label(self.leg, text="Z - zapis")
        c.pack()
        c = tkinter.Label(self.leg, text="L - wczytaj")
        c.pack()

    def updateButtons(self, swiat):
        i = 0
        j = 0
        components = self.plansza.winfo_children()
        for component in components:
            if isinstance(component, tkinter.Button):
                org = swiat.GetOrganizmy(j, i)
                if org is not None:
                    component.configure(background=org.rysowanie())
                else:
                    component.configure(background="#000000")
                j += 1
                if j == swiat.Getszerokosc():
                    j = 0
                    i += 1

        for widget in self.komunikaty.winfo_children():
            widget.destroy()
        k = 0
        if(swiat.GetKomunikat() != ""):
            l = tkinter.Label(self.komunikaty, text=swiat.GetKomunikat())
            l.pack()
            swiat.Usunkomunikat()
        while not swiat.CzypusteWydarzenia():
            l = tkinter.Label(self.komunikaty, text=swiat.wezWydarzenie())
            l.pack()
            k += 1
            if k == swiat.Getwyskosc():
                break

    def Load(self, swiat):
        for widget in self.plansza.winfo_children():
            widget.destroy()

        for i in range(0, swiat.Getszerokosc()):
            for j in range(0, swiat.Getwyskosc()):
                if(swiat.GetOrganizmy(j, i) != None):
                    but = tkinter.Button(self.plansza, width=2, bg=swiat.GetOrganizmy(j, i).rysowanie())
                    but.grid(row=i, column=j)
                    but.config(command=lambda x=j, y=i, przycisk=but: self.dodawanie(x, y, swiat, przycisk))
                else:
                    but = tkinter.Button(self.plansza, width=2, bg="#000000")
                    but.grid(row=i, column=j)
                    but.config(command=lambda x=j, y=i, przycisk=but: self.dodawanie(x, y, swiat, przycisk))