package com.jd.www.jdk_eight.lambda_test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/13 下午4:16</li>
 * <li>function:</li>
 * </ul>
 */
public class RosterTest {
    interface CheckPerson{
        boolean test(Person person);
    }
    //approach 1
    public static void printPersonsOlderThan(List<Person> roster,int age){
        for(Person p : roster){
            if(p.getAge()>=age){
                p.printPerson();
            }
        }
    }
    //approach 2
    public static void printPersonWithinAgeRange(List<Person> roster,int low,int high){
        for(Person p:roster){
            if(low<=p.getAge()&&p.getAge()<high){
                p.printPerson();
            }
        }
    }

    //approach 3 specify search criteria code in a local class 内部类
    //approach 4 specify search criteria code in an anonymous class 匿名类
    //approach 5 specify search criteria code with a lambda expression lambda表达式
    public static void printPersons(List<Person> roster,CheckPerson tester){
        for(Person p:roster){
            if(tester.test(p)){
                p.printPerson();
            }
        }
    }

    //approach 6：use standard functional interfaces with lambda expressions
    public static void printPersonsWithPredicate(List<Person> roster,Predicate<Person> tester){
       for(Person p : roster){
           if(tester.test(p)){
               p.printPerson();
           }
       }
    }

    //approach 7：use lambda expressions throughout your application
    public static void processPersons(List<Person> roster,Predicate<Person> tester,Consumer<Person> block){
        for(Person p : roster){
            if(tester.test(p)){
                block.accept(p);
            }
        }
    }
    //approach 7: second example
    public static void processPersonsWithFunction(List<Person> roster,Predicate<Person> tester,Function<Person,String> mapper,Consumer<String> block){
        for(Person p : roster){
            if(tester.test(p)){
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    //approach 8
    public static <X,Y> void processElements(Iterable<X> source,Predicate<X> tester,Function<X,Y> mapper,Consumer<Y> block){
        for(X p:source){
            if(tester.test(p)){
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();
        for(Person p:roster){
            p.printPerson();
        }

        //approach 1 create methods that search for persons that match one
        System.out.println("persons older than 20:");
        printPersonsOlderThan(roster,20);
        System.out.println();

        //approach 2  create more generalized search methods
        System.out.println("persons between the ages of 14 and 30:");
        printPersonWithinAgeRange(roster,14,30);
        System.out.println();

        //approach 3 specify search criteria code in local class
        System.out.println("persons who are eligible for selective service:");
        class CheckPersonEligibleForSelectiveService implements CheckPerson{

            @Override
            public boolean test(Person person) {
                return person.getGender()== Person.Sex.MALE && person.getAge()>=18&& person.getAge()<=25;
            }
        }

        printPersons(roster,new CheckPersonEligibleForSelectiveService());
        System.out.println();

        //approach 4:specify search criteria code in anonymous class
        System.out.println("persons who are eligible for selective service (anonymous class):");
        printPersons(roster, new CheckPerson() {
            @Override
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        });

        System.out.println();

        //approach 5:specify search criteria code with a lambda expression
        //expressions
        System.out.println("persons who are eligible for selective service (lambda expression):");
        printPersons(roster,(Person p ) -> p.getGender() == Person.Sex.MALE&&p.getAge()>=18&&p.getAge()<=25);


        //approach 6 : use standard functional interfaces with lambda
        System.out.println("persons who are eligible for selective service (with predicate parameter):");
        printPersonsWithPredicate(roster,p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25);

        System.out.println();

        //approach 7,second
        System.out.println("Persons who are eligible for selective service"+"(with " +
                "predicate ,function,and consumer parameters:)");
        processPersonsWithFunction(roster,p->p.getGender()==Person.Sex.MALE&&p.getAge()>=18&&p.getAge()<=25,p->p.getEmailAddress(),email-> System.out.println(email));

        //approach 8 : use generics more extensively
        System.out.println("persons who are eligible for selective service (generic version)");
        processElements(roster,p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25,p->p.getEmailAddress(),email-> System.out.println(email));
        System.out.println();


        //approach 9:use bulk data operations that accept lambda expressions as parameters
        System.out.println("persons who are eligible for selective service (with bulk data operations):");
        roster.stream().filter(p->p.getGender()==Person.Sex.MALE&&p.getAge()>=18&&p.getAge()<=25)
                .map(p->p.getEmailAddress()).forEach(email-> System.out.println(email));
    }

}
