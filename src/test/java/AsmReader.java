import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

public class AsmReader {
    public static void main(String[] args)  {
        try {
            ClassReader classReader = new ClassReader("java.lang.String");
            TraceClassVisitor visit = new TraceClassVisitor(new PrintWriter(System.out));
            classReader.accept(visit,0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
