class DeepPrototype implements Cloneable {
    private String name;
    private NestedObject nestedObject;

    public DeepPrototype(String name, String nestedData) {
        this.name = name;
        this.nestedObject = new NestedObject(nestedData);
    }

    @Override
    public DeepPrototype clone() {
        return new DeepPrototype(this.name, this.nestedObject.getData());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNestedData(String data) {
        this.nestedObject.setData(data);
    }

    public String getName() {
        return name;
    }

    public String getNestedData() {
        return nestedObject.getData();
    }

    static class NestedObject {
        private String data;

        public NestedObject(String data) {
            this.data = data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }
}

// 클라이언트 코드
public class DeepPrototypeExample {
    public static void main(String[] args) {
        DeepPrototype original = new DeepPrototype("Original", "Nested Data");
        DeepPrototype cloned = original.clone();

        // 원본 수정
        original.setNestedData("Changed Nested Data");

        System.out.println("Original Nested Data: " + original.getNestedData());
        System.out.println("Cloned Nested Data: " + cloned.getNestedData());
    }
}
