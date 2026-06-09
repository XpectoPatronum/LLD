package org.example.designPatterns.cor;

public abstract class InterviewHandler {
    protected InterviewHandler next;

    protected InterviewHandler setNextHandler(InterviewHandler next){
        this.next = next;
        return next;
    }

    protected void callNext(String candidate){
        if(this.next == null){
            System.out.println(candidate + " passed all interviews \n");
        }else{
            next.hire(candidate);
        }
    }

    public abstract void hire(String candidate);
}
