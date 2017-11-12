# Program pro výpočet válcových tečných zobrazení

## Funkce

Tento program slouží k vypočítání souřadnic x,y v centimetrech, které budou mít jednotlivé rovnoběžky 
a poledníky ve vybraném zobrazení na papíře. Poté je ještě možnost v daném zobrazení vypočítat stejný typ souřadnic pro jakýkoli bod
na planetě. 

Na začátku uživatel zadá celočíselné kladné měřítko, kladný poloměr planety (nejčastěji Země), přičemž pro vstup 0 se použije 
referenční hodnota 6371,11 km, a velké písmeno pro požadovyný typ zobrazení (L pro Lambertovo, A pro Marinovo, B pro Braunovo,
M pro Mercatorovo). Poté se již vypýše seznam
x-ových souřadnic pro poledníky a y-ových souřadnic pro rovnoběžky, kde bod [0;0] je střed papíru. Dále uživatel dostane možnost
zadat nejprve zeměpisnou délku pak šířku jakéhokoli bodu a dostane opět jeho x,y souřadnice na papíře. Pokud nemá zájem další body
počítat, může zadat u obou vstupů 0 a program skončí.


## Funkčnost

Program má ořešeny všechny možnosti vstupů (krom zadávání double do raedInt apod.) a funguje pro 4 válcová tečná zobrazení.
Marinovo, Lambertovo, Braunovo a Mercatorovo. Pokud by vzdálenost rovnoběžky nebo poledníku od středu papíru překročila 1 metr,
vypíše se pouze pomlčka. Pokud uživatel jakkoli nesplní podmínky pro zadání argumentů nebo zadá hloupost, program to oznámí a ukončí se.
Při výběru Mercatorova zobrazení a zadání bodu se zem. šířkou +-90° program odpoví, že bod nelze zobrazit, jelikož pro Mercatorovo zobrazení
se 90. rovnoběžky nachází v nekonečnu.

## Vnitřek programu

Program se skládá z hlavní funkce, kde se dostanou první proměnné pomocí vstupů od uživatele a poté pomocí podmínek se volají
jednotlivé funkce pro výpočet zobrazení. Každá funkce má stejný průběh, jen s jiným vzorcem zobrazení. Jsou zde použité ještě pomocné funkce.
Rozdíl je pouze u Mercatorova zobrazení, kde je dodána podmínka pro vypisování záporných hodnot u záporných vstupů a také ošetření
90° zem. šířky, která se nachází v nekonečnu.
