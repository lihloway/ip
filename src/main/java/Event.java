public class Event extends Task{
    String start;
    String end;
    public Event(String description, String start, String end){
        this.description = description;
        this.isDone = false;
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from: " + start + " to: " + end + ")";
    }
}
