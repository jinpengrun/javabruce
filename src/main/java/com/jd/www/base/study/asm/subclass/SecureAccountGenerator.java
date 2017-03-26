package com.jd.www.base.study.asm.subclass;

import com.jd.www.base.study.asm.Account;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * Created by zhujinpeng on 16/1/12.
 */
public class SecureAccountGenerator {
    private static AccountGeneratorClassLoader classLoader =
            new AccountGeneratorClassLoader();

    private static Class secureAccountClass;

    public Account generateSecureAccount() throws Exception {
        if (null == secureAccountClass) {
            ClassReader cr = new ClassReader("com.jd.www.base.study.asm.Account");
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
            //cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = cw.toByteArray();
            //System.out.println(new String(data));
            //System.out.println(data!=null?data.length:data);
            secureAccountClass = classLoader.defineClassFromClassFile(
                    //包全路径 首先生成的时候 必须在这个 classpath里
                    "com.jd.www.base.study.asm.Account$EnhancedByASM",data);
        }
        return (Account) secureAccountClass.newInstance();
    }

    private static class AccountGeneratorClassLoader extends ClassLoader {
        public Class defineClassFromClassFile(String className,
                                              byte[] classFile) throws ClassFormatError {
            return defineClass(className, classFile, 0,
                    classFile.length);
        }
    }

    public static void main(String[]args)throws Exception{
        SecureAccountGenerator secureAccountGenerator = new SecureAccountGenerator();
        Account account = secureAccountGenerator.generateSecureAccount();
        account.operation();
    }
}
