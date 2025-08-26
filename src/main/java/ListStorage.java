import java.io.*;
import java.util.ArrayList;

public class ListStorage {
    protected ArrayList<Task> data;

    private static final String FILE_PATH = "data/tasks.txt";
    private static final int MAXIMUM_CAPACITY = 100;
    ListStorage() {
        data = new ArrayList<>(MAXIMUM_CAPACITY);
        File file = new File(FILE_PATH);

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
    }
    protected void complete(int index){
        data.get(index).complete();
    }
    protected void uncomplete(int index){
        data.get(index).uncomplete();
    }
    protected String print(int index){
        return data.get(index).toString();
    }
    protected String printLast(){
        return data.get(data.size()-1).toString();
    }
    protected void remove(int index){
        data.remove(index);
        saveData();
    }
    protected int getSize(){
        return data.size();
    }
    protected void add(Task t){
        data.add(t);
        saveData();
        if(data.size() == 100){
            throw new HarryException("I'm Full");
        }
    }
    private void saveData(){
        File file = new File(FILE_PATH);
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
