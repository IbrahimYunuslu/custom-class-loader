public class Main {
    public static void main(String[] args) {
        String customClassPath = "custom_classes/";

        CustomClassLoader customClassLoader = new CustomClassLoader(customClassPath);

        try {
            Class<?> myClass = customClassLoader.loadClass("CustomClass");
            Object instance = myClass.getDeclaredConstructor().newInstance();
            System.out.println("Class loaded: " + myClass.getName());
            System.out.println("Class loader: " + myClass.getClassLoader());
        } catch (Exception e) {
            System.err.println("Error loading or instantiating class: " + e.getMessage());
        }
    }
}