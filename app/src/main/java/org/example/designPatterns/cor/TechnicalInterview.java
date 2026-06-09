package org.example.designPatterns.cor;

import java.util.Scanner;

public class TechnicalInterview extends InterviewHandler{
    @Override
    public void hire(String candidate) {
        Scanner sc = new Scanner(System.in);
        int score = sc.nextInt();
        if(score >= 70){
            System.out.println(candidate + " has passed in technical interview\n");
            callNext(candidate);
            return;
        }
        System.out.println(candidate + " has failed in technical interview\n");
    }
}
