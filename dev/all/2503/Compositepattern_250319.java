import java.util.ArrayList;
import java.util.List;

// 1. Component (공통 인터페이스)
interface Employee {
    void showDetails();
}

// 2. Leaf (직원 - 개별 객체)
class IndividualEmployee implements Employee {
    private String name;
    private String position;

    public IndividualEmployee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public void showDetails() {
        System.out.println(position + ": " + name);
    }
}

// 3. Composite (부서 - 여러 직원과 부서를 포함)
class Department implements Employee {
    private String name;
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public void showDetails() {
        System.out.println("Department: " + name);
        for (Employee employee : employees) {
            employee.showDetails();
        }
    }
}

// 4. 클라이언트 코드
public class CompositePatternOrganizationExample {
    public static void main(String[] args) {
        // 개별 직원 생성
        Employee emp1 = new IndividualEmployee("Alice", "Developer");
        Employee emp2 = new IndividualEmployee("Bob", "Designer");
        Employee emp3 = new IndividualEmployee("Charlie", "QA Engineer");

        // 개발 부서 생성 및 직원 추가
        Department devDepartment = new Department("Development Department");
        devDepartment.addEmployee(emp1);
        devDepartment.addEmployee(emp3);

        // 디자인 부서 생성 및 직원 추가
        Department designDepartment = new Department("Design Department");
        designDepartment.addEmployee(emp2);

        // 회사 전체(최상위 Composite) 생성 및 부서 추가
        Department company = new Department("Tech Company");
        company.addEmployee(devDepartment);
        company.addEmployee(designDepartment);

        // 조직도 출력
        company.showDetails();
    }
}
