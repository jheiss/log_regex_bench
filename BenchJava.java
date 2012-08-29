import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BenchJava {
  public static void main(String[] args) {
    String corpus = args.length > 0 ? args[0] : "corpus-syslog";
    
    ArrayList<Matcher> alertList = loadRegexFile("regex-alert");
    ArrayList<Matcher> suppressList = loadRegexFile("regex-suppress");
    
    Date startTime = new Date();
    
    // Apply regexes to corpus, counting hits and misses
    int alertCount = 0;
    int suppressCount = 0;
    int limboCount = 0;
    try {
      BufferedReader in
         = new BufferedReader(new FileReader(corpus));
      String line;
      while ((line = in.readLine()) != null) {
        boolean matched = false;
        for (Matcher alert : alertList) {
          alert.reset(line);
          if (alert.find()) {
            alertCount++;
            matched = true;
            break;
          }
        }
        if (matched) {
          continue;
        }
        for (Matcher suppress : suppressList) {
          suppress.reset(line);
          if (suppress.find()) {
            suppressCount++;
            matched = true;
            break;
          }
        }
        if (matched) {
          continue;
        }
        limboCount++;
      }
      in.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
    
    Date endTime = new Date();
    
    System.out.println("Elapsed time: " + ((endTime.getTime() - startTime.getTime()) / 1000.0));
    System.out.println("Alert: " + alertCount);
    System.out.println("Suppress: " + suppressCount);
    System.out.println("Limbo: " + limboCount);
  }
  
  private static ArrayList<Matcher> loadRegexFile(String file) {
    Pattern blankLinePattern = Pattern.compile("^\\s*$");
    Pattern commentLinePattern = Pattern.compile("^\\s*#");
    ArrayList<Matcher> regexes = new ArrayList<Matcher>();
    try {
      BufferedReader in
         = new BufferedReader(new FileReader(file));
      String line;
      while ((line = in.readLine()) != null) {
        if (blankLinePattern.matcher(line).find()) {
          continue;
        }
        if (commentLinePattern.matcher(line).find()) {
          continue;
        }
        Pattern p = Pattern.compile(line);
        regexes.add(p.matcher(""));
      }
      in.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
    return(regexes);
  }
}

