import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {

    private String classPath;

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filePath = classPath + name.replace('.', '/') + ".class";
        byte[] classData = loadClassData(filePath);

        if (classData == null) {
            throw new ClassNotFoundException("Class not found: " + name);
        }

        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] loadClassData(String path) {
        try (FileInputStream fis = new FileInputStream(new File(path))) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            return buffer;
        } catch (IOException e) {
            System.err.println("Error loading class data: " + e.getMessage());
            return null;
        }
    }
}