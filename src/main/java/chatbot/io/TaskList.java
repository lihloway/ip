package chatbot.io;

import chatbot.exception.HarryException;
import chatbot.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> data;
    protected Storage loader;

    private static final String FILE_PATH = "data/tasks.txt";
    private static final int MAXIMUM_CAPACITY = 100;
    public TaskList() {
        loader = new Storage(FILE_PATH, MAXIMUM_CAPACITY);
        data = loader.loadData();
    }
    public void complete(int index){
        data.get(index).complete();
    }
    public void uncomplete(int index){
        data.get(index).uncomplete();
    }
    public String print(int index){
        return data.get(index).toString();
    }
    public String printLast(){
        return data.get(data.size()-1).toString();
    }
    public void remove(int index){
        data.remove(index);
        saveData();
    }
    public int getSize(){
        return data.size();
    }
    public void add(Task t){
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
