// 1 Flyweight (공통 인터페이스)
interface FontStyle {
    void applyStyle(String text);
}

// 2. ConcreteFlyweight (구체적인 플라이웨이트 객체)
class ConcreteFontStyle implements FontStyle {
    private final String font;
    private final int size;
    private final String color;

    public ConcreteFontStyle(String font, int size, String color) {
        this.font = font;
        this.size = size;
        this.color = color;
    }

    @Override
    public void applyStyle(String text) {
        System.out.println("ConcreteFontStyle.applyStyle(" + text + ", " + size + ", " + color + ")");
    }
}

// 3. FlyweightFactory (풀라이에웨이트 팩토리)
class FontStyleFactory {
    private static final Map<String, FontStyle> fontCache = new HashMap<>();

    public static FontStyle getFontStyle(String font, int size, String color) {
        String key = font + size + color;
        if (!fontCache.containsKey(key)) {
            System.out.println("새로운 폰트 스타일 생성: " + key);
            fontCache.put(key, new ConcreteFontStyle(font, size, color));
        }

        return fontCache.get(key);
    }
}

// 4. Clinet (클라이언트)
public class FlyweightPatternExample {
    public static void main(String[] args) {
        FontStyle style1 = FontStyleFactory.getFontStyle("Arial", 12, "Black");
        FontStyle style2 = FontStyleFactory.getFontStyle("Arial", 12, "Black");
        FontStyle style3 = FontStyleFactory.getFontStyle("Times New Roman", 14, "Blue");

        style1.applyStyle("Hello, world!");
        style2.applyStyle("Flyweight Pattern");
        style3.applyStyle("Java Design Patterns");

        System.out.println("\nstyle1 == style2: " + (style1 == style2)); // true (객체 공유됨)
        System.out.println("style1 == style3: " + (style1 == style3)); // false (다른 스타일)
    }
}