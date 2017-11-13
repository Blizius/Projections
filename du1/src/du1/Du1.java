/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package du1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.log;
import static java.lang.Math.sin;
import static java.lang.Math.tan;
import static java.lang.Math.toRadians;

import java.util.Scanner;


/**
 *
 * @author jethro
 */
public class Du1 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
//    Hlavni funkce. Výběr měřítka a poloměru planety (Země) a jeho převedení do měřítka.
//    Výběr zobrazení. Volání příslušných funkcí. Všude ošetření nekorektních vstupů.
    public static void main(String[] args) throws IOException {
              
        System.out.println("Měřítko (celočíselné) je 1 : ");
        double scale = readDouble();
        if(scale <= 0 || ((int)scale - scale) != 0)
        {
            System.out.println("Už jsi někdy viděl neceločíselné, záporné nebo"
                    + " nulové měřítko?. Jak vůbec můžeš studovat tuhle školu?");
            System.exit(0);
        }
        
        double rMap;
        System.out.println("Zadejte poloměr planety (Země). Pro referenční poloměr"
                + " Země používaný v kartogradii zadejte 0.");
        double r = readDouble();
        if(r < 0)
        {
            System.out.println("Poloměr planety nemůže být záporný. Jak ses "
                    + "vůbec mohl dostat i přes základku?");
            System.exit(0);        
        }
        if (r == 0)
        {
            rMap = 637111000/scale; 
        }
        else        
        {
            rMap = r*100000/scale;
        }
                  
        
        System.out.println("Vyberte zobrazení vepsáním L pro Lambertovo, A pro Marinovo, "
                + "B pro Braunovo nebo M pro Mercatorovo zobrazení.");
        char projection = readChar();
        System.out.println();       
        
        if (projection == 'A')
        {
            Marin(rMap);
        }
        else if (projection == 'L')
        {
            Lambert(rMap);
        }
        else if (projection == 'B')
        {
            Braun(rMap);
        }
        else if (projection == 'M')
        {
            Mercator(rMap);  
        }
        else
        {
            System.out.println("Tady někdo neumí číst zadání. Zkus to znovu a podívej se pořádně.");  
        }
    }
    
//  Podmínka pro výpis pomlček nad 1 m.
    public static void PaperEdge(double coord)
    {
        if (abs(coord) <= 100.0)
        {
            System.out.format("%.1f\n", coord);
        }
        else              
        {
            System.out.format("-\n");
        }
    }
    
//  Funkce pro zadání souřadnic bodu, který chce uživatel zobrazit.  
    public static double [] point() throws IOException
    {
        double []vu;
        vu = new double [2];
        System.out.println("Pro ukončení programu zadejte v obou následujících"
                    + " vstupech 0.");
        System.out.println("Zadejte zeměpisnou délku hledaného bodu "
                    + "ve stupních (s desetinou čárkou, ne na minuty a vteřiny)."
                    + " Od -180° do 180°.");
        vu[0] = readDouble();
        if (abs(vu[0]) > 180)
        {
            System.out.println("Už chodíš dokola. Zadávej pouze výše definované vstupy.");
            System.exit(0);
        }
        
        System.out.println("Zadejte zeměpisnou šířku hledaného bodu "
                    + "ve stupních (s desetinou čárkou, ne na minuty a vteřiny)."
                    + " Od -90° do 90°.");
        vu[1] = readDouble();
        if (abs(vu[1]) > 90)
        {
            System.out.println("Už chodíš dokola. Zadávej pouze výše definované vstupy.");
            System.exit(0);
        }
        
        return vu;
    }
    
//  Funkce pro výpočet zobrazení. Všechny na stejném principu. Do všech vstupuje
//  poloměr převedený na dané měřítko a nic nevystupuje, pouze se vypisuje.
//  Uvnitř vytvořeny proměnné pro zem. délku a šířku ve skutečnosti a na papíře.
//  Použití cyklu pro projití každé rovnoběžky a poledníku v daném rozsahu.
//  Podmínka pro vzdálenosti větší než 1 m.
//  Poté cyklus pro opětovné počítání souřadnic zadaného bodu.    
    public static void Marin(double r) throws IOException
    {        
        double x;
        double y;
        System.out.println("Vzdálenosti poledníků od středu papíru v cm (max 1 m):");
        for (double v = -180; v < 181; v += 10)
        {
            x = r*toRadians(v);
            PaperEdge(x);
        }
        
        System.out.println("Vzdálenosti rovnoběžek od středu papíru v cm (max 1 m):");
        for (double u = -90; u < 91; u += 10)
        {            
            y = r*toRadians(u);
            PaperEdge(y);
        }      
        
         while (r != 0)
        {
            double [] vu = point();
            if (vu[0] == 0 && vu[1] == 0)
            {
                break;
            }
            else
            {                
                x = r*toRadians(vu[0]);                
                y = r*toRadians(vu[1]);
                System.out.format("Souřadnice [x ; y] bodu (v cm) na papíře jsou:\n"
                        + "[%.1f ; %.1f].\n", x, y);
            }
        }
    }   
    
    public static void Lambert (double r) throws IOException
    {
        double x;
        double y;
        System.out.println("Vzdálenosti poledníků od středu papíru v cm (max 1 m):");
        for (double v = -180; v < 181; v += 10)
        {
            x = r*toRadians(v);
            PaperEdge(x);
        }
        
        System.out.println("Vzdálenosti rovnoběžek od středu papíru v cm (max 1 m):");
        for (double u = -90; u < 91; u += 10)
        {            
            y = r*sin(toRadians(u));
            PaperEdge(y);
        }      
        
         while (r != 0)
        {
            double [] vu = point();
            if (vu[0] == 0 && vu[1] == 0)
            {
                break;
            }
            else
            {                
                x = r*toRadians(vu[0]);                
                y = r*sin(toRadians(vu[1]));
                System.out.format("Souřadnice [x ; y] bodu (v cm) na papíře jsou:\n"
                        + "[%.1f ; %.1f].\n", x, y);
            }
        }        
    }
    
    public static void Braun (double r) throws IOException
    {
        double x;
        double y;
        System.out.println("Vzdálenosti poledníků od středu papíru v cm (max 1 m):");
        for (double v = -180; v < 181; v += 10)
        {
            x = r*toRadians(v);
            PaperEdge(x);
        }
        
        System.out.println("Vzdálenosti rovnoběžek od středu papíru v cm (max 1 m):");
        for (double u = -90; u < 91; u += 10)
        {            
            y = 2*r*tan(toRadians(u)/2);
            PaperEdge(y);
        }      
        
         while (r != 0)
        {
            double [] vu = point();
            if (vu[0] == 0 && vu[1] == 0)
            {
                break;
            }
            else
            {                
                x = r*toRadians(vu[0]);                
                y = 2*r*tan(toRadians(vu[1])/2);
                System.out.format("Souřadnice [x ; y] bodu (v cm) na papíře jsou:\n"
                        + "[%.1f ; %.1f].\n", x, y);
            }
        }  
    }
    
     public static void Mercator (double r) throws IOException
    {
        double x;
        double y;
        System.out.println("Vzdálenosti poledníků od středu papíru v cm (max 1 m):");
        for (double v = -180; v < 181; v += 10)
        {
            x = r*toRadians(v);
            PaperEdge(x);
        }
        
        System.out.println("Vzdálenosti rovnoběžek od středu papíru v cm (max 1 m):");
        for (double u = -90; u < 91; u += 10)
        {            
            y = r*log(1/tan(toRadians(90-abs(u))/2));
            if (u < 0)
            {
               y = -y; 
            }                
            PaperEdge(y);
        }      
        
         while (r != 0)
        {
            double [] vu = point();
            if (vu[0] == 0 && vu[1] == 0)
            {
                break;
            }
            else if (abs(vu[1]) == 90)
            {
                System.out.format("Bod se zem. šířkou +- 90° nelze zobrazit,"
                        + " nachází se nekonečně daleko od středu papíru.\n\n");
            }
            else
            {                
                x = r*toRadians(vu[0]);                
                y = r*log(1/tan(toRadians(90-abs(vu[1]))/2));
                System.out.format("Souřadnice [x ; y] bodu (v cm) na papíře jsou:\n"
                        + "[%.1f ; %.1f].\n", x, y);
            }
        }
    }
    
    public static int readInt() throws IOException{
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }
    
    public static double readDouble() throws IOException{
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        return Double.parseDouble(reader.readLine());
    }
    
    public static char readChar() throws IOException{
        Scanner s= new Scanner(System.in);
        return  s.next().charAt(0);
    }
}
