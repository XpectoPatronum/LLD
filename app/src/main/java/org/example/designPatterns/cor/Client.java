package org.example.designPatterns.cor;

public class Client {
    public static void main(String[] args) {
        InterviewHandler client = new TechnicalInterview();
        client.setNextHandler(new BarRaiserInterview()).setNextHandler(new HiringManagerInterview());
        client.hire("Prakhar");
    }
}
