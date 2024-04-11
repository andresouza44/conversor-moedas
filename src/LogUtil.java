import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {

    public static void salvarLog(LogEntity log){
        try (BufferedWriter bw = new BufferedWriter (new FileWriter("log.txt",true))){

            ZonedDateTime zonedLocalDateTime = ZonedDateTime.now(ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String logDate = formatter.format(zonedLocalDateTime);

                bw.write(logDate +"; " +log.toString());
              //  bw.newLine();
                bw.flush();
        }
        catch (IOException e ){
            System.out.println(e.getMessage());
        }
    }

    public static  void loadLog(){
        try(BufferedReader br = new BufferedReader( new FileReader("log.txt"))){
            String line = br.readLine();
            while (line != null){
                System.out.println(line);
                line = br.readLine();
            }
        }
        catch(IOException e ){
            System.out.println(e.getMessage());
        }

    }
}
