import java.util.Scanner;

public class SafeInput
{
    /**
     * gets a string value from the user that is at lease one character
     *
     * @param pipe   Scanner to read input
     * @param prompt Prompt to tell the user what to input
     * @return String that is at least one character
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retVal = "";

        do {
            System.out.print(prompt + ": ");
            retVal = pipe.nextLine();
            if (retVal.isEmpty())
                System.out.println("You must enter at lease one character. ");

        }
        while (retVal.isEmpty());
        return retVal;

    }

    /**
     * Gets an unconstrained int value from the user
     *
     * @param pipe   Scanner for input
     * @param prompt prompt that asks the user what to input
     * @return unconstrained int value
     */
    public static int getInt(Scanner pipe, String prompt) {

        int retVal = 0;
        String trash;
        boolean done = false;

        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer not " + trash);
            }

        }
        while (!done);
        return retVal;
    }
    /**
     * Gets an unconstrained int value from the user
     *
     * @param pipe Scanner for input
     * @param prompt prompt that asks the user what to input
     * @return returns an integer
     */
    public static double getDouble(Scanner pipe, String prompt)
    {

        double retVal = 0;
        String trash;
        boolean done = false;

        do
        {
            System.out.print(prompt + ": ");
            if(pipe.hasNextDouble())
            {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double not " + trash);
            }

        }
        while(!done);
        return retVal;
    }
    /**
     * Gets an int value from the user within a specified inclusive range
     *
     * @param pipe Scanner for input
     * @param prompt prompt that asks the user what to input
     * @return int value in specified range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {

        int retVal = 0;
        String trash;
        boolean done = false;

        do
        {
            System.out.print(prompt + " [" + low +" - " + high +"]: ");
            if(pipe.hasNextInt())
            {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if(retVal >= low && retVal <=high) {
                    done = true;
                }
                else
                    System.out.println("You must enter a valid int in range [" + low + " - " + high +"]: ");
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer not " + trash);
            }

        }
        while(!done);
        return retVal;
    }
    /**
     * Gets a double value from the user within a specified inclusive range
     *
     * @param pipe Scanner for input
     * @param prompt prompt that asks the user what to input
     * @return double value in specified range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {

        double retVal = 0;
        String trash;
        boolean done = false;

        do
        {
            System.out.print(prompt + " [" + low +" - " + high +"]: ");
            if(pipe.hasNextDouble())
            {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if(retVal >= low && retVal <=high) {
                    done = true;
                }
                else
                    System.out.println("You must enter a valid double in range [" + low + " - " + high +"]: ");
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double not " + trash);
            }

        }
        while(!done);
        return retVal;
    }
    /**
     * gets a string value from the user of [YyNn]
     *
     * @param pipe Scanner to read input
     * @param prompt Prompt to tell the user what to input
     * @return Boolean true for Yy (yes) and false for Nn (no)
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        boolean retVal = false;
        boolean done = false;
        String input = "";

        do
        {
            System.out.print(prompt + "[YyNn]: ");
            input = pipe.nextLine();
            if(input.isEmpty())
            {
                System.out.println("You must enter y or n ");
            }
            else if (input.equalsIgnoreCase("Y"))
            {
                retVal = true;
                done = true;
            }
            else if (input.equalsIgnoreCase("N"))
            {
                retVal = false;
                done = true;
            }
            else
            {
                System.out.println("You must enter a Y or N not: " + input);
            }
        }
        while(!done);
        return retVal;
    }

    /**
    * gets a string value from the user of [YyNn]
     *
     * @param pipe Scanner to read input
     * @param prompt Prompt to tell the user what to input
     * @param regEx String - tells the user what to input
     * @return
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {

        boolean done = false;
        String retVal = "";

        do
        {
            System.out.print(prompt + regEx + ": "  );
            retVal = pipe.nextLine().trim();
            if(retVal.matches(regEx))
                done = true;
            else
            {
                System.out.println("You must enter a value that matches pattern " + regEx + " not " + retVal);
            }

        }
        while(!done);
        return retVal;
    }
    /**
     * Makes a pretty header
     *
     * @param msg message for header
     * @param pattern symbol that gets printed around the message in the header
     */
    public static void prettyHeader(String msg, String pattern) {
        int msgL = msg.length();
        int totalL = 0;
        int spacing = 0;
        for (int s = 0; s < 60; s++) {
            System.out.print(pattern);
        }
        System.out.println();
        if (msgL % 2 == 0) {
            for (int s = 0; s < 3; s++) {
                System.out.print(pattern);
            }
            totalL = 54;
            spacing = (totalL - msg.length()) / 2;
            System.out.printf("%" + spacing + "s%s", "", msg);
            System.out.printf("%" + spacing + "s", "");
            for (int s = 0; s < 3; s++) {
                System.out.print(pattern);
            }

        }
        if (msgL % 2 == 1)
        {
            for (int s = 0; s <= 3; s++) {
                System.out.print(pattern);
            }
            totalL = 53;
            spacing = (totalL - msg.length()) / 2;
            System.out.printf("%" + spacing + "s%s", "", msg);
            System.out.printf("%" + spacing + "s", "");
            for (int s = 0; s < 3; s++)
            {
                System.out.print(pattern);

            }
        }
        System.out.println();
        for (int s = 0; s < 60; s++)
        {
            System.out.print(pattern);
        }
    }
}