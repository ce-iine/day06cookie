import java.io.BufferedReader;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Fortunes {

    private final List<String> fortunesList;
    private int totalFortunes;
    public Random rand = new SecureRandom();

    public Fortunes (String file) throws Exception { // if got error reading file try change string to fortunesfile

        try { 
            FileReader fr = new FileReader(file);
            BufferedReader filebr = new BufferedReader(fr);
            
            fortunesList = filebr.lines()// .lines() function Returns a Stream, the elements of which are lines read from this BufferedReader.
                .map(line -> line.trim())
                .toList();

            totalFortunes = fortunesList.size();
        } finally {

        }
    
    }
    
    public String get() { // String Fortunes.get 
        return get(1).get(0);
     }

   public List<String> get(int count) { // this new method needs to return type List<String>
      List<String> toReturn = new LinkedList<>(); 
      // create new List called toReturn which stores all the random lines selected from the fortune cookie file. whenever Fortunes.get is used, the List of lines will be returned
      for (int i = 0; i < count; i++) {
         int idx = rand.nextInt(totalFortunes); // generate random index that is less than the size of list that stores the fortune cookie lines - each index stores a fortune cookie line 
         toReturn.add(fortunesList.get(idx));  // the fortunesList list that stores all the fortune cookie lines gets the line from the random index generated. that line is added to the toReturn list 
      }
      return toReturn;
   }


}
