import tkinter
from tkinter import *
from tkinter import ttk
from Swiat import Swiat
from Przyciski import Przyciski

uk = 0
num1 = 0
num2 = 0

def key_pressed(event):
    if event.keysym == "w":
        sw.Setkierunek(sw.GORA)
        sw.wykonajTure()
        sw.rysujSwiat(gra)
    elif event.keysym == "s":
        sw.Setkierunek(sw.DOL)
        sw.wykonajTure()
        sw.rysujSwiat(gra)
    elif event.keysym == "a":
        sw.Setkierunek(sw.LEWO)
        sw.wykonajTure()
        sw.rysujSwiat(gra)
    elif event.keysym == "d":
        sw.Setkierunek(sw.PRAWO)
        sw.wykonajTure()
        sw.rysujSwiat(gra)
    elif event.keysym == "space":
        sw.Setkierunek(sw.BRAK)
        sw.umiejetnosc()
        sw.wykonajTure()
        sw.rysujSwiat(gra)
    elif event.keysym == "z":
        sw.save()
    elif event.keysym == "l":
        sw.load(gra)
        sw.rysujSwiat(gra)

def przycisk1():
    global uk
    uk = 4
    #op.destroy()


def przycisk2():
    global uk
    uk = 6
    #op.destroy()


root = tkinter.Tk()

root.withdraw()
"""
op = tkinter.Toplevel()
op.title('Wybierz plansze')

o1 = tkinter.Button(op, text="4", command=przycisk1)
o1.pack()

o2 = tkinter.Button(op, text="6", command=przycisk2)
o2.pack()

root.wait_window(op)
"""

wymiary = tkinter.Toplevel()
wymiary.title("Wprowadź wymiary")

def get_numbers():
    # Używanie zmiennych globalnych
    global num1, num2
    num1 = int(entry1.get())
    num2 = int(entry2.get())
    wymiary.destroy()  # Zamknięcie okna


label1 = tkinter.Label(wymiary, text="X:")
label1.pack()
entry1 = tkinter.Entry(wymiary)
entry1.pack()

label2 = tkinter.Label(wymiary, text="Y:")
label2.pack()
entry2 = tkinter.Entry(wymiary)
entry2.pack()

button = tkinter.Button(wymiary, text="OK", command=get_numbers)
button.pack()

root.wait_window(wymiary)
root.deiconify()
root.state('zoomed')


sw = Swiat(num1, num2, 4)

gra = Przyciski(sw, root)

root.bind("<KeyPress>", key_pressed)

root.mainloop()
