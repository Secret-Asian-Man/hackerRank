/**
 * Created by David Wu on 12/9/2016.
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Contacts
{
    public Contacts()
    {
        in = new Scanner(System.in);
        contacts = new TreeMap<>();

        for (int i=0;i<26;++i)
            contacts.put((char)(i+97) ,new LinkedList<>()); //i+97 is a - z

    }

    Scanner in;
    Map<Character, LinkedList<String> > contacts;

    private void processFile()
    {
        int n = in.nextInt();
        String op;
        String contact;

        for(int i = 0; i < n; ++i)
        {
            op = in.next();
            contact = in.next();

            if (op.equals("add"))
                addToContacts(contact);

            else if(op.equals("find"))
            {
                System.out.println(findPartial(contact));
            }

        }

    }

    private void addToContacts(String input)
    {
        contacts.get(input.charAt(0)).add(input);
    }

    public int findPartial(String partialWord)
    {
        //3 options
        // Map<Character, TreeSet<String> > old way
        // Map<Character, TreeSet<String> > custom TreeSet search
        // Map<Character, Map<Character, Map<Character, Map...> > 26^26 matrix (Extremely fast but extremely memory intensive) (Also makes add function slow)

        //possibly make a temp list where unmatching items are continously removed

        int numberFound = 0;
        LinkedList<String> tempList = contacts.get(partialWord.charAt(0));
        Iterator<String> it = tempList.listIterator(0); //saves me 2 function calls when I use .listIterator(0) instead of .iterator()
        String tempWord;

        while(it.hasNext())
        {
            tempWord = it.next();

            if (partialWord.length() <= tempWord.length() && partialWord.equals(tempWord.substring(0,partialWord.length()))) //if partial word is smaller or equal to list word, and the first Nth of both words match
                numberFound++;

        }


        return numberFound;
    }

    public static void main(String[] args)
    {
        Contacts test = new Contacts();
        test.processFile();
    }

}




// @@@@@@@@@@@@@@ main()
//        Scanner in = new Scanner(System.in);
//        Set<String> contacts = new TreeSet<>();
//        int numberFound;
//
//        int n = in.nextInt();
//        for(int i = 0; i < n; ++i)
//        {
//            String op = in.next();
//            String contact = in.next();
//
//
//            if (op.equals("add"))
//                contacts.add(contact);
//
//            else if(op.equals("find"))
//            {
//                numberFound = 0;
//
//                String temp;
//                for (Iterator<String> it = contacts.iterator();it.hasNext(); )
//                {
//                    temp = it.next();
//
//                    if (contact.length() <= temp.length() && contact.equals(temp.substring(0,contact.length())))
//                        numberFound++;
//                }
//
//                System.out.println(numberFound);
//            }
//
//        }



//@@@@@@   findPartial()

//        int numberFound = 0;
//        String temp;
//
//
//
//
//        for (Iterator<String> it = contacts.iterator();it.hasNext(); )
//        {
//            temp = it.next();
//
//            if (partialWord.length() <= temp.length() && partialWord.equals(temp.substring(0,partialWord.length())))
//                numberFound++;
//        }
//
//        return numberFound;