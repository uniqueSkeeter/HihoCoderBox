package org.uinqueSkeeter.java;

import java.util.Scanner;

public class AAndB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A,B;
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
            A = scanner.nextInt();
            B = scanner.nextInt();
            System.out.println(A+B);
        }
	}

}
