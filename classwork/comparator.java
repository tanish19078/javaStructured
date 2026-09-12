import java.util.*;

class Employee {
    String name;
    double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}

class SortBySalary implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        if (e1.salary < e2.salary) return -1;
        if (e1.salary > e2.salary) return 1;
        return 0;
    }
}

public class comparator {
    
    static void sortEmployees(Employee[] emp) {
        Arrays.sort(emp, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                if (e1.salary < e2.salary) return -1;
                if (e1.salary > e2.salary) return 1;
                return 0;
            }
        });
    }
    
    static void print(Employee[] emp) {
        for (Employee e : emp) {
            System.out.print(e.name + ":" + e.salary + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Employee[] emp = {
            new Employee("John", 45000),
            new Employee("Alice", 75000),
            new Employee("Bob", 35000),
            new Employee("David", 65000)
        };
        
        System.out.print("Original: ");
        print(emp);
        
        sortEmployees(emp);
        System.out.print("Sorted: ");
        print(emp);
    }
}