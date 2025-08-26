public enum Response {
    MARK_SUCCESS("Nice! I've marked this task as done:\n"),
    UNMARK_SUCCESS("OK, I've marked this task as not done yet:\n"),
    TASK_SUCCESS("Got it. I've added this task:"),
    NUMBER_FAILURE("I need a number index"),
    MARK_FAILURE("There's nothing to mark!"),
    UNMARK_FAILURE("There's nothing to unmark!"),
    DELETE_FAILURE("There's nothing to delete!"),
    LIST_FAILURE("There's nothing to list!"),
    LIST_TASKS("Here are the tasks in your list:"),
    REMOVE_TASK("Noted. I've removed this task:\n");

    private final String message;
    Response(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
