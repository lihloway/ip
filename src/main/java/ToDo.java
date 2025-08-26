public class ToDo extends Task{
    public ToDo(String description){
        this.description = description;
        this.isDone = false;
    }
    public ToDo(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
