package org.example.designPatterns.cor;

import java.util.Scanner;

public class HiringManagerInterview extends InterviewHandler{
    @Override
    public void hire(String candidate) {
        Scanner sc = new Scanner(System.in);
        int score = sc.nextInt();
        if(score >= 80){
            System.out.println(candidate + " has passed in HRM interview\n");
            callNext(candidate);
            return;
        }
        System.out.println(candidate + " has failed in HRM interview\n");
    }
}
