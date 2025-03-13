class DeepPrototype implements Clneable {
    private String name;
    private NestedObject nestedObject;

    public DeepPrototype(String name, String nestedData) {
        this.name = name;
        this.nestedObject = new NestedObject(nestedObject);
    }

    @Override
}