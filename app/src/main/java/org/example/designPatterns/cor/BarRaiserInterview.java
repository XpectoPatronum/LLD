package org.example.designPatterns.cor;

import java.util.Scanner;

public class BarRaiserInterview extends InterviewHandler{
    @Override
    public void hire(String candidate) {
        Scanner sc = new Scanner(System.in);
        int score = sc.nextInt();
        if(score >= 90){
            System.out.println(candidate + " has passed in bar raiser interview\n");
            callNext(candidate);
            return;
        }
        System.out.println(candidate + " has failed in bar raiser interview\n");
    }
}
