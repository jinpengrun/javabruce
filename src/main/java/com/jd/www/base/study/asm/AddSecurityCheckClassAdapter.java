package com.jd.www.base.study.asm;


import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zhujinpeng on 16/1/12.
 */
public class AddSecurityCheckClassAdapter extends ClassAdapter {

    public AddSecurityCheckClassAdapter(ClassVisitor classVisitor) {
        //负责改写后台代码输出
        super(classVisitor);
    }

    public MethodVisitor visitMethod(final int access,final String name, final String desc,final String signature,final String[] exceptions){
        MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);
        MethodVisitor wrappedMv = mv;
        if(mv!=null){
            //对于operation方法
            if("operation".equals(name)){
               // wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            }
        }
        return wrappedMv;
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

    public static void main(String[]args)throws  Exception{
          //生成 新的class 文件  放在了目录下  包含了 调用 静态方法的  这块是全路径
        ClassReader cr = new ClassReader("com.jd.www.base.study.asm.Account");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);

        //cr.accept(classAdapter, ClassReader.SKIP_DEBUG);

        byte[] data = cw.toByteArray();
        File file = new File("Account.class");
        System.out.println(file.getAbsolutePath());
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();


        Account account = new Account();
        account.operation();
    }


}
