package com.jd.www.base.study.asm.subclass;


import com.jd.www.base.study.asm.Account;
import org.objectweb.asm.*;

/**
 * Created by zhujinpeng on 16/1/12.
 */
public class AddSecurityCheckClassAdapter extends ClassAdapter {

    //将动态生成类改造成原始类 Account 的子类 不改变原来的 类  只生成新的类

    private String enhancedSuperName="";

    public AddSecurityCheckClassAdapter(ClassVisitor classVisitor) {
        //负责改写后台代码输出
        super(classVisitor);
    }

    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            //如果是 operation 方法
            if (name.equals("operation")) {
                //wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            } else if (name.equals("<init>")) {
                //wrappedMv = new ChangeToChildConstructorMethodAdapter(mv,
                       // enhancedSuperName);
            }
        }
        return wrappedMv;
    }

    //重写visit方法
    public void visit(final int version, final int access, final String name,
                      final String signature, final String superName,
                      final String[] interfaces) {
        //改变类命名
        String enhancedName = name + "$EnhancedByASM";  // 改变类命名
        enhancedSuperName = name; // 改变父类，这里是”Account”
        super.visit(version, access, enhancedName, signature,
                enhancedSuperName, interfaces);
    }



    class AddSecurityCheckMethodAdapter extends MethodAdapter {
        public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
            super(mv);
        }

        public void visitCode() {
            //调用 静态方法   //全路径 进行操作  添加静态方法，
            visitMethodInsn(Opcodes.INVOKESTATIC, "com/jd/www/base/study/asm/SecurityChecker",
                    "checkSecurity", "()V");
        }
    }

    class ChangeToChildConstructorMethodAdapter extends MethodAdapter {
        private String superClassName;

        public ChangeToChildConstructorMethodAdapter(MethodVisitor mv,
                                                     String superClassName) {
            super(mv);
            this.superClassName = superClassName;
        }

        public void visitMethodInsn(int opcode, String owner, String name,
                                    String desc) {
            // 调用父类的构造函数时
            if (opcode == Opcodes.INVOKESPECIAL && name.equals("<init>")) {
                owner = superClassName;
            }
            super.visitMethodInsn(opcode, owner, name, desc);// 改写父类为 superClassName
        }
    }

    public static void main(String[]args)throws  Exception{
          //生成 新的class 文件  放在了目录下  包含了 调用 静态方法的  这块是全路径
//        ClassReader cr = new ClassReader("com.jd.www.base.study.asm.Account");
//        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//        ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
//
//        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
//
//        byte[] data = cw.toByteArray();
//        File file = new File("Account.class");
//        System.out.println(file.getAbsolutePath());
//        FileOutputStream fout = new FileOutputStream(file);
//        fout.write(data);
//        fout.close();


        Account account = new Account();
        account.operation();
    }


}
