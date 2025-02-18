//Vatsalya 22BCS12876
import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return "[ID: " + id + ", Name: " + name + ", Salary: " + salary + "]";
    }
}

class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    void addEmployee() {
        System.out.print("Enter ID: "); int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: "); String name = sc.nextLine();
        System.out.print("Enter Salary: "); double salary = sc.nextDouble();
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee Added!");
    }

    void updateEmployee() {
        System.out.print("Enter ID to Update: ");
        int id = sc.nextInt();
        for (Employee e : employees) {
            if (e.id == id) {
                sc.nextLine();
                System.out.print("New Name: "); e.name = sc.nextLine();
                System.out.print("New Salary: "); e.salary = sc.nextDouble();
                System.out.println("Employee Updated!");
                return;
            }
        }
        System.out.println("Employee Not Found!");
    }

    void removeEmployee() {
        System.out.print("Enter ID to Remove: ");
        int id = sc.nextInt();
        if (employees.removeIf(e -> e.id == id)) 
            System.out.println("Employee Removed!");
        else 
            System.out.println("Employee Not Found!");
    }

    void searchEmployee() {
        System.out.print("Enter ID to Search: ");
        int id = sc.nextInt();
        employees.stream().filter(e -> e.id == id).findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Not Found!"));
    }

    void displayEmployees() {
        if (employees.isEmpty()) System.out.println("⚠ No Employees Found!");
        else employees.forEach(System.out::println);
    }
}

public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1️⃣ Add  2️⃣ Update  3️⃣ Remove  4️⃣ Search  5️⃣ Display  6️⃣ Exit");
            System.out.print("Choice: ");
            switch (sc.nextInt()) {
                case 1 -> service.addEmployee();
                case 2 -> service.updateEmployee();
                case 3 -> service.removeEmployee();
                case 4 -> service.searchEmployee();
                case 5 -> service.displayEmployees();
                case 6 -> { System.out.println("🚪 Exiting..."); return; }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}