import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    File file;
    int max;
    Storage(String filepath, int max) {
        this.file = new File(filepath);
        this.max = max;
    }
    protected ArrayList<Task> loadData(){

        ArrayList<Task> data = new ArrayList<>(max);

        try {
            if (!file.exists()) {
                // If file doesn't currently exist, create it
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                Task t = Task.fromFile(line); // parse back into Task
                data.add(t);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }
    protected void saveData(ArrayList<Task> data){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task t: data){
                bw.write(t.toFile());
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
