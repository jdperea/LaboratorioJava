/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package micalculadora;

import java.util.Scanner;

/**
 *
 * @author jdperea
 */
public class ClaseSwitch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numero = scanner.nextInt();
        
        if(numero >= 0){
            System.out.println("El numero es mayor o igual que 0");
        } else {
            System.out.println("El numero es menor que 0");
        }
    }
}
