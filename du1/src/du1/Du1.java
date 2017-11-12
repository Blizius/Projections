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
//    Výběr zobrazení. Volání příslušných funkcí.
    public static void main(String[] args) throws IOException {
              
        System.out.println("Měřítko (celočíselné) je 1 : ");
        int scale = readInt();
        
        double R;
        System.out.println("Zadejte poloměr planety (Země). Pro referenční poloměr"
                + " Země používaný v kartogradii zadejte 0.");
        double r = readDouble();
        if (r == 0)
        {
            R = 637111000/scale;   
        }           
        else
        {
            R = r*100000/scale;
        }          
        
        System.out.println("Vyberte zobrazení vepsáním L pro Lambertovo, A pro Marinovo, "
                + "B pro Braunovo nebo M pro Mercatorovo zobrazení.");
        char projection = readChar();
        System.out.println();
        
        if (projection == 'A')
            Marin(R);            
        else if (projection == 'L')
            Lambert(R);    
        else if (projection == 'B')
            Braun(R);        
        else if (projection == 'M')
            Mercator(R);      
        else
            System.out.println("Tady někdo neumí číst zadání. Zkus to znovu a podívej se pořádně.");
              
    
        // TODO code application logic here
    }
//  Funkce pro výpočet zobrazení. Všechny na stejném principu. Do všech vstupuje
//  poloměr převedený na dané měřítko a nic nevystupuje, pouze se vypisuje.
//  Uvnitr vytvořeny proměnné pro zem. délku a šířku ve skutečnosti a na papíře.
//  Použití cyklu pro projití každé rovnoběžky a poledníku v daném rozsahu.
//  Podmínka pro vzdálenosti větší než 1 m. 
    public static void Marin(double r) throws IOException
    {        
        double v;
        double u;
        double x;
        double y;
        System.out.println("Vzdálenosti poledníků od středu papíru v cm (max 1 m):");
        for (v = -180; v < 181; v += 10)
        {
            double rad = abs(v)*(PI/180);
            x = r*rad;
            if (x <= 100.0)
                System.out.format("%.1f\n", x);
            else                           
                System.out.format("-\n");
        }
        
        System.out.println("Vzdálenosti rovnoběžek od středu papíru v cm (max 1 m):");
        for (u = -90; u < 91; u += 10)
        {
            double rad = abs(u)*(PI/180);
            y = r*rad;
            if (y <= 100.0)
                System.out.format("%.1f\n", y);
            else                           
                System.out.format("-\n");
        }      
        
         while (r != 0)
        {
            System.out.println("Pro ukončení programu zadejte v obou následujících"
                    + " vstupech 0.");
            System.out.println("Zadejte zeměpisnou délku hledaného bodu "
                    + "ve stupních (s desetinou čárkou, ne na minuty a vteřiny).");
            v = readDouble();
            System.out.println("Zadejte zeměpisnou šířku hledaného bodu "
                    + "ve stupních (s desetinou čárkou, ne na minuty a vteřiny).");
            u = readDouble();
            if (v == 0 && u == 0)
                break;
            else
            {
                double xRad = v*(PI/180);
                x = r*xRad;
                double yRad = u*(PI/180);
                y = r*yRad;
                System.out.format("Souřadnice [x ; y] bodu (v cm) na papíře jsou:\n"
                        + "[%.1f ; %.1f].\n", x, y);
            }
        }
    }
    
    public static void Lambert (double r) throws IOException
    {
        double v;
        double u;
        double x;
        double y;
        System.out.println("Vzdálenosti poledníků od středu papíru v cm (max 1 m):");
        for (v = -180; v < 181; v += 10)
        {
            double rad = abs(v)*(PI/180);
            x = r*rad;
            if (x <= 100.0)
                System.out.format("%.1f\n", x);
            else                           
                System.out.format("-\n");
        }
        
        System.out.println("Vzdálenosti rovnoběžek od středu papíru v cm (max 1 m):");
        for (u = -90; u < 91; u += 10)
        {
            double rad = abs(u)*(PI/180);
            y = r*sin(rad);
            if (y <= 100.0)
                System.out.format("%.1f\n", y);
            else                           
                System.out.format("-\n");
        }
        
        while (r != 0)
        {
            System.out.println("Pro ukončení programu zadejte v obou následujících"
                    + " vstupech 0.");
            System.out.println("Zadejte zeměpisnou délku hledaného bodu "
                    + "ve stupních (s desetinou čárkou, ne na minuty a vteřiny).");
            v = readDouble();
            System.out.println("Zadejte zeměpisnou šířku hledaného bodu "
                    + "ve stupních (s desetinou čárkou, ne na minuty a vteřiny).");
            u = readDouble();
            if (v == 0 && u == 0)
                break;
            else
            {
                double xRad = v*(PI/180);
                x = r*xRad;
                double yRad = u*(PI/180);
                y = r*sin(yRad);
                System.out.format("Souřadnice [x ; y] bodu (v cm) na papíře jsou:\n"
                        + "[%.1f ; %.1f].\n", x, y);
            }
            
        }
        
        
        
        
    }
    
    public static void Braun (double r) throws IOException
    {
        double v;
        double u;
        double x;
        double y;
        System.out.println("Vzdálenosti poledníků od středu papíru v cm (max 1 m):");
        for (v = -180; v < 181; v += 10)
        {
            double rad = abs(v)*(PI/180);
            x = r*rad;
            if (x <= 100.0)
                System.out.format("%.1f\n", x);
            else                           
                System.out.format("-\n");
        }
        
        System.out.println("Vzdálenosti rovnoběžek od středu papíru v cm (max 1 m):");
        for (u = -90; u < 91; u += 10)
        {
            double rad = abs(u)*(PI/180);
            y = 2*r*tan(rad/2);
            if (y <= 100.0)
                System.out.format("%.1f\n", y);
            else                           
                System.out.format("-\n");
        }
        
        while (r != 0)
        {
            System.out.println("Pro ukončení programu zadejte v obou následujících"
                    + " vstupech 0.");
            System.out.println("Zadejte zeměpisnou délku hledaného bodu "
                    + "ve stupních (s desetinou čárkou, ne na minuty a vteřiny).");
            v = readDouble();
            System.out.println("Zadejte zeměpisnou šířku hledaného bodu "
                    + "ve stupních (s desetinou čárkou, ne na minuty a vteřiny).");
            u = readDouble();
            if (v == 0 && u == 0)
                break;
            else
            {
                double xRad = v*(PI/180);
                x = r*xRad;
                double yRad = u*(PI/180);
                y = 2*r*tan(yRad/2);
                System.out.format("Souřadnice [x ; y] bodu (v cm) na papíře jsou:\n"
                        + "[%.1f ; %.1f].\n", x, y);
            }
        }    
    }
     public static void Mercator (double r) throws IOException
    {
        double v;
        double u;
        double x;
        double y;
        System.out.println("Vzdálenosti poledníků od středu papíru v cm (max 1 m):");
        for (v = -180; v < 181; v += 10)
        {
            double rad = abs(v)*(PI/180);
            x = r*rad;
            if (x <= 100.0)
                System.out.format("%.1f\n", x);
            else                           
                System.out.format("-\n"); 
        }
        
        System.out.println("Vzdálenosti rovnoběžek od středu papíru v cm (max 1 m):");
        for (u = -90; u < 91; u += 10)
        {
            double rad = (90 - abs(u))*(PI/180);
            y = r*log(1/tan(rad/2));
            if (y <= 100.0)
                System.out.format("%.1f\n", y);
            else
                System.out.format("-\n");
        }
        
        while (r != 0)
        {
            System.out.println("Pro ukončení programu zadejte v obou následujících"
                    + " vstupech 0.");
            System.out.println("Zadejte zeměpisnou délku hledaného bodu "
                    + "ve stupních (s desetinou čárkou, ne na minuty a vteřiny).");
            v = readDouble();
            System.out.println("Zadejte zeměpisnou šířku hledaného bodu "
                    + "ve stupních (s desetinou čárkou, ne na minuty a vteřiny).");
            u = readDouble();
            if (v == 0 && u == 0)
                break;
            else
            {
                double xRad = v*(PI/180);
                x = r*xRad;
                double yRad = (90 - abs(u))*(PI/180);
                y = r*log(1/tan(yRad/2));
                if (u < 0)
                {
                    y = -y;
                }                    
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
