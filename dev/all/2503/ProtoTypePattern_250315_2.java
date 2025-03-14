import java.util.HashMap;
import java.util.Map;

// Prototype 인터페이스
// 데이터베이스 쿼리 결과 캐싱
//🗃️ 사용 이유
//데이터베이스에서 동일한 쿼리를 반복 실행하면 성능 저하가 발생할 수 있음.
//Prototype 패턴을 사용하면 쿼리 결과를 캐싱하여 객체를 복제할 수 있음
interface QueryResult extends Cloneable {
    QueryResult clone();
}

// 데이터베이스 쿼리 결과 저장
class UserData implements QueryResult {
    private String username;
    private String email;

    public UserData(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public QueryResult clone() {
        return new UserData(this.username, this.email);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}

// 캐싱 시스템
class QueryCache {
    private Map<String, QueryResult> cache = new HashMap<>();

    public void storeQueryResult(String key, QueryResult result) {
        cache.put(key, result);
    }

    public QueryResult getQueryResult(String key) {
        return cache.get(key).clone();
    }
}

// 클라이언트 코드
public class DatabasePrototypeExample {
    public static void main(String[] args) {
        QueryCache cache = new QueryCache();

        // 쿼리 결과를 캐싱
        cache.storeQueryResult("user_123", new UserData("Alice", "alice@example.com"));

        // 캐싱된 데이터 복제
        UserData clonedUser = (UserData) cache.getQueryResult("user_123");

        System.out.println("Original: " + ((UserData) cache.getQueryResult("user_123")).getUsername());
        System.out.println("Cloned: " + clonedUser.getUsername());
    }
}
//✅ 이점: 동일한 데이터베이스 요청을 줄이고, 캐싱된 결과를 복제하여 성능 최적화 가능.