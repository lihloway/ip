import java.io.File;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> data;
    protected Storage loader;

    private static final String FILE_PATH = "data/tasks.txt";
    private static final int MAXIMUM_CAPACITY = 100;
    TaskList() {
        loader = new Storage(FILE_PATH, MAXIMUM_CAPACITY);
        loader.loadData();
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
        loader.saveData(data);
    }
}
