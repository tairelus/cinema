package com.bjs;

import com.bjs.cinema.Cinema;
import com.bjs.cinema.Order;
import com.bjs.visitor.Visitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Type '" + Cinema.QUIT_CODE + "' to terminate program.\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Cinema cinema = new Cinema("Butterfly Country", reader);

            System.out.println("Enter number of visitors:");
            int visitorsNum = Cinema.getIntegerValue(reader);

            for (int i = 0; i < visitorsNum; ++i) {
                Visitor visitor = getVisitor(reader);
                Order order = cinema.getOrder(visitor);

                if (order == null) {
                    System.out.println("Your order canceled. Visit us next time!");
                } else {
                    order.setVisitor(visitor);
                    System.out.println("Have a nice view! :-)\n");
                }
            }
        } catch (TerminateException e) {
            System.out.println(e);
        }
    }

    /**
     * Create and store current visitor
     */
    public static Visitor getVisitor(BufferedReader reader) throws TerminateException {
        System.out.println("Hello! Please, enter your age:");
        int retVal = Cinema.getIntegerValue(reader);
        return new Visitor(retVal);
    }
}
