//1. User 클래스
public class User {
    private String name;
    private String role;  // "admin" 또는 "user"

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}

// 2. Iterator 인터페이스
public interface Iterator<T> {

    boolean hasNext();
    T next();
}

// 3. UserIterator 클래스 (조건 기반 필터링 지원)
public class UserIterator implements Iterator<User> {
    private List<User> users;
    private String filterRole;
    private int position = 0;

    public UserIterator(List<User> users, String filterRole) {
        this.users = users;
        this.filterRole = filterRole;
    }

    @Override
    public boolean hasNext() {
        while (position < users.size()) {
            if (users.get(position).getRole().equalsIgnoreCase(filterRole)) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public User next() {
        return users.get(position++);
    }
}

// 4. UserCollection 클래스
public class UserCollection {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public Iterator<User> getIterator(String role) {
        return new UserIterator(users, role);
    }
}

// 5. 클라이언트 코드 (사용자 순회)
public class IteratorPatternClient {
    public static void main(String[] args) {
        UserCollection userCollection = new UserCollection();
        userCollection.addUser(new User("Alice", "admin"));
        userCollection.addUser(new User("Bob", "user"));
        userCollection.addUser(new User("Charlie", "admin"));
        userCollection.addUser(new User("David", "user"));

        System.out.println("관리자 목록:");
        Iterator<User> adminIterator = userCollection.getIterator("admin");

        while (adminIterator.hasNext()) {
            User admin = adminIterator.next();
            System.out.println("- " + admin.getName());
        }
    }
}




















