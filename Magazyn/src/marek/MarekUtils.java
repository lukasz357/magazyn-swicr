package marek;

import java.util.ArrayList;
import java.util.List;

public class MarekUtils {
	
	
	
	
	/* szerokosc i wysokosc */
	public static int getWidth(){
		return 10;
	}
	
	public static int getHeight(){
		return 10;
	}
	
	
	public static List<Wspolrzedne> getWspolrzedneRegalow(){
		List<Wspolrzedne> lista = new ArrayList<Wspolrzedne>();
	
		//regal 1
		lista.add(new Wspolrzedne(3,2));
		lista.add(new Wspolrzedne(3,3));
		lista.add(new Wspolrzedne(3,4));
		lista.add(new Wspolrzedne(3,5));
		lista.add(new Wspolrzedne(3,6));
		lista.add(new Wspolrzedne(3,7));
		
		
		//regal 2
		lista.add(new Wspolrzedne(5,2));
		lista.add(new Wspolrzedne(5,3));
		lista.add(new Wspolrzedne(5,4));
		lista.add(new Wspolrzedne(5,5));
		lista.add(new Wspolrzedne(5,6));
		lista.add(new Wspolrzedne(5,7));
		
	
		return lista;
	}
	

}
