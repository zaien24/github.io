//이터레이터 패턴

// Java 컬렉션 프레임워크에서의 활용
public class JavaIteratorExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("X", "Y", "Z");
        Iterator<String> iterator = list.iteraotr();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

// 배열 기반 컬렉션을 순회하는 이터레이터 패턴의 구현
// Iterator 인터페이스 정의
public interface Iterator<T> {
    boolean hasNext(); // 다음 요소가 있는지 확인
    T next();           // 다음 요소 반환
}

// 집합체 인터페이스 정의 (컬렉션 인터페이스)
public interface Aggregate<T> {
    Iterator<T> createIterator();   // 이터레이터 객체 생성
}

// 이터레이터 구현
public class ArrayIterator<T> implements Iterator {
    private T[] array;
    private int index = 0;

    public ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new RuntimeException("No more elements");
        }
        return array[index++];
    }
}

// 구체적인 컬렉션 클래스 ( 배열을 내부적으로 저장)
public class ArrayCollection<T> implements Aggregate<T> {
    private T[] array;

    public ArrayCollection(T[] array) {
        this.array = array;
    }

    @Override
    public Iterator<T> createIterator() {
        return new ArrayIterator<T>(array);
    }
}

// 클라이언트 코드 실행
public class IteratorPatternExample {
    public static void main(String[] args) {
        String[] itmes = {"A", "B", "C", "D"};
        ArrayCollection<String> collection = new ArrayCollection<>(items);
        Iterator<String> iterator = collection.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}















































