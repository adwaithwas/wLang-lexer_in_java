package com.adwaith;

import java.util.List;
import java.util.Scanner;

public class shell {
    public static void main(String[] Args){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print("wLang > ");
            String text = scan.nextLine();

            if (text.equals("exit")) {
                break;
            }
            Run run = new Run(text);
            List<Tokens> result = run.run();

            for (Tokens token : result) {
                System.out.println(token);
            }
        }


    }
}
