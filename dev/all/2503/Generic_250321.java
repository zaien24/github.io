//자바 제네릭
// GOrigin
public class GOrigin <T> {
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

public class TypeGenericTest {

    public static void main(String[] args) {
        GOrigin<String> origin1 = new GOrigin<String>();
        String str = "안녕하세요";
        origin1.setData(str);
//		origin1.setData(new Integer(300)); //String 타입외에 다른 타입을 메서드로 전달 시 에러를 발생시킵니다.
        String result1 = origin1.getData();
//		String result1 = (String)origin1.getData();
        System.out.println(result1);

        GOrigin<Integer> origin2 = new GOrigin<Integer>();
        int num = 100;
        origin2.setData(new Integer(num));
//		origin2.setData(str); //Integer 객체외 다른 타입을 메서드로 전달 시 에러를 발생시킵니다.
        Integer result2 = origin2.getData();
//		Integer result2 = (Integer)origin2.getData();
        System.out.println(result2);

        // 타입을 지정 안하면 모든 종류의 객체 저장 가능
        GOrigin origin3 = new GOrigin();
//		GOrigin<Object> origin3 = new GOrigin<Object>();
        origin3.setData("hello!");
        String result3 = (String)origin3.getData();
        System.out.println(result3);

        origin3.setData(new Integer(300));
        Integer result4 = (Integer)origin3.getData();
        System.out.println(result4.intValue());

    }
}

/////////

public class Student {
    String name;
    int grade;

    Student() {
        this("이순신", 3);
    }

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "이름: " + name +" 학년: " +grade;
    }

}

public class ArrayListGenericTest {
    public static void main(String[] args) {
        List<String> aList1 = new ArrayList<String>();
//		aList1.add(new Integer(100));  //문자열 외 다른 타입 객체는 저장할 수 없습니다.
        aList1.add("이순신");
        aList1.add("홍길동");
        aList1.add("손흥민");
        aList1.add("차범근");

        String name1= aList1.get(0);
//		String name2= (String)aList1.get(0);  //String 타입으로 다운캐스팅 할 필요 없습니다.
//		String name3= (Integer)aList1.get(0);  //다른 타입으로 다운캐스팅 시 에러를 발생시킵니다.
        for(int i = 0; i< aList1.size(); i++) {
            String name2 = aList1.get(i);
            System.out.println(name2);
        }
        System.out.println();

        List<Student> aList2 = new ArrayList<Student>();
        aList2.add(new Student("이순신", 3));
        aList2.add(new Student("홍길동", 2));
        aList2.add(new Student("손흥민", 1));

        // list.add(new Integer(123)); //다른 타입 저장 시 에러를 발생시킵니다.
        Student s1 = aList2.get(0);
        System.out.println(s1);
        System.out.println();
//		 Student s = (Student)list.get(0);  //String 타입으로 다운캐스팅을 할 필요가 없습니다.
//		 Student s = (Integer)list.get(0);  //다른 타입으로 다운캐스팅 시 에러가 발생합니다.

        for(int i= 0; i< aList2.size(); i++) {
            Student s2 = aList2.get(i);
            System.out.println(s2.toString());
        }
    }

}


////
public class Product {
    private String code;
    private String name;
    private String color;
    private int qty;

    public Product() {
        this("0001", "스마트폰", "블랙", 100);
    }

    public Product(String code, String name, String color, int qty) {
        this.code = code;
        this.name = name;
        this.color = color;
        this.qty = qty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}

public class ProductGenericTest {
    //Product 객체만 저장된 ArrayList만 매개값으로 받을 수 있습니다.
    public static void getProdInfo(List<Product> pList) {
        Product prod = null;
        String code = null;
        String name = null;
        String color = null;
        int qty = 0;

        System.out.println("제품 정보 출력");
        System.out.println("--------------------------------");
        for (int i = 0; i < pList.size(); i++) {
            prod = pList.get(i);
            code = prod.getCode();
            name = prod.getName();
            color = prod.getColor();
            qty = prod.getQty();

            System.out.println("제품번호 = " + code);
            System.out.println("제품이름 = " + name);
            System.out.println("제품색상 = " + color);
            System.out.println("제품수량 = " + qty);
            System.out.println();
        }

    }

    public static void main(String[] args) {
        List<Product> pList = new ArrayList<Product>();
        Product p1 = new Product();
        Product p2 = new Product("0002", "스마트 TV", "흰색", 200);
        Product p3 = new Product("0003", "노트북", "은색", 100);

        //Product 객체만 저장할 수 있습니다.
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);
        //  pList.add("홍길동");
        //  pList.add(new Integer(123));
        getProdInfo(pList);

        List<String> sList = new ArrayList<String>();
        sList.add("홍길동");
        sList.add("차범근");
        sList.add("박지성");
//		getProdInfo(sList);  //컴파일 에러가 발생합니다.


    }

}


public class Student {
    String name;
    int grade;

    Student() {
        this("이순신", 3);
    }

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "이름: " + name +" 학년: " +grade;
    }

}

public class MyMap <K, V>{
    private K key;
    private V value;


    public void   put(K key, V value){
        this.key = key ;
        this.value = value;

    }

    public V  get(K key){
        return value;
    }

}


public class MyMapGenericTest {
    public static void main(String[] args) {
        MyMap<String, String> myMap1 = new MyMap<String, String>();
        myMap1.put("홍길동", "서울시");
//		myMap1.put("홍길동", new Integer(200)); 다른 타입을 value로 사용하면 컴파일 에러가 발생합니다.
        String addr = myMap1.get("홍길동");
//		String addr = (Integer)myMap1.get("홍길동");  //다른 타입으로 다운캐스팅하면 컴파일 에러가 발생합니다.
        System.out.println(addr);

        MyMap<Integer, String> myMap2 = new MyMap<Integer, String>();
        myMap2.put(100, "사과");
//		myMap2.put("바나나", 200 ); 다른 타입을 key와 value로 사용하면 컴파일 에러가 발생합니다.
        String fruit = myMap2.get(100);
//		String fruit2 = (Integer)myMap2.get(100);  //다른 타입으로 다운캐스팅하면 컴파일 에러가 발생합니다.
        System.out.println(fruit);

        MyMap<String, Student> myMap3 = new MyMap<String, Student>();
        myMap3.put("서울시", new Student("이순신", 3));
//		myMap3.put("부산시",new College());  //다른 타입 객체를 value로 사용하면 컴파일 에러가 발생합니다.
        Student s = myMap3.get("서울시");
//		Student s2 = (String)myMap3.get("서울시");  //다른 타입으로 다운캐스팅하면 컴파일 에러가 발생합니다.
        System.out.println(s.toString());

    }

}