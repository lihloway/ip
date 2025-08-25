public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description){
        this.description = description;
        this.uncomplete();
    }
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public void complete(){
        isDone = true;
    }

    public void uncomplete(){
        isDone = false;
    }

    public void change_completion(){
        isDone = !isDone;
    }
    @Override
    public String toString(){
        if (isDone){
            return "[X] "+ description;
        }
        return "[ ] "+ description;
    }

    public boolean isDone(){
        return this.isDone;
    }
}
