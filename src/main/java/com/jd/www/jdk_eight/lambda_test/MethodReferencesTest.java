package com.jd.www.jdk_eight.lambda_test;

import java.util.*;
import java.util.function.Supplier;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/15 下午3:06</li>
 * <li>function:</li>
 * </ul>
 */
public class MethodReferencesTest {

    public static <T,SOURCE extends Collection<T>,DEST extends Collection<T>> DEST transferElements(SOURCE sourceCollection,Supplier<DEST> collectionFactory){
        DEST result = collectionFactory.get();
        for(T t:sourceCollection){
            result.add(t);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();

        for(Person p:roster){
            p.printPerson();
        }

        Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);

        class PersonAgeComparator implements Comparator<Person>{

            @Override
            public int compare(Person o1, Person o2) {
                return o1.getBirthday().compareTo(o2.getBirthday());
            }
        }

        //without method reference
        Arrays.sort(rosterAsArray,new PersonAgeComparator());

        //with lambda expression
        Arrays.sort(rosterAsArray,(a,b)->{return a.getBirthday().compareTo(b.getBirthday());});


        Arrays.sort(rosterAsArray,(a,b)->Person.compareByAge(a,b));
        //with method reference  方法引用 :: 两个引号
        Arrays.sort(rosterAsArray,Person::compareByAge);



        //Reference to an instance method of a particular object
        class ComparisonProvider {
            public int compareByName(Person a,Person b){
                return a.getName().compareTo(b.getName());
            }

            public int compareByAge(Person a,Person b){
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }

        ComparisonProvider myComparisonProvider = new ComparisonProvider();
        Arrays.sort(rosterAsArray,myComparisonProvider::compareByAge);


        //reference to an instance method of an arbitrary object of a particular type
        String[] stringArray = {"barbara","james","mary","john","patricia"};
        Arrays.sort(stringArray,String::compareToIgnoreCase);


        Set<Person> rosterSetLambda = transferElements(roster,HashSet::new);




        System.out.println("printing rosterSet:");
        rosterSetLambda.stream().forEach(p->p.printPerson());

    }


}
