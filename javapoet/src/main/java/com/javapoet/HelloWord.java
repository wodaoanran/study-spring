package com.javapoet;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;

/**
 * @author OVAmach
 * @date 2021/8/13
 */
public final class HelloWord {
    public static void main(String[] args) {
        ClassName activity = ClassName.get("android.app", "Activity");

        TypeSpec.Builder mainActivityBuilder = TypeSpec.classBuilder("MainActivity")
                .addModifiers(Modifier.PUBLIC)
                .superclass(activity);

        ClassName override = ClassName.get("java.lang", "Override");

        ClassName bundle = ClassName.get("android.os", "Bundle");

        ClassName nullable = ClassName.get("android.support.annotation", "Nullable");

        ParameterSpec savedInstanceState = ParameterSpec.builder(bundle, "savedInstanceState")
                .addAnnotation(nullable)
                .build();

        MethodSpec onCreate = MethodSpec.methodBuilder("onCreate")
                .addAnnotation(override)
                .addModifiers(Modifier.PROTECTED)
                .addParameter(savedInstanceState)
                .addStatement("super.onCreate(savedInstanceState)")
                .addStatement("setContentView(R.layout.activity_main)")
                .build();

        TypeSpec mainActivity = mainActivityBuilder.addMethod(onCreate)
                .build();

        JavaFile file = JavaFile.builder("com.javapoet", mainActivity).build();

        try {
            file.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
